package com.example.odm.basemvvm.Base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.example.odm.basemvvm.Entity.ResourceState;
import com.example.odm.basemvvm.R;
import com.google.gson.JsonSyntaxException;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * description: View层 Activity基类
 * author: ODM
 * date: 2019/10/26
 */
public abstract class BaseActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends RxFragmentActivity {

    //获取当前activity布局文件
    protected abstract int getContentViewId();

    //处理逻辑业务
    protected abstract void init();


    protected VM mViewModel;
    protected VDB binding;


//    不同项目使用不同的进度条用来显示
//    private CustomProgress dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getContentViewId());
        binding.setLifecycleOwner(this);
        createViewModel();
        init();

    }

    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) ViewModelProviders.of(this).get(modelClass);
            mViewModel.setObjectLifecycleTransformer(bindLifecycle());
        }
    }


    public LifecycleTransformer bindLifecycle() {
        LifecycleTransformer objectLifecycleTransformer = bindToLifecycle();
        return objectLifecycleTransformer;
    }

    public Context getContext() {
        return this;
    }

    /**
     * View层继承后,根据实际需求来重写下面的方法,注意判断是否需要super
     * @param <T>
     */
    public abstract class OnCallback<T> implements ResourceState.OnHandleCallback<T> {
        @Override
        public void onLoading(String msg) {
//            if (dialog == null) {
//                dialog = CustomProgress.show(BaseActivity.this, "", true, null);
//            }
//
//            if (!TextUtils.isEmpty(msg)) {
//                dialog.setMessage(msg);
//            }
//
//            if (!dialog.isShowing()) {
//                dialog.show();
//            }
        }

        /**
         * 错误回调处理,可根据不同种类错误弹出通知
         *
         * @param throwable
         */
        @Override
        public void onError(Throwable throwable) {

        }

        /**
         * 失败回调处理
         *
         * @param msg
         */
        @Override
        public void onFailure(String msg) {

        }

        /**
         * 成功回调处理
         */
        @Override
        public void onCompleted() {
//            if (dialog != null && dialog.isShowing()) {
//                dialog.dismiss();
//            }
        }

        /**
         * 进度条
         *
         * @param percent
         * @param total
         */
        @Override
        public void onProgress(int percent, long total) {

        }


    }
}