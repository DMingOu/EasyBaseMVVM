package com.example.odm.basemvvm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.odm.basemvvm.Base.BaseViewModel;
import com.example.odm.basemvvm.Entity.ResourceState;

import java.util.List;

/**
 * description: Banner ViewModel 层
 * author: ODM
 * date: 2019/10/26
 */
public class BannerViewModel extends BaseViewModel<BannerRepository> {

    public BannerViewModel(Application application){
        super(application);
    }

    @Override
    protected BannerRepository createRepository() {
        return new BannerRepository();
    }

    public LiveData<ResourceState<List<BannerBean>>> getBanner() {
        return getRepository().getBannerList();
    }
}
