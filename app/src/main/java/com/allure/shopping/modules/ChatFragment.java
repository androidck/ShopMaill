package com.allure.shopping.modules;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allure.shopping.R;
import com.allure.shopping.common.fragemnt.CommonLazyFragment;
import com.allure.shopping.common.okhttp.OkHttp;
import com.allure.shopping.common.view.MyItemDecoration;
import com.allure.shopping.modules.adapter.ChatAdapter;
import com.allure.shopping.modules.adapter.ChatHeadAdapter;
import com.allure.shopping.modules.model.Customer;
import com.allure.shopping.modules.model.GuessLikeYou;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 消息
 */
public class ChatFragment extends CommonLazyFragment {
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    List<GuessLikeYou> list=new ArrayList<>();
    List<Customer> dataList=new ArrayList<>();
    ChatAdapter adapter;
    ChatHeadAdapter headAdapter;

    int page=1;

    @Override
    protected int getLayoutId() {
        return R.layout.chat_layout;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        //添加头view
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.chat_head,null);


        //初始化顶部recyclerView
        SwipeMenuRecyclerView recyclerViews=view.findViewById(R.id.head_recyclerView);
        headAdapter=new ChatHeadAdapter(getActivity(),dataList);
        recyclerViews.setAdapter(headAdapter);
        LinearLayoutManager linerLayoutManager=  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViews.setLayoutManager(linerLayoutManager);
        recyclerView.addHeaderView(view);

        recyclerView.setLongPressDragEnabled(true);

        adapter= new ChatAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return true;
            }
        };

        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        recyclerView.setLayoutManager(layoutManager);
        refreshLayout.setEnableLoadMore(true);
        //关闭下拉刷新
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getData(page);
                        refreshLayout.finishLoadMore();
                    }
                },100);
            }
        });
    }

    @Override
    protected void initData() {
        getData(1);
        getCustomer();
    }

    //请求数据
    public void getData(int pages){
        OkHttp.getAsync("http://api.taojiji.com/api.php?m=Message&a=msgRecommend&page="+pages+"&user_id=35109788818823835&token=d14cf15e97b8205a&g=Api2_8_1&uuid=8d74a4c1-bd22-48eb-babe-eb00950e93bf", new OkHttp.DataCallBack() {
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

    //获取客服数据
    public void getCustomer(){
        OkHttp.getAsync("http://api.taojiji.com/api.php?m=Message&a=SystemMsgCount&user_id=35109788818823835&token=d14cf15e97b8205a&g=Api2_8_1&uuid=8d74a4c1-bd22-48eb-babe-eb00950e93bf", new OkHttp.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                for (JsonElement json:jsonArray){
                    Customer customer=gson.fromJson(json,Customer.class);
                    dataList.add(new Customer(customer.getIcon(),customer.getName(),customer.getShopId(),customer.getNumber(),customer.getType()));
                }
                headAdapter.notifyDataSetChanged();
            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });
    }

    public static ChatFragment newInstance() {
        return new ChatFragment();
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
