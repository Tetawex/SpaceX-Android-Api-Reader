package org.test.yotatask.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<T> data;
    protected Context context;

    public abstract int getLayoutId();

    public abstract void bindSingleItem(VH viewHolder, T item);

    public abstract VH createVH(View view);

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(this.getLayoutId(), parent, false);
        return this.createVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        this.bindSingleItem((VH) viewHolder, this.data.get(position));
    }

    public final List getData() {
        return this.data;
    }

    public void replaceData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    public void appendData(List<T> data) {
        this.data.addAll(data);
    }

    public final void replaceDataWithNotify(List<T> data) {
        this.replaceData(data);
        this.notifyDataSetChanged();
    }

    public final void appendDataWithNotify(List<T> data) {
        this.appendData(data);
        this.notifyDataSetChanged();
    }

    public final void clear() {
        this.data.clear();
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getItemCount() {
        return this.data.size();
    }

    public BaseRecyclerAdapter(Context context) {
        super();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
    }
}

