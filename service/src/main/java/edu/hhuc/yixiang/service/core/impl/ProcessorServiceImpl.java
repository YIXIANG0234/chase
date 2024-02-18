package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.base.BaseResultCode;
import edu.hhuc.yixiang.common.constant.NumberConstants;
import edu.hhuc.yixiang.common.constant.StringConstants;
import edu.hhuc.yixiang.common.dto.ServiceCallDTO;
import edu.hhuc.yixiang.common.dto.ServiceCallParamDTO;
import edu.hhuc.yixiang.common.exception.IllegalRequestException;
import edu.hhuc.yixiang.common.exception.MethodExecuteException;
import edu.hhuc.yixiang.common.utils.JsonUtil;
import edu.hhuc.yixiang.service.core.ProcessorService;
import edu.hhuc.yixiang.service.helper.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 11:20:45
 */
@Service
@Slf4j
public class ProcessorServiceImpl implements ProcessorService {

    @Override
    public Object call(ServiceCallDTO serviceCallDTO) {
        Object result;
        try {
            String serviceName = serviceCallDTO.getServiceName();
            String methodName = serviceCallDTO.getMethodName();
            ServiceCallParamDTO[] params = serviceCallDTO.getParams();
            if (serviceName.contains(StringConstants.NUMBER_SIGN)) {
                String[] keys = serviceName.split(StringConstants.NUMBER_SIGN);
                if (keys.length != NumberConstants.TWO || StringUtils.isAnyBlank(keys[0], keys[1])) {
                    throw new IllegalRequestException(BaseResultCode.REQUEST_INVALID_ERROR);
                }
                serviceName = keys[0];
                methodName = keys[1];
            }
            Class<?> clazz = Class.forName(serviceName);
            Object instance = ApplicationContextHolder.getApplicationContext().getBean(clazz);

            Method method;
            Object[] args = null;
            if (Objects.nonNull(params) && params.length > 0) {
                // 方法参数和参数类型
                args = new Object[params.length];
                Class<?>[] paramsTypes = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    if (StringUtils.isBlank(params[i].getType())) {
                        // 没有指定类型的情况下，按照实际类型来算
                        paramsTypes[i] = params[i].getClass();
                        args[i] = params[i];
                    } else {
                        // 指定类型
                        Class<?> type = Class.forName(params[i].getType());
                        paramsTypes[i] = type;
                        args[i] = JsonUtil.parse((String) params[i].getValue(), type);
                    }
                }
                method = clazz.getMethod(methodName, paramsTypes);
            } else {
                method = clazz.getMethod(methodName);
            }
            result = method.invoke(instance, args);
        } catch (Exception e) {
            log.error("execute service error", e);
            throw new MethodExecuteException(BaseResultCode.METHOD_EXECUTE_EXCEPTION);
        }
        return result;
    }
}
