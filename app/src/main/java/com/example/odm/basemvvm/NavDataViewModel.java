package com.example.odm.basemvvm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.odm.basemvvm.Base.BaseViewModel;
import com.example.odm.basemvvm.Entity.ResourceState;

import java.util.List;

/**
 * description: 导航数据ViewModel
 * author: ODM
 * date: 2019/10/26
 */
public class NavDataViewModel extends BaseViewModel<NavDataRepository> {

    public NavDataViewModel(Application application){
        super(application);
    }

    @Override
    protected NavDataRepository createRepository() {
        return new NavDataRepository();
    }

    public LiveData<ResourceState<List<NavDataBean>>> getNavData() {
        return getRepository().getNavDataList();
    }



}
