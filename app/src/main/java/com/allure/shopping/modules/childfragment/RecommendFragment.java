package com.allure.shopping.modules.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allure.shopping.R;
import com.allure.shopping.common.fragemnt.CommonLazyFragment;
import com.allure.shopping.common.okhttp.OkHttp;
import com.allure.shopping.modules.adapter.RecommendAdapter;
import com.allure.shopping.modules.model.BannerImg;
import com.allure.shopping.modules.model.Recommend;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 子类布局
 */
public class RecommendFragment extends CommonLazyFragment {

    Banner banner;

    Unbinder unbinder;

    List<String> imageUrl = new ArrayList<>();
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<Recommend> list=new ArrayList<>();
    RecommendAdapter adapter;

    int page=1;

    @Override
    protected int getLayoutId() {
        return R.layout.recommend_layout;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.recommend_banner,null);
        banner=view.findViewById(R.id.banner);
        recyclerView.addHeaderView(view);
        refreshLayout.setEnableRefresh(false);

        adapter=new RecommendAdapter(getActivity(),list);
        LinearLayoutManager linerLayoutManager=  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linerLayoutManager);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getRecommedGoodsList(page);
                        refreshLayout.finishLoadMore();
                    }
                },100);
            }
        });
    }

    @Override
    protected void initData() {
        //获取banner图片
        getBanner();
        getRecommedGoodsList(1);
    }


    //获取banner图
    private void getBanner(){
        OkHttp.getAsync("http://api.taojiji.com/api.php?a=home&m=Goods&user_id=35109788818823835&ag=Api2_8_2", new OkHttp.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("banners");
                for (JsonElement json : jsonArray) {
                    BannerImg banner = gson.fromJson(json, BannerImg.class);
                    imageUrl.add(banner.getBannerImg());
                }
                startBanner();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    //获取推荐商品列表
    private void getRecommedGoodsList(int nowpage){
        OkHttp.getAsync("http://api.taojiji.com/api.php?a=homeGoods&m=Goods&nowpage="+nowpage+"&user_id=35109788818823835&g=Api2_8_2", new OkHttp.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                for (JsonElement json:jsonArray){
                    Recommend recommend=gson.fromJson(json,Recommend.class);
                    list.add(new Recommend(recommend.getLinkId(),recommend.getTitle(),recommend.getImgUrl(),recommend.getHtmlImgUrl(),recommend.getPrice(),recommend.getSaleNum()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    //单利模式
    public static RecommendFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(bundle);
        return fragment;
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

    private void startBanner() {
        //设置banner样式(显示圆形指示器)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（指示器居右）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageUrl);
        //设置banner动画效果
        //banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    //图片加载器
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

}
