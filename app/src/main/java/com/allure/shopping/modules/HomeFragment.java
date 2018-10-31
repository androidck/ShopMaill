package com.allure.shopping.modules;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allure.shopping.R;
import com.allure.shopping.common.fragemnt.CommonLazyFragment;
import com.allure.shopping.common.okhttp.OkHttp;
import com.allure.shopping.modules.childfragment.ChildFragment;
import com.allure.shopping.modules.childfragment.RecommendFragment;
import com.allure.shopping.modules.model.CatList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 首页
 */
public class HomeFragment extends CommonLazyFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;



    @Override
    protected int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        OkHttp.getAsync("http://api.taojiji.com/api.php?a=homeInfo&m=Home", new OkHttp.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("catList");
                MyPageAdapter adapter = new MyPageAdapter(getChildFragmentManager());
                tabLayout.addTab(tabLayout.newTab().setText("推荐"));
                adapter.addFragment(RecommendFragment.newInstance("888"),"推荐");
                for (JsonElement json:jsonArray){
                    CatList catList=gson.fromJson(json,CatList.class);
                    tabLayout.addTab(tabLayout.newTab().setText(catList.getCatName()));
                    adapter.addFragment(ChildFragment.newInstance(catList.getCatId()),catList.getCatName());
                }
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private class MyPageAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragment=new ArrayList<>();
        private final List<String> mFragmentTitle=new ArrayList<>();

        public void addFragment(Fragment fragment,String title){
            mFragment.add(fragment);
            mFragmentTitle.add(title);
        }

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitle.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    }
}
