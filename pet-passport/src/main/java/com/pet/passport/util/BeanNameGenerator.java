package com.pet.passport.util;

import java.beans.Introspector;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;
@Component
public class BeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String name = Introspector.decapitalize(definition.getBeanClassName());
        return name;
    }
    
    public static void main(String[] args){
        System.out.println(Introspector.decapitalize("com.xiaonei.feed.edm.util.PackageAnnotationBeanNameGenerator"));
    }
}

