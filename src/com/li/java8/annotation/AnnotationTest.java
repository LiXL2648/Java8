package com.li.java8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class AnnotationTest {

    @Test
    public void testAnnotation() {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        AnnotationTest obj = new AnnotationTest();
        try {
            Method method = clazz.getMethod("testAnnotations");
            List<MyAnnotation> myAnnotations = Arrays.asList(method.getAnnotationsByType(MyAnnotation.class));
            myAnnotations.forEach(a -> System.out.println(a.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void testAnnotations(@MyAnnotation String value) {
        System.out.println(value);
    }
}
