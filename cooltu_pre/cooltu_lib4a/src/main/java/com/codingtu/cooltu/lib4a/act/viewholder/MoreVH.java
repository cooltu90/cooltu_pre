package com.codingtu.cooltu.lib4a.act.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;

public class MoreVH extends CoreAdapterVH {

    public TextView moreTv;

    public MoreVH(ViewGroup parent) {
        super(R.layout.item_more, parent);
        this.moreTv = itemView.findViewById(R.id.moreTv);
    }
}
