package com.example.odm.basemvvm.Base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * description: ViewModel层基类
 * author: ODM
 * date: 2019/10/26
 */
public abstract class BaseViewModel<T extends BaseModel> extends AndroidViewModel {

    //退出页面，取消请求
    public CompositeDisposable compositeDisposable;
    private T repository;
    private ArrayList<String> onNetTags;

    protected abstract T createRepository();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.repository = createRepository();
        compositeDisposable = new CompositeDisposable();
        onNetTags = new ArrayList<>();
    }

    public void setObjectLifecycleTransformer(LifecycleTransformer objectLifecycleTransformer) {
        repository.setObjectLifecycleTransformer(objectLifecycleTransformer);
        repository.setCompositeDisposable(compositeDisposable);
        repository.setOnNetTags(onNetTags);
    }

    public T getRepository() {
        return repository;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        //销毁后，取消当前页所有在执行的网络请求。
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}