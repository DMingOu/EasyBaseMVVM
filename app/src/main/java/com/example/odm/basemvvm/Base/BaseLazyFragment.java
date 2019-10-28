package com.example.odm.basemvvm.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.example.odm.basemvvm.Entity.ResourceState;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @description: 懒加载Fragment基类
 * @author: ODM
 * @date: 2019/10/28
 */
public abstract class BaseLazyFragment <VM extends BaseViewModel, VDB extends ViewDataBinding> extends RxFragment {


    //获取当前activity布局文件
    protected abstract int getContentViewId();

    //处理逻辑业务
    protected abstract void init();


    protected VM mViewModel;
    protected VDB binding;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUiVisible;

//    //不同项目使用不同的进度条用来显示
//    private CustomProgress dialog;


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater ,getContentViewId() ,container ,false);
        binding.setLifecycleOwner(this);
        createViewModel();
        init();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isUiVisible = false;
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据isUiVisible
        if (isViewCreated && isUiVisible) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载，若在懒加载数据将 isViewCreate变量设置为false则无法变为true
//            isViewCreated = false;
            isUiVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isUiVisible = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUiVisible = true;
            lazyLoad();
        }else{
            isUiVisible = false;
        }
    }

    /**
     * 懒加载后(页面可见时)才会执行的方法
     */
    protected abstract void lazyLoadData();



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

    /**
     * View层继承后,根据实际需求来重写下面的方法,注意判断是否需要super
     * @param <T>
     */
    public abstract class OnCallback<T> implements ResourceState.OnResourceStateHandleCallback<T> {
        @Override
        public void onLoading(String msg) {

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
