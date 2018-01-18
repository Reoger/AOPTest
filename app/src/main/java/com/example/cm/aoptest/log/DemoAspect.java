package com.example.cm.aoptest.log;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by CM on 2018/1/18.
 */

@Aspect
public class DemoAspect {

    static final String TAG = "TAG";

    @Pointcut("execution(@com.example.cm.aoptest.MainActivity.*  * *(..))")
    public void logForActivity(){}

    @Before("logForActivity()")
    public void log(JoinPoint joinPoint){
        Log.e(TAG, "log: 自动打印" );
    }


}
