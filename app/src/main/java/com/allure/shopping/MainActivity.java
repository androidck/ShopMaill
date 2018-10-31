package com.allure.shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.allure.shopping.common.activity.CommonActivity;
import com.allure.shopping.modules.adapter.HomeViewPagerAdapter;
import com.hjq.baselibrary.widget.NoScrollViewPager;

import butterknife.BindView;

public class MainActivity extends CommonActivity implements ViewPager.OnPageChangeListener,BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.vp_home_pager)
    NoScrollViewPager vpHomePager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView bvHomeNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        vpHomePager.addOnPageChangeListener(this);
        bvHomeNavigation.setItemIconTintList(null);
        bvHomeNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        vpHomePager.setAdapter(new HomeViewPagerAdapter(this));
    }


    /**
     *
     * @param i
     * @param v
     * @param i1
     */
    @Override
    public void onPageScrolled(int i, float v, int i1) { }

    @Override
    public void onPageSelected(int i) {
            switch (i){
                case 0:
                    bvHomeNavigation.setSelectedItemId(R.id.menu_home);
                    break;
                case 1:
                    bvHomeNavigation.setSelectedItemId(R.id.menu_chat);
                    break;
                case 2:
                    bvHomeNavigation.setSelectedItemId(R.id.menu_me);
                    break;
            }
    }

    @Override
    public void onPageScrollStateChanged(int i) { }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vpHomePager.removeOnPageChangeListener(this);
        vpHomePager.setAdapter(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                vpHomePager.setCurrentItem(0);
                return true;
            case R.id.menu_chat:
                vpHomePager.setCurrentItem(1);
                return true;
            case R.id.menu_me:
                vpHomePager.setCurrentItem(2);
                return true;

        }
        return false;
    }
}
