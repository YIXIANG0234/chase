package edu.hhuc.yixiang.service.core.log;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 21:43:16
 */
@Component
public class LogRecordExpressionEvaluator {
    private final MethodExecutedExpressionParser parser;
    private final Map<ExpressionKey, List<Expression>> cache;

    public LogRecordExpressionEvaluator() {
        this.parser = new MethodExecutedExpressionParser();
        this.cache = new HashMap<>(64);
    }

    public MethodExecutedExpressionParser getParser() {
        return this.parser;
    }

    public List<Expression> getExpression(AnnotatedElementKey elementKey, String expression, ParserContext context) {
        ExpressionKey expressionKey = ExpressionKey.of(elementKey, expression, context);
        List<Expression> expressions = cache.get(expressionKey);
        if (CollectionUtils.isEmpty(expressions)) {
            expressions = parseExpression(expression, context);
            cache.put(expressionKey, expressions);
        }
        return expressions;
    }

    public List<Expression> parseExpression(String expression, ParserContext context) {
        return getParser().parseFromExpressionTemplate(expression, context);
    }
}
