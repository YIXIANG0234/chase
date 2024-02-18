package edu.hhuc.yixiang.service.core.log;

import com.google.common.base.Strings;
import edu.hhuc.yixiang.common.annotation.LogRecord;
import edu.hhuc.yixiang.common.constant.StringConstants;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.utils.DateUtil;
import edu.hhuc.yixiang.common.utils.IPUtil;
import edu.hhuc.yixiang.service.context.LogRecordContext;
import edu.hhuc.yixiang.service.core.LogRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 22:00:40
 */
@Component
public class LogRecordParser {
    @Autowired
    private LogRecordExpressionEvaluator expressionEvaluator;

    @Autowired
    private ParseFunctionFactory parseFunctionFactory;

    @Autowired
    private LogRecordService logRecordService;

    private static final Pattern PATTERN = Pattern.compile("\\{\\s*(\\w*(?:\\.before)?)\\s*\\{(.*?)}}");
    private static final String REPLACE_HOLDER = "REPLACE_HOLDER";

    public void doRecord(Map<String, String> expressionParseResult, LogRecord logRecord) {
        Date startTime = (Date) LogRecordContext.getVariable("startTime");
        Date endTime = (Date) LogRecordContext.getVariable("endTime");
        OperationLogDTO operationLog = OperationLogDTO.builder()
                .operatorUser(expressionParseResult.get(logRecord.operatorUser()))
                .content(expressionParseResult.get(logRecord.content()))
                .businessId(expressionParseResult.get(logRecord.businessId()))
                .operatorModule(logRecord.operatorModule().getCode())
                .operatorType(logRecord.operatorType().getCode())
                .ip(IPUtil.getClientIp())
                .startTime(startTime)
                .endTime(endTime)
                .duration((int) DateUtil.durationBetween(startTime, endTime))
                .build();
        logRecordService.recordOperation(operationLog);
    }

    public Map<String, String> doParseCompletedExpression(Collection<String> expressions, Class<?> targetClass, Method method, Object[] args, Object methodResult, Map<String, String> executeBeforeParseResult) {
        Map<String, String> expressionParseResult = new HashMap<>();
        EvaluationContext evaluationContext = LogRecordEvaluationContext.createEvaluationContext(method, args, new DefaultParameterNameDiscoverer(), methodResult);
        for (String expression : expressions) {
            StringBuilder singleTemplateResult = new StringBuilder();
            Matcher matcher = PATTERN.matcher(expression);
            while (matcher.find()) {
                String functionName = matcher.group(1);
                String functionArgumentExpression = matcher.group(2);
                String parseResult;
                // 在目标方法执行前已经求得值的函数
                if (IParseFunction.executeBefore(functionName)) {
                    parseResult = executeBeforeParseResult.get(functionName);
                } else {
                    AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);
                    parseResult = executeExpression(functionName, functionArgumentExpression, annotatedElementKey, evaluationContext);
                }
                matcher.appendReplacement(singleTemplateResult, REPLACE_HOLDER);
                singleTemplateResult = new StringBuilder(singleTemplateResult.toString().replace(REPLACE_HOLDER, Strings.nullToEmpty(parseResult)));
            }
            matcher.appendTail(singleTemplateResult);
            expressionParseResult.put(expression, singleTemplateResult.toString());
        }
        return expressionParseResult;
    }


    public Map<String, String> parseExpressionBeforeExecuteFunction(Collection<String> expressions, Class<?> targetClass, Method method, Object[] args) {
        Map<String, String> functionParseResultMapping = new HashMap<>();
        EvaluationContext evaluationContext = LogRecordEvaluationContext.createEvaluationContext(method, args, new DefaultParameterNameDiscoverer(), null);
        for (String expression : expressions) {
            Matcher matcher = PATTERN.matcher(expression);
            while (matcher.find()) {
                String functionName = matcher.group(1);
                String functionArgumentExpression = matcher.group(2);
                if (IParseFunction.executeBefore(functionName)) {
                    // 自定义函数求值
                    AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);
                    String functionNameNoSuffix = functionName.replace(IParseFunction.EXECUTE_BEFORE_PREFIX, StringConstants.EMPTY);
                    String parseResult = executeExpression(functionNameNoSuffix, functionArgumentExpression, annotatedElementKey, evaluationContext);
                    functionParseResultMapping.put(functionName, parseResult);
                }
            }
        }
        return functionParseResultMapping;
    }

    private String executeExpression(String functionName, String functionArgumentExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        IParseFunction parseFunction = parseFunctionFactory.getParseFunction(functionName);
        // 没有自定义函数，简单的EL表达式求值
        if (Objects.isNull(parseFunction)) {
            Object executeResult = expressionEvaluator.parseExpression(annotatedElementKey, functionArgumentExpression, evaluationContext);
            return Objects.isNull(executeResult) ? null : executeResult.toString();
        }
        // 需要对自定义函数求值，可能有多个入参
        String[] argumentExpressions = StringUtils.split(functionArgumentExpression, ",");
        List<Object> arguments = new ArrayList<>();
        for (String expression : argumentExpressions) {
            if (StringUtils.isBlank(expression)) {
                continue;
            }
            expression = expression.trim();
            arguments.add(expressionEvaluator.parseExpression(annotatedElementKey, expression, evaluationContext));
        }
        // 自定义函数求值
        return parseFunction.apply(arguments.toArray());
    }
}
