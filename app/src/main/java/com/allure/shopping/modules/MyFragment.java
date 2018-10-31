package com.allure.shopping.modules;

import com.allure.shopping.R;
import com.allure.shopping.common.fragemnt.CommonLazyFragment;

/**
 * 我的
 */
public class MyFragment extends CommonLazyFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.my_layout;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    public static MyFragment newInstance(){
        return new MyFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
