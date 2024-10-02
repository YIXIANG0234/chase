package edu.hhuc.yixiang.service.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/9/5 15:46:51
 */
@Slf4j
@Component
public class LifeCycleBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (LifeCycleBean.LIFE_CYCLE_BEAN_NAME.equals(beanName)) {
            log.warn("{}.【LifeCycleBean】BeanPostProcessor.postProcessBeforeInitialization", LifeCycleBean.LIFE_CYCLE_STEP.incrementAndGet());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (LifeCycleBean.LIFE_CYCLE_BEAN_NAME.equals(beanName)) {
            log.warn("{}.【LifeCycleBean】BeanPostProcessor.postProcessAfterInitialization", LifeCycleBean.LIFE_CYCLE_STEP.incrementAndGet());
        }
        return bean;
    }
}
