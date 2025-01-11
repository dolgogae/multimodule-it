package com.example.mmitclient.domain.global.generator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

public class CustomBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();

        if (beanClassName == null) {
            throw new IllegalStateException("Bean class name cannot be null");
        }

        String factoryMethodName = definition.getFactoryMethodName();
        if (StringUtils.hasText(factoryMethodName)) {
            return beanClassName + "." + factoryMethodName;
        }

        return beanClassName;
    }
}
