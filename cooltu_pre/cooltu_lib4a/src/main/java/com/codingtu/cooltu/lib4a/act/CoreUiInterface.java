package com.codingtu.cooltu.lib4a.act;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;

public interface CoreUiInterface extends Destroys {

    public void toast(String msg);

    public void addWhenKeyDown(WhenKeyDown whenKeyDown);

    public void removeWhenKeyDown(WhenKeyDown whenKeyDown);

    public void addPermissionBack(PermissionBack permissionBack);

    public void removePermissionBack(PermissionBack permissionBack);

    public void addOnActBack(OnActBack back);

    public void removeOnActBack(OnActBack back);
}
