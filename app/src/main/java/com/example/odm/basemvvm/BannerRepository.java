package com.example.odm.basemvvm;

import androidx.lifecycle.MutableLiveData;

import com.example.odm.basemvvm.Base.BaseModel;
import com.example.odm.basemvvm.Entity.ResourceState;
import com.example.odm.basemvvm.Net.RetrofitManager;

import java.util.List;

/**
 * description: Banner Model层
 * author: ODM
 * date: 2019/10/26
 * @author ODM
 */
public class BannerRepository extends BaseModel {


    public MutableLiveData<ResourceState<List<BannerBean>>> getBannerList() {
        MutableLiveData<ResourceState<List<BannerBean>>> liveData = new MutableLiveData<>();
        return startObserve(RetrofitManager.getRetrofitManager().getApiService().getBanner(), liveData);
    }





}
