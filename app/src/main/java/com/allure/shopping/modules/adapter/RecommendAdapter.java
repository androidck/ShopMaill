package com.allure.shopping.modules.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allure.shopping.R;
import com.allure.shopping.modules.model.GuessLikeYou;
import com.allure.shopping.modules.model.Recommend;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐列表适配器
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<Recommend> mDatas;

    public RecommendAdapter(Context context,List<Recommend> mDatas){
        this.context=context;
        this.mDatas=mDatas;
        if (this.mDatas==null){
            this.mDatas=new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_recommend,viewGroup,false);
        RecommendViewHolder holder=new RecommendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecommendViewHolder holder, int i) {
        holder.tv_price.setText(mDatas.get(i).getPrice());
        holder.tv_title.setText(mDatas.get(i).getTitle());
        holder.tv_saleNum.setText(mDatas.get(i).getSaleNum());
        Glide.with(context).load(mDatas.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(holder.img_imgUrl);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemView.getBackground() == null) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = holder.itemView.getContext().getTheme();
                    int top = holder.itemView.getPaddingTop();
                    int bottom = holder.itemView.getPaddingBottom();
                    int left = holder.itemView.getPaddingLeft();
                    int right = holder.itemView.getPaddingRight();
                    if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                        holder.itemView.setBackgroundResource(typedValue.resourceId);
                    }
                    holder.itemView.setPadding(left, top, right, bottom);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class RecommendViewHolder extends RecyclerView.ViewHolder{

    TextView tv_title,tv_price,tv_saleNum;
    ImageView img_imgUrl;
    public RecommendViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title=itemView.findViewById(R.id.tv_title);
        tv_price=itemView.findViewById(R.id.tv_price);
        tv_saleNum=itemView.findViewById(R.id.tv_saleNum);
        img_imgUrl=itemView.findViewById(R.id.img_imgUrl);
    }
}
