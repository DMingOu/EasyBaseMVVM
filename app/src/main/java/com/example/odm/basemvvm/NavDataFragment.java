package com.example.odm.basemvvm;

import android.view.View;

import com.example.odm.basemvvm.Base.BaseFragment;
import com.example.odm.basemvvm.databinding.ActivityNavDataBinding;
import com.example.odm.basemvvm.databinding.FragmentNavDataBinding;
import com.example.odm.basemvvm.utils.LogUtils;

import java.util.List;

/**
 * description: 导航数据Fragment
 * author: ODM
 * date: 2019/10/26
 */
public class NavDataFragment extends BaseFragment<NavDataViewModel , FragmentNavDataBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_nav_data;
    }

    @Override
    protected void init() {
        binding.buttonNav.setOnClickListener(v -> {
            LogUtils.e("点击了按钮获取导航数据");
            mViewModel.getNavData().observe(this , listResourceState -> listResourceState.handler(new OnCallback<List<NavDataBean>>() {
                        @Override
                        public void onSuccess(List<NavDataBean> data) {
                            binding.tvNav.setText(data.get(0).getArticles().get(0).getChapterName());
                        }

                        @Override
                        public void onLoading(String msg) {
                            binding.tvNav.setText("正在加载导航数据......");
                        }
                    })
            );
        });
    }




}
