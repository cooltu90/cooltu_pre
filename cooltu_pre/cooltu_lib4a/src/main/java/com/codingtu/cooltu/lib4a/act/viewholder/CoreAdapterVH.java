package com.codingtu.cooltu.lib4a.act.viewholder;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.codingtu.cooltu.lib4a.tools.InflateTool;

public class CoreAdapterVH extends RecyclerView.ViewHolder {

    public CoreAdapterVH(int layoutId, ViewGroup parent) {
        super(InflateTool.inflate(layoutId, parent));
    }
}
