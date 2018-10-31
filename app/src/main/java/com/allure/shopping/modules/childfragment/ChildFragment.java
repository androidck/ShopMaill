package com.allure.shopping.modules.childfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allure.shopping.R;
import com.allure.shopping.common.fragemnt.CommonLazyFragment;
import com.allure.shopping.common.okhttp.OkHttp;
import com.allure.shopping.common.view.MyItemDecoration;
import com.allure.shopping.modules.adapter.ChatAdapter;
import com.allure.shopping.modules.model.GuessLikeYou;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
public class ChildFragment extends CommonLazyFragment {
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    List<GuessLikeYou> list=new ArrayList<>();
    ChatAdapter adapter;

    int page=1;


    @Override
    protected int getLayoutId() {
        return R.layout.child_layout;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        adapter= new ChatAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return true;
            }
        };

        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        recyclerView.setLayoutManager(layoutManager);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        getGoodsList(1);
                        refreshLayout.finishRefresh();
                    }
                },100);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getGoodsList(page);
                        refreshLayout.finishLoadMore();
                    }
                },100);
            }
        });
    }

    @Override
    protected void initData() {
        getGoodsList(1);
    }

    //获取列表数据
    private void getGoodsList( int nowpage) {
        String type=this.getArguments().getString("type");
        OkHttp.getAsync("http://api.taojiji.com/api.php?a=goodsList&m=Home&cat_id=" + type + "&nowpage=" + nowpage + "&user_id=35109788818823835&g=Api2_8_2", new OkHttp.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("goodsList");
                for (JsonElement json:jsonArray){
                    GuessLikeYou guessLikeYou=gson.fromJson(json,GuessLikeYou.class);
                    list.add(new GuessLikeYou(guessLikeYou.getGoodsName(),guessLikeYou.getGoodsId(),guessLikeYou.getImgUrl(),guessLikeYou.getSaleNum(),guessLikeYou.getPrice()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    //单利模式
    public static ChildFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ChildFragment fragment = new ChildFragment();
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
}
