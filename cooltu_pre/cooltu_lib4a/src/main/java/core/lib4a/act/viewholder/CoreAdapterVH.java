package core.lib4a.act.viewholder;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import core.lib4a.tools.InflateTool;

public class CoreAdapterVH extends RecyclerView.ViewHolder {

    public CoreAdapterVH(int layoutId, ViewGroup parent) {
        super(InflateTool.inflate(layoutId, parent));
    }
}
