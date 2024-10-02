package edu.hhuc.yixiang.service.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行顺序：
 * 1.【LifeCycleBean】InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation
 * 2.【LifeCycleBean】构造函数
 * 3.【LifeCycleBean】InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation
 * 4.【LifeCycleBean】属性填充：2024-09-05 16:28:39
 * 5.【LifeCycleBean】BeanNameAware.setBeanName：lifeCycleBean
 * 6.【LifeCycleBean】BeanPostProcessor.postProcessBeforeInitialization
 * 7.【LifeCycleBean】postConstruct
 * 8.【LifeCycleBean】InitializingBean.afterPropertiesSet
 * 9.【LifeCycleBean】initMethod
 * 10.【LifeCycleBean】BeanPostProcessor.postProcessAfterInitialization
 * 11.【LifeCycleBean】preDestroy
 * 12.【LifeCycleBean】DisposableBean.destroy
 * 13.【LifeCycleBean】destroyMethod
 *
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/9/4 21:50:02
 */
@Slf4j
public class LifeCycleBean implements InitializingBean, DisposableBean, BeanNameAware {
    public static final AtomicInteger LIFE_CYCLE_STEP = new AtomicInteger();
    public static final String LIFE_CYCLE_BEAN_NAME = "lifeCycleBean";
    private String propertySetTime;

    public LifeCycleBean() {
        log.warn("{}.【LifeCycleBean】构造函数", LIFE_CYCLE_STEP.incrementAndGet());
    }

    @Override
    public void setBeanName(String name) {
        log.warn("{}.【LifeCycleBean】BeanNameAware.setBeanName：{}", LIFE_CYCLE_STEP.incrementAndGet(), name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("{}.【LifeCycleBean】InitializingBean.afterPropertiesSet", LIFE_CYCLE_STEP.incrementAndGet());
    }

    @Override
    public void destroy() throws Exception {
        log.warn("{}.【LifeCycleBean】DisposableBean.destroy", LIFE_CYCLE_STEP.incrementAndGet());
    }

    /**
     * 同init-method
     */
    @PostConstruct
    public void postConstruct() {
        log.warn("{}.【LifeCycleBean】postConstruct", LIFE_CYCLE_STEP.incrementAndGet());

    }

    /**
     * 同destroy-method
     */
    @PreDestroy
    public void preDestroy() {
        log.warn("{}.【LifeCycleBean】preDestroy", LIFE_CYCLE_STEP.incrementAndGet());
    }

    public void initMethod() {
        log.warn("{}.【LifeCycleBean】initMethod", LIFE_CYCLE_STEP.incrementAndGet());

    }

    public void destroyMethod() {
        log.warn("{}.【LifeCycleBean】destroyMethod", LIFE_CYCLE_STEP.incrementAndGet());
    }

    @Value(value = "#{T(edu.hhuc.yixiang.common.utils.DateUtil).formatNow()}")
    public void setPropertySetTime(String propertySetTime) {
        this.propertySetTime = propertySetTime;
        log.warn("{}.【LifeCycleBean】属性填充：{}", LIFE_CYCLE_STEP.incrementAndGet(), propertySetTime);
    }
}
