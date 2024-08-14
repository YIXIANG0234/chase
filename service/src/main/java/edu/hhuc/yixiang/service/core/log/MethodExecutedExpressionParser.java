package edu.hhuc.yixiang.service.core.log;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/9 11:40:18
 */
public class MethodExecutedExpressionParser extends SpelExpressionParser {
    public List<Expression> parseFromExpressionTemplate(String expressionString, ParserContext context) {
        List<Expression> expressions = new ArrayList<>();
        Expression expression = super.parseExpression(expressionString, context);
        if (expression instanceof SpelExpression) {
            expressions.add(expression);
        } else if (expression instanceof CompositeStringExpression) {
            for (Expression item : ((CompositeStringExpression) expression).getExpressions()) {
                if (item instanceof SpelExpression) {
                    expressions.add(item);
                }
            }
        }
        return expressions;
    }
}
