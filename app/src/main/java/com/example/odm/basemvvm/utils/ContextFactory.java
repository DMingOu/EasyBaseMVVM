package com.example.odm.basemvvm.utils;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * @description: 全局获取Context工厂 ,通过反射获取
 * @author: ODM
 * @date: 2019/10/28
 */
public class ContextFactory {
    @NonNull

    public static Application getContext() {
        return CONTEXT;
    }

    @SuppressLint("StaticFieldLeak")
    private static final Application CONTEXT;

    static {
        try {
            Object activityThread = getActivityThread();
            Object app = activityThread.getClass().getMethod("getApplication")
                                                 .invoke(activityThread);
            CONTEXT = (Application) app;
        } catch (Throwable e) {
            throw new IllegalStateException("Can not access Application context by magic code, boom!", e);
        }
    }

    private static Object getActivityThread() {
        Object activityThread = null;
        try {

            @SuppressLint("PrivateApi")
            Method method = Class.forName("android.app.ActivityThread")
                    .getMethod("currentActivityThread");

            method.setAccessible(true);
            activityThread = method.invoke(null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return activityThread;

    }
}
