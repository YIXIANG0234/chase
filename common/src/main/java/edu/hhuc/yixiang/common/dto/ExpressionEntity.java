package edu.hhuc.yixiang.common.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用了测试el表达式的对象
 *
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/7 17:35:24
 */
@Data
public class ExpressionEntity {
    private String name;
    private String expressionString;
    private List<String> args;
    private Map<String, Integer> weight;
    private List<ExpressionInfo> expressions;
    private ExpressionContext context;

    @Data
    private static class Token {
        private Character token;
        private Boolean valid;

        private static Token of(Character token) {
            Token instance = new Token();
            instance.setToken(token);
            instance.setValid((token - 'a') % 2 == 0);
            return instance;
        }
    }

    @Data
    private static class ExpressionInfo {
        private List<Token> tokens;
        private String expression;

        private static ExpressionInfo of(List<Character> characters, String expression) {
            ExpressionInfo expressionInfo = new ExpressionInfo();
            expressionInfo.setExpression(expression);
            List<Token> list = new ArrayList<>();
            for (Character ch : characters) {
                list.add(Token.of(ch));
            }
            expressionInfo.setTokens(list);
            return expressionInfo;
        }
    }

    @Data
    private static class ExpressionContext {
        private Integer id;
        private String contextName;

        public ExpressionContext() {
        }

        public ExpressionContext(Integer id, String contextName) {
            this.id = id;
            this.contextName = contextName;
        }
    }

    public static ExpressionEntity init() {
        ExpressionEntity entity = new ExpressionEntity();
        entity.setName("一个SpEL表达式");
        entity.setExpressionString("#{250 > 200 && 200 < 4000}");
        entity.setArgs(Lists.newArrayList("hello", "world", "chase", "java", "parse"));
        entity.setWeight(Map.of("hello", 5, "world", 3, "chase", 10, "java", 1, "parse", 8));
        ExpressionInfo expression1 = ExpressionInfo.of(Lists.newArrayList('a', 'b', 'c'), "表达式一");
        ExpressionInfo expression2 = ExpressionInfo.of(Lists.newArrayList('d', 'e'), "表达式二");
        ExpressionInfo expression3 = ExpressionInfo.of(Lists.newArrayList('f', 'g', 'g', 'i'), "表达式三");
        entity.setExpressions(Lists.newArrayList(expression1, expression2, expression3));
        entity.setContext(new ExpressionContext(13452, "表达式上下文"));
        return entity;
    }

    public Integer expressionStringLength() {
        return StringUtils.isBlank(this.expressionString) ? 0 : this.expressionString.length();
    }
}
