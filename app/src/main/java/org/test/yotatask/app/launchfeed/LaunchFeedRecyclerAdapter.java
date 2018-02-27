package org.test.yotatask.app.launchfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.test.yotatask.base.BaseRecyclerAdapter;
import org.test.yotatask.R;
import org.test.yotatask.base.bitmaps.BitmapTransformer;
import org.test.yotatask.base.bitmaps.CircularBitmapTransformer;
import org.test.yotatask.base.bitmaps.DefaultBitmapTransformer;
import org.test.yotatask.base.bitmaps.ImageLoadManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tetawex on 15.02.2018.
 */

public class LaunchFeedRecyclerAdapter extends BaseRecyclerAdapter<LaunchData, LaunchFeedRecyclerAdapter.ViewHolder> {
    private BitmapTransformer bitmapTransformer;
    private SimpleDateFormat dateFormatter;
    private ImageLoadManager imageLoadManager;

    public interface LaunchClickListener {
        void onClick(String actionUrl);
    }

    private LaunchClickListener clickListener;

    public void clearImageLoader() {
        imageLoadManager.clear();
    }

    public LaunchFeedRecyclerAdapter(Context context) {
        super(context);
        bitmapTransformer = new DefaultBitmapTransformer();
        imageLoadManager = new ImageLoadManager();
        dateFormatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        clickListener = new LaunchClickListener() {
            @Override
            public void onClick(String actionUrl) {

            }
        };
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycleritem_launch;
    }

    @Override
    public void bindSingleItem(ViewHolder viewHolder, final LaunchData item) {
        viewHolder.tvTitle.setText(item.getRocketName());
        if ("null".equals(item.getDetails()))
            viewHolder.tvDetails.setText(R.string.err_no_desc);
        else
            viewHolder.tvDetails.setText(item.getDetails());

        viewHolder.tvDate.setText(dateFormatter.format(new Date(item.getLaunchDate() * 1000L)));

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(item.getArticleUrl());
            }
        });

        //Clear iv before loading
        viewHolder.ivMissionPatch.setImageResource(R.drawable.shape_circle);
        //Load image into iv
        imageLoadManager.loadImageIntoImageView(
                viewHolder.ivMissionPatch,
                bitmapTransformer,
                item.getPatchUrl());
    }

    @Override
    public ViewHolder createVH(View view) {
        return new ViewHolder(view);
    }

    public void setClickListener(LaunchClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;

        TextView tvTitle;
        TextView tvDetails;
        TextView tvDate;

        ImageView ivMissionPatch;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetails = itemView.findViewById(R.id.tv_details);
            tvDate = itemView.findViewById(R.id.tv_launch_date);
            ivMissionPatch = itemView.findViewById(R.id.iv_mission_patch);
        }
    }
}
