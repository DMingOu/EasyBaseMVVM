package com.example.odm.basemvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.odm.basemvvm.Base.BaseActivity;
import com.example.odm.basemvvm.databinding.ActivityBannerBinding;
import com.example.odm.basemvvm.utils.LogUtils;

import java.util.List;

public class BannerActivity extends BaseActivity<BannerViewModel , ActivityBannerBinding> implements View.OnClickListener {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void init() {
        binding.setOnclickListener(this);
    }



    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_banner:
                mViewModel.getBanner().observe(this, resource -> resource.handler(new OnCallback<List<BannerBean>>() {
                    @Override
                    public void onSuccess(List<BannerBean> data) {
                        System.out.println(data.get(0).getTitle());
                        updateBanner(data);
                    }

                    @Override
                    public void onLoading(String msg) {
                        binding.tvBanner.setText("正在获取数据......");
                    }
                }));
                break;

            default:break;
        }

    }


    private void updateBanner(List<BannerBean> data) {
        if (data == null || data.size() <= 0) {
            binding.tvBanner.setText("空数据");
            return;
        }
        binding.tvBanner.setText(data.get(0).getTitle());
    }
}
