package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.ExpressionDTO;
import edu.hhuc.yixiang.common.dto.ExpressionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/7 14:42:57
 */
@RestController
@RequestMapping("/el")
public class SpELController {

    @Value("#{2^4}")
    private List<String> template;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double random;

    @Value("#{T(edu.hhuc.yixiang.common.dto.ExpressionEntity).init()}")
    private ExpressionEntity expressionEntity;

    @PostMapping("/parse")
    public BaseResponse<Object> parse(@RequestBody ExpressionDTO expressionDTO) {
        // 1.创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(expressionEntity);
        context.setVariable("pass", "this is a password");

        // 2.解析表达式
        Expression expression = parser.parseExpression(expressionDTO.getExpression());

        // 3.执行表达式
        Object result = expression.getValue(context);

        return BaseResponse.ofSuccess(result);
    }

    @GetMapping("/get")
    public BaseResponse<Object> get() {
        template.add(String.valueOf(random));
        return BaseResponse.ofSuccess(template);
    }

    @PostMapping("/template")
    public BaseResponse<Object> template(@RequestBody ExpressionDTO expressionDTO) {
        // 1.创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(expressionEntity);

        // 2.解析表达式，设置TemplateParserContext，使用表达式模板#{}
        Expression expression = parser.parseExpression(expressionDTO.getExpression(), new TemplateParserContext());

        // 3.执行表达式
        Object result = expression.getValue(context);

        return BaseResponse.ofSuccess(result);
    }
}
