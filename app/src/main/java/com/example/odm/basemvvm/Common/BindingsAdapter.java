package com.example.odm.basemvvm.Common;

import android.app.Application;
import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.odm.basemvvm.utils.ContextFactory;

/**
 * @description: DataBinding辅助类
 * @author: ODM
 * @date: 2019/10/28
 */
public class BindingsAdapter {

    /**
     * EditText使用输入法右下角为搜索，点击搜索键后的操作：隐藏输入法且清空内容
     * @param editText
     * @param value 当前已输入内容
     */
    @BindingAdapter("afterSearch")
    public static void bindSearch(EditText editText , String value) {

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH && !"".equals(value)) {
                    //搜索内容非空且点击了搜索键后收起软键盘
                    InputMethodManager manager = ((InputMethodManager) ContextFactory.getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null) {
                        manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        //点击键盘的搜索键后，清空内容，放弃焦点
                        editText.clearFocus();
                        editText.setText("");
                    }
                }
                return true;
            }

        });
    }

    /**
     * 检查是否设置了新的值，防止重复赋值
     * @param view  TextView 的泛型子类对象
     * @param newValue 新的值
     */
    @BindingAdapter("checkNewStringValue")
    public static <T extends TextView>void checkNewValue(T  view , String newValue) {
        if(! view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }


}
