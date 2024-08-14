package edu.hhuc.yixiang.service.core.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/12 11:45:26
 */
@Component
public class LogRecordExecutor implements BeanFactoryAware {
    private final LogRecordExpressionEvaluator expressionEvaluator;
    private final InheritableThreadLocal<Stack<Map<String, MethodExecutedExpression>>> expressionHolder;
    private final MethodExecutedParserContext methodExecutedParserContext;
    private final StandardEvaluationContext originalEvaluationContext = new StandardEvaluationContext();
    private LogRecordEvaluationContextFactory evaluationContextFactory;
    private BeanFactory beanFactory;

    public LogRecordExecutor() {
        this.expressionEvaluator = new LogRecordExpressionEvaluator();
        this.expressionHolder = new InheritableThreadLocal<>();
        this.methodExecutedParserContext = MethodExecutedParserContext.DEFAULT;
    }

    public void pushExpressions(List<String> expressions, MethodExecutedMeta meta) {
        Stack<Map<String, MethodExecutedExpression>> expressionStack = expressionHolder.get();
        if (Objects.isNull(expressionStack)) {
            expressionStack = new Stack<>();
            expressionHolder.set(expressionStack);
        }
        expressionStack.push(new HashMap<>());
        for (String expression : expressions) {
            List<Expression> before = expressionEvaluator.getExpression(meta.getAnnotatedElementKey(), expression, methodExecutedParserContext.getBefore());
            List<Expression> after = expressionEvaluator.getExpression(meta.getAnnotatedElementKey(), expression, methodExecutedParserContext.getAfter());
            expressionStack.peek().put(expression, new MethodExecutedExpression(expression, before.toArray(new Expression[0]), after.toArray(new Expression[0])));
        }
    }

    public void parseBefore(MethodExecutedMeta meta) {
        if (Objects.isNull(expressionHolder.get()) || expressionHolder.get().isEmpty()) {
            return;
        }
        Map<String, MethodExecutedExpression> currentExpressions = expressionHolder.get().peek();
        EvaluationContext evaluationContext = this.evaluationContextFactory.forOperation(meta.getMethod(), meta.getArgs(), meta.getResult());
        currentExpressions.forEach((key, expression) -> {
            expression.parseBefore(evaluationContext);
        });
    }

    public void parseAfter(MethodExecutedMeta meta) {
        if (Objects.isNull(expressionHolder.get()) || expressionHolder.get().isEmpty()) {
            return;
        }
        Map<String, MethodExecutedExpression> currentExpressions = expressionHolder.get().peek();
        EvaluationContext evaluationContext = this.evaluationContextFactory.forOperation(meta.getMethod(), meta.getArgs(), meta.getResult());
        currentExpressions.forEach((key, expression) -> {
            expression.parseAfter(evaluationContext);
        });
    }

    public Map<String, String> getValue() {
        if (Objects.isNull(expressionHolder.get()) || expressionHolder.get().isEmpty()) {
            return new HashMap<>();
        }
        Map<String, MethodExecutedExpression> currentExpressions = expressionHolder.get().peek();
        Map<String, String> expressionValue = new HashMap<>();
        currentExpressions.forEach((key, expression) -> {
            expressionValue.put(key, expression.getValue(methodExecutedParserContext));
        });
        return expressionValue;
    }

    public void clearExpressions() {
        if (Objects.nonNull(expressionHolder.get()) && !expressionHolder.get().isEmpty()) {
            expressionHolder.get().pop();
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.originalEvaluationContext.setBeanResolver(new BeanFactoryResolver(this.beanFactory));
        this.evaluationContextFactory = new LogRecordEvaluationContextFactory(originalEvaluationContext);
    }
}
