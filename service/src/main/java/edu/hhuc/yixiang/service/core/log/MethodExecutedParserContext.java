package edu.hhuc.yixiang.service.core.log;

import edu.hhuc.yixiang.service.core.log.function.JoinFunction;
import lombok.Getter;
import org.springframework.expression.ParserContext;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/9 16:35:27
 */
@Getter
public class MethodExecutedParserContext {
    /**
     * 方法执行前执行的SpEL表达式模板
     */
    private final ParserContext before;

    /**
     * 方法执行后执行的SpEL表达式模板
     */
    private final ParserContext after;

    /**
     * 默认模版
     */
    public static MethodExecutedParserContext DEFAULT;

    public MethodExecutedParserContext(ParserContext before, ParserContext after) {
        this.before = before;
        this.after = after;
    }

    public static ParserContext TEMPLATE_EXPRESSION_BEFORE = new ParserContext() {

        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        public String getExpressionPrefix() {
            return "%{";
        }

        @Override
        public String getExpressionSuffix() {
            return "}";
        }
    };

    static {
        DEFAULT = new MethodExecutedParserContext(MethodExecutedParserContext.TEMPLATE_EXPRESSION_BEFORE, ParserContext.TEMPLATE_EXPRESSION);
        new JoinFunction().apply(1,"s");
    }
}
