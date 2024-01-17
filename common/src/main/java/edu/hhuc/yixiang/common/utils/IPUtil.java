package edu.hhuc.yixiang.common.utils;

import edu.hhuc.yixiang.common.constant.StringConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/9 21:28:08
 */
public class IPUtil {
    public static String getClientIp() {
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            return StringConstants.EMPTY;
        }
        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_REQUEST);
        if (Objects.isNull(request)) {
            return StringConstants.EMPTY;
        }
        return request.getRemoteAddr();
    }
}
