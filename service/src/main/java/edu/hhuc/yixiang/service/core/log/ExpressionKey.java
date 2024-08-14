package edu.hhuc.yixiang.service.core.log;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.ParserContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/12 11:10:05
 */
public class ExpressionKey implements Comparable<ExpressionKey> {
    private final AnnotatedElementKey element;
    private final String expression;
    private final ParserContext parserContext;

    protected ExpressionKey(AnnotatedElementKey element, String expression, ParserContext parserContext) {
        Assert.notNull(element, "AnnotatedElementKey must not be null");
        Assert.notNull(expression, "Expression must not be null");
        Assert.notNull(parserContext, "ParserContext must not be null");
        this.element = element;
        this.expression = expression;
        this.parserContext = parserContext;
    }

    public static ExpressionKey of(AnnotatedElementKey element, String expression, ParserContext parserContext) {
        return new ExpressionKey(element, expression, parserContext);
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (Objects.isNull(other)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (other instanceof ExpressionKey that) {
            return this.element.equals(that.element) && ObjectUtils.nullSafeEquals(this.expression, that.expression) && this.parserContext.equals(that.parserContext);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = this.element.hashCode();
        result = result * 17 + this.expression.hashCode();
        result = result * 17 + this.parserContext.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.element + " with expression \"" + this.expression + "\"" + " with mark \"" + this.parserContext.getExpressionPrefix() + this.parserContext.getExpressionSuffix() + "\"";
    }

    @Override
    public int compareTo(ExpressionKey other) {
        int result = this.element.toString().compareTo(other.element.toString());
        if (result == 0) {
            result = this.expression.compareTo(other.expression);
            if (result == 0) {
                result = (this.parserContext.getExpressionPrefix() + this.parserContext.getExpressionSuffix()).compareTo(other.parserContext.getExpressionPrefix() + other.parserContext.getExpressionSuffix());
            }
        }
        return result;
    }
}
