package com.dali.admin.livestreaming.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter封装类
 * Created by dali on 2017/5/16.
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{

    protected int mLayoutId;//布局id
    protected List<T> mDatas;//数据源
    protected Context mContext;//上下文
    private LayoutInflater mInflater;

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        //将监听传到RecyclerViewHolder中
        return new RecyclerViewHolder(itemView);
    }

    public RecyclerViewAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        bindData(holder, mDatas.get(position), position);
    }



    /**
     * 把必要参数传进去，让每个 Adapter 去设置具体值
     * @param holder   RecyclerViewHolder
     * @param t        数据
     * @param position 当前位置
     */
    protected abstract void bindData(RecyclerViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //获取单条数据
    public T getItem(int position) {
        if (position >= mDatas.size())
            return null;
        return mDatas.get(position);
    }

    public void clearData() {
        mDatas.clear();
        notifyItemRangeRemoved(0, mDatas.size());
    }

    public void addData(ArrayList<T> datas) {
        mDatas.addAll(datas);
        notifyItemRangeChanged(0,mDatas.size());
    }

    public void addData(int position, ArrayList<T> datas) {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

}
