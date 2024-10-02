package edu.hhuc.yixiang.service.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/9/5 15:58:11
 */
@Slf4j
@Component
public class LifeCycleInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (LifeCycleBean.LIFE_CYCLE_BEAN_NAME.equals(beanName)) {
            log.warn("{}.【LifeCycleBean】InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation", LifeCycleBean.LIFE_CYCLE_STEP.incrementAndGet());
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (LifeCycleBean.LIFE_CYCLE_BEAN_NAME.equals(beanName)) {
            log.warn("{}.【LifeCycleBean】InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation", LifeCycleBean.LIFE_CYCLE_STEP.incrementAndGet());
        }
        return true;
    }
}
