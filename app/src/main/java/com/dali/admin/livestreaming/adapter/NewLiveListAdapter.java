package com.dali.admin.livestreaming.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dali.admin.livestreaming.R;
import com.dali.admin.livestreaming.base.RecyclerViewAdapter;
import com.dali.admin.livestreaming.base.RecyclerViewHolder;
import com.dali.admin.livestreaming.model.LiveInfo;
import com.dali.admin.livestreaming.utils.OtherUtils;

import java.util.List;

/**
 * 直播列表展示
 * Created by dali on 2017/5/16.
 */

public class NewLiveListAdapter extends RecyclerViewAdapter<LiveInfo>{

    private OnItemClickListener mOnItemClickListener = null;

    public NewLiveListAdapter(Context context, List<LiveInfo> datas, OnItemClickListener listener) {
        super(context, R.layout.list_live_item, datas);
        this.mOnItemClickListener = listener;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, final LiveInfo liveInfo, final int position) {
        //直播标题
        holder.setText(R.id.live_title, liveInfo.getTitle())
                //主播昵称
                .setText(R.id.host_name, TextUtils.isEmpty(liveInfo.getUserInfo().getNickname()) ? OtherUtils.getLimitString(liveInfo.getUserInfo().getUserId(), 10) : OtherUtils.getLimitString(liveInfo.getUserInfo().getNickname(), 10))
                //直播观看人数
                .setText(R.id.live_members, liveInfo.getViewCount() + "")
                //直播点赞数
                .setText(R.id.praises, liveInfo.getLikeCount() + "")
                //主播地址
                .setText(R.id.live_lbs, TextUtils.isEmpty(liveInfo.getUserInfo().getLocation()) ? "不显示地理位置" : OtherUtils.getLimitString(liveInfo.getUserInfo().getLocation(), 9))
                //直播封面
                .setImagePath(R.id.cover, new RecyclerViewHolder.ImageLoder(liveInfo.getLiveCover()) {
                    @Override
                    public void loadImage(ImageView imageView, String path) {
                        Glide.with(mContext).load(path).placeholder(R.drawable.bg_dark).into(imageView);
                    }
                })
                //直播logo
                .setImageResource(R.id.live_logo, R.drawable.icon_live);
        //主播头像（圆角显示图片）
        OtherUtils.showPicWithUrl(mContext, (ImageView) holder.getView(R.id.avatar), liveInfo.getUserInfo().getHeadPic(), R.drawable.default_head);


        holder.getView(R.id.rv_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,position);
                Log.e("NewLiveListAdapter","position:"+position+"");
            }
        });
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
