package edu.hhuc.yixiang.base.pattern;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 20:37:05
 */
public class MatcherExample {
    private static final Pattern PATTERN = Pattern.compile("\\{\\s*(\\w*)\\s*\\{(.*?)}}");

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1","2","3");
        System.out.println(list.stream().skip(0).findFirst().get());
        System.out.println(list.stream().skip(1).findFirst().get());
        System.out.println(list.stream().skip(2).findFirst().get());
        System.out.println(list.stream().skip(3).findFirst().orElse(null));
        Matcher matcher = PATTERN.matcher("{queryInterviewProcessStatusPre{#examinationResultDto.interviewId}}=>{{T(com.pdd.ei.recruitment.common.enums.process.CampusNodeStatusEnum).getMessageByCode(#examinationResultDto.interviewStatus)}}");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
        Pattern p = Pattern.compile("java");
        Matcher m = p.matcher("The java book is java program book c");
        StringBuilder result = new StringBuilder();
        while (m.find()) {
            System.out.println(m.group());
            m.appendReplacement(result, "hello");
            System.out.println(result);
        }
        m.appendTail(result);
        System.out.println(result);

    }
}
