package com.example.cm.aoptest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CM on 2018/1/18.
 */
@Target(ElementType.METHOD) //可以注解在方法 上
@Retention(RetentionPolicy.RUNTIME) //运行时（执行时）存在
public @interface AspectAnnotion {
    String value();
}
