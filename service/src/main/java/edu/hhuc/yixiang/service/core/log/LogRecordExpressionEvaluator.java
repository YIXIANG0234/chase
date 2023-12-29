package edu.hhuc.yixiang.service.core.log;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 21:43:16
 */
@Component
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {
    private final Map<ExpressionKey, Expression> cache;

    public LogRecordExpressionEvaluator() {
        super();
        this.cache = new HashMap<>(64);
    }

    public Object parseExpression(AnnotatedElementKey elementKey, String expression, EvaluationContext context) {
        return getExpression(cache, elementKey, expression).getValue(context, Object.class);
    }
}
