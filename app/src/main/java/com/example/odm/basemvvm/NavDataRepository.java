package com.example.odm.basemvvm;

import androidx.lifecycle.MutableLiveData;

import com.example.odm.basemvvm.Base.BaseModel;
import com.example.odm.basemvvm.Entity.ResourceState;
import com.example.odm.basemvvm.Net.RetrofitManager;

import java.util.List;

/**
 * description: 导航数据Model层
 * author: ODM
 * date: 2019/10/26
 */
public class NavDataRepository extends BaseModel {

    public MutableLiveData<ResourceState<List<NavDataBean>>>  getNavDataList()  {
        MutableLiveData<ResourceState<List<NavDataBean>>>  liveData = new MutableLiveData<>();
        return  startObserve(RetrofitManager.getRetrofitManager().getApiService().getNavData()  , liveData);

    }
}
