package com.example.cm.asoptest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cm.aoptest.AspectAnnotion;
import com.example.cm.aoptest.PermissionManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by CM on 2018/1/18.
 */

//声明这个类需要
@Aspect
public class AspectTest {

    private String TAG = "TAG";

    //寻找切片点
    @Pointcut("execution(@com.example.cm.aoptest.AspectAnnotion  * *(..))")
    public void executionAspectJ() {
        Log.d(TAG, "executionAspectJ: 空方法的执行》》》");
    }

    //切面的处理逻辑
    @Around("executionAspectJ()")
    public Object realDoSomeThing(ProceedingJoinPoint joinPoint){
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
            AspectAnnotion aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectAnnotion.class);
            String permission = aspectJAnnotation.value();
            Context context = (Context) joinPoint.getThis();
            Object o = null;
            String result = "";
            if (PermissionManager.getInnerInstance().checkPermission(context, permission)) {
                o = joinPoint.proceed();
                Log.i(TAG, "有权限");
                result =  "有权限";
            } else {
                Log.i(TAG, "没有权限，不可以使用");
                result =  "没有权限，不可以使用";
            }
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }


}
