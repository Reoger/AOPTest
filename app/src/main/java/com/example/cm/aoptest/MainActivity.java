package com.example.cm.aoptest;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cm.aoptest.log.AspectLogTest;
import com.example.cm.aoptest.net.AspectNetAnnotion;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @AspectLogTest.AspectLog(value = "来自OnCreate的日志信息")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.check_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermisson();
            }
        });

        findViewById(R.id.check_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNet();
            }
        });

        findViewById(R.id.check_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLog();
            }
        });

        findViewById(R.id.log_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogTest();
            }
        });
    }

    @AspectLogTest.AspectLog(value = "来自checkLog的日志信息")
    private void checkLog(){
        Log.d(TAG, "checkLog: 这是在方法内部的a日志");

        for(int i=0;i<3;i++)
            doSomeThing(i);
    }

    @AspectLogTest.AspectLog(value = "来自doSomething的日志信息")
    private void doSomeThing(int i) {

    }

    @AspectAnnotion(value = Manifest.permission.CAMERA)
    public void checkPermisson(){
        Log.d(TAG, "checkPermisson: 检查权限");
    }

    @AspectNetAnnotion()
    private void checkNet(){
        Log.d(TAG,"网络状态可以呀");
        Toast.makeText(this, "网络连接正常", Toast.LENGTH_SHORT).show();
    }

    @AspectLogTest.AspectLog(value = "来自OnStart的日志信息")
    @Override
    protected void onStart() {
        super.onStart();
    }

    @AspectLogTest.AspectLog(value = "来自OnPause的日志信息")
    @Override
    protected void onPause() {
        super.onPause();
    }


    @AspectLogTest.AspectLog(value = "来自OnStop的日志信息")
    @Override
    protected void onStop() {
        super.onStop();
    }

    @AspectLogTest.AspectLog(value = "来自doLogTest的日志信息")
    public  static void doLogTest(){
        Log.d(TAG, "doLogTest: 手写log");
    }
}
