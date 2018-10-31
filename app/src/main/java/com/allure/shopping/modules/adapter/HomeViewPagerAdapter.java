package com.allure.shopping.modules.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.allure.shopping.common.fragemnt.CommonLazyFragment;
import com.allure.shopping.modules.ChatFragment;
import com.allure.shopping.modules.HomeFragment;
import com.allure.shopping.modules.MyFragment;
import com.hjq.baselibrary.base.BaseFragmentPagerAdapter;


import java.util.List;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 主页界面 ViewPager + Fragment 适配器
 */
public final class HomeViewPagerAdapter extends BaseFragmentPagerAdapter<CommonLazyFragment> {

    public HomeViewPagerAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void init(FragmentManager fm, List<CommonLazyFragment> list) {
        list.add(HomeFragment.newInstance());
        list.add(ChatFragment.newInstance());
        list.add(MyFragment.newInstance());
    }
}