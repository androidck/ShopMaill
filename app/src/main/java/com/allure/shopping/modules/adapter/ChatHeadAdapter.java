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
import com.allure.shopping.modules.model.Customer;
import com.allure.shopping.modules.model.GuessLikeYou;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 头部适配器
 */
public class ChatHeadAdapter extends RecyclerView.Adapter<ChatHeadViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<Customer> mDatas;

    public ChatHeadAdapter(Context context,List<Customer> mDatas){
        this.context=context;
        this.mDatas=mDatas;
        if (this.mDatas==null){
            this.mDatas=new ArrayList<>();
        }
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatHeadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_head,viewGroup,false);
        ChatHeadViewHolder viewHolder=new ChatHeadViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatHeadViewHolder holder, int i) {
        holder.tvTitle.setText(mDatas.get(i).getName());
        Glide.with(context).load(mDatas.get(i).getIcon()).error(R.mipmap.ic_launcher).into(holder.headImg);
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
class ChatHeadViewHolder extends RecyclerView.ViewHolder{

    ImageView headImg;
    TextView tvTitle;

    public ChatHeadViewHolder(@NonNull View itemView) {
        super(itemView);
        headImg=itemView.findViewById(R.id.head_img);
        tvTitle=itemView.findViewById(R.id.head_title);
    }
}
