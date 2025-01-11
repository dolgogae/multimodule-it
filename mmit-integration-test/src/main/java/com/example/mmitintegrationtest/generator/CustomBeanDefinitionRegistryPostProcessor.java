package com.example.mmitintegrationtest.generator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        for (String beanName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);

            String factoryBeanName = beanDefinition.getFactoryBeanName();
            String methodName = beanDefinition.getFactoryMethodName();
            String beanClassName = beanDefinition.getBeanClassName();

            if (beanClassName == null && factoryBeanName != null && methodName != null
                    && !factoryBeanName.startsWith("org.springframework")) {

                // 새로운 빈 이름 생성 및 재등록
                String newBeanName = factoryBeanName + ":" + methodName;
                registry.removeBeanDefinition(beanName);
                registry.registerBeanDefinition(newBeanName, beanDefinition);

                System.out.println("Renamed bean: " + beanName + " → " + newBeanName);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}