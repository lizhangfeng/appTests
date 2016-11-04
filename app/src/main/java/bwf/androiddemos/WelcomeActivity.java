package bwf.androiddemos;

import android.view.View;

import bwf.androiddemos.base.BaseActivity;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void beforeInitView() {
        useDefaultTitleBarColor = false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
