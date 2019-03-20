package com.bw.com.baweistore.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author liuruiqi
 * @fileName MyApplication
 * @package com.bw.com.baweistore.app
 * @date 2019/3/20 14:09
 **/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
