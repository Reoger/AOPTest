package com.example.cm.aoptest.net;

import android.content.Context;
import android.util.Log;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by CM on 2018/1/18.
 *
 */

@Aspect
public class AspectActionTask {

    private String TAG = "TAG";


    @Pointcut("execution(@com.example.cm.aoptest.net.AspectNetAnnotion  * *(..))")
    private void apsectCheckNet(){
        Log.d(TAG, "apsectCheckNet: 这个方法里什么时候执行》》");
    }

    @Around("apsectCheckNet()")
    private Object realDoCheckNetByApsect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log.d(TAG, "正在检查 网络状态: ");
        AspectNetAnnotion aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectNetAnnotion.class);

        Context context = (Context) joinPoint.getThis();
        if(NetworkManager.isNetworkConnected(context)){
            return joinPoint.proceed();
        }else{
            Log.d(TAG, "realDoCheckNetByApsect: 网络状态不行！！");
            return false;
        }

    }

}
