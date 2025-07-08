package edu.hhuc.yixiang.common.lang.demo;

import edu.hhuc.yixiang.common.lang.TypeTest;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/17 15:39:00
 */
public class TypeDemo {
    public static void main(String[] args) throws Exception {
        //        testTypeVariable();
        testParameterizedType();
    }

    /**
     * TypeVariable：获取
     *
     * @throws Exception
     */
    public static void testTypeVariable() throws Exception {
        Field field = TypeTest.class.getField("v");
        TypeVariable typeVariable = (TypeVariable) field.getGenericType();
        Type[] types = typeVariable.getBounds();
        for (Type type : types) {
            System.out.println("getBounds: " + type.getTypeName());
            typeVariable.getGenericDeclaration();
        }
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        System.out.println("getGenericDeclaration: " + genericDeclaration);

        AnnotatedType[] annotatedTypes = typeVariable.getAnnotatedBounds();
        for (AnnotatedType annotatedType : annotatedTypes) {
            System.out.println("getAnnotatedBounds: " + annotatedType.toString());
        }
    }

    public static void testParameterizedType() throws Exception {
        Field field = TypeTest.class.getField("map");
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        System.out.println("getRawType: " + parameterizedType.getRawType());
        for (Type type : parameterizedType.getActualTypeArguments()) {
            System.out.println("getActualTypeArguments: " + type);
        }
    }
}
