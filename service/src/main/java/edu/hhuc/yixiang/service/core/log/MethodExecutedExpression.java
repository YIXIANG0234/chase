package edu.hhuc.yixiang.service.core.log;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/9 16:55:04
 */
public class MethodExecutedExpression {
    private final String expressionString;
    private final Expression[] executedBeforeExpressions;
    private final Expression[] executedAfterExpressions;
    private final Map<Expression, String> expressionValues = new HashMap<>(16);


    public MethodExecutedExpression(String expressionString, Expression[] executedBeforeExpressions, Expression[] executedAfterExpressions) {
        this.expressionString = expressionString;
        this.executedBeforeExpressions = executedBeforeExpressions;
        this.executedAfterExpressions = executedAfterExpressions;
    }

    public void parseBefore(EvaluationContext context) {
        for (Expression expression : executedBeforeExpressions) {
            expressionValues.put(expression, expression.getValue(context, String.class));
        }
    }

    public void parseAfter(EvaluationContext context) {
        for (Expression expression : executedAfterExpressions) {
            expressionValues.put(expression, expression.getValue(context, String.class));
        }
    }

    public String getValue(MethodExecutedParserContext context) throws EvaluationException {
        StringBuilder sb = new StringBuilder(this.expressionString);
        connectingExpression(sb, executedBeforeExpressions, context.getBefore());
        connectingExpression(sb, executedAfterExpressions, context.getAfter());
        return sb.toString();
    }

    private void connectingExpression(StringBuilder result, Expression[] expressions, ParserContext parserContext) {
        for (Expression expression : expressions) {
            String originExpression = parserContext.getExpressionPrefix() + expression.getExpressionString() + parserContext.getExpressionSuffix();
            int startIndex = result.indexOf(originExpression);
            int length = originExpression.length();
            String expressionValue = String.valueOf(expressionValues.get(expression));
            result.replace(startIndex, startIndex + length, expressionValue);
        }
    }
}
