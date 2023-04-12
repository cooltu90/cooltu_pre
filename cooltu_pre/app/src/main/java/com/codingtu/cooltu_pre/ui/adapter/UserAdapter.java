package com.codingtu.cooltu_pre.ui.adapter;

import com.codingtu.cooltu_pre.R;
import java.lang.String;

import com.codingtu.cooltu.lib4a.act.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;
import core.vh.UserVH;

@VH(R.layout.item_user)
public abstract class UserAdapter extends CoreMoreListAdapter<UserVH, String> {
    @Override
    public void onBindVH(UserVH vh, int position, String t) {

    }
}
