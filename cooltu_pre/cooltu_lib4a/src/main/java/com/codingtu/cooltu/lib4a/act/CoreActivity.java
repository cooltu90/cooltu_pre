package com.codingtu.cooltu.lib4a.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.lib4a.bus.Bus;
import com.codingtu.cooltu.lib4a.bus.BusStation;
import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4a.tools.ActTool;
import com.codingtu.cooltu.lib4a.tools.ScreenAdaptationTool;
import com.codingtu.cooltu.lib4a.tools.StatusBarTool;
import com.codingtu.cooltu.lib4a.tools.ToastTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class CoreActivity extends AppCompatActivity implements CoreUiInterface {

    protected ViewGroup rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptationTool.setCustomDensity(this);
        initStatusBar();
        ActivityManager.getInstance().add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyAll();
        removeBuses();
        ActivityManager.getInstance().remove(this);
    }

    protected void initStatusBar() {
        StatusBarTool.translucentAndDark(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        rootView = ViewTool.getRootViewGroup(this);
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onViewInitComplete();
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    protected void onViewInitComplete() {

    }

    protected void onCreateComplete() {

    }

    protected void forbidKeyBack() {
        addWhenKeyDown(new WhenBackKeyDown() {
            @Override
            public boolean onBack(KeyEvent event) {
                return true;
            }
        });
    }

    /********************************
     *
     * 对finish方法做扩展
     *
     ********************************/
    @Override
    public void finish() {
        beforeFinish();
        super.finish();
        setFinishAnimation();
        afterFinish();
    }

    protected void finishToNewPage() {
        beforeFinish();
        super.finish();
        afterFinish();
    }

    //finish之后调用
    protected void afterFinish() {
    }

    //设置finish动画
    protected void setFinishAnimation() {
        ActTool.actRightOut(this);
    }

    //finish之前调用
    protected void beforeFinish() {
    }

    /************************************************
     *
     * setResultOk
     *
     ************************************************/
    protected void setResultOk() {
        setResult(RESULT_OK);
    }

    protected void setResultOk(Intent data) {
        setResult(RESULT_OK, data);
    }

    /**********************************************
     *
     * getThis
     *
     **********************************************/
    protected Activity getThis() {
        return this;
    }

    /**********************************************
     *
     * toast
     *
     **********************************************/
    public void toast(String str) {
        ToastTool.toast(str);
    }

    /************************************************
     *
     *
     *
     ************************************************/

    protected List<WhenKeyDown> whenKeyDowns;

    protected List<WhenKeyDown> getWhenKeyDowns() {
        if (whenKeyDowns == null)
            whenKeyDowns = new ArrayList<WhenKeyDown>();
        return whenKeyDowns;
    }

    public void addWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().add(whenKeyDown);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean[] b = {false};
        List<WhenKeyDown> whenKeyDowns = getWhenKeyDowns();
        Ts.ls(whenKeyDowns, new BaseTs.EachTs<WhenKeyDown>() {
            @Override
            public boolean each(int position, WhenKeyDown whenKeyDown) {
                if (whenKeyDown.onKeyDown(keyCode, event)) {
                    b[0] = true;
                }
                return false;
            }
        });
        return b[0] ? b[0] : super.onKeyDown(keyCode, event);
    }

    @Override
    public void removeWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().remove(whenKeyDown);
    }

    /************************************************
     *
     *
     *
     ************************************************/

    protected List<PermissionBack> permissionHelpers;

    public List<PermissionBack> getPermissionHelpers() {
        if (permissionHelpers == null)
            permissionHelpers = new ArrayList<PermissionBack>();
        return permissionHelpers;
    }

    @Override
    public void addPermissionBack(PermissionBack back) {
        getPermissionHelpers().add(back);

    }

    @Override
    public void removePermissionBack(PermissionBack back) {
        getPermissionHelpers().remove(back);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onRequestPermissionsResultInCore(requestCode, permissions, grantResults);
    }

    public void onRequestPermissionsResultInCore(int requestCode, String[] permissions,
                                                 int[] grantResults) {
        Ts.ls(getPermissionHelpers(), new BaseTs.EachTs<PermissionBack>() {
            @Override
            public boolean each(int position, PermissionBack helper) {
                helper.back(requestCode, permissions, grantResults);
                return false;
            }
        });
    }

    /************************************************
     *
     *
     *
     ************************************************/
    protected List<OnActBack> onActBacks;

    public List<OnActBack> getOnActBacks() {
        if (onActBacks == null)
            onActBacks = new ArrayList<OnActBack>();
        return onActBacks;
    }

    @Override
    public void addOnActBack(OnActBack onActBack) {
        getOnActBacks().add(onActBack);
    }

    @Override
    public void removeOnActBack(OnActBack onActBack) {
        getOnActBacks().remove(onActBack);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Ts.ls(getOnActBacks(), new BaseTs.EachTs<OnActBack>() {
            @Override
            public boolean each(int position, OnActBack back) {
                back.onActivityResult(requestCode, resultCode, data);
                return false;
            }
        });
    }

    /************************************************
     *
     *
     *
     ************************************************/
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    @Override
    public void add(OnDestroy onDestroy) {
        getOnDestroys().add(onDestroy);
    }

    @Override
    public void destroyAll() {
        Ts.ls(getOnDestroys(), new BaseTs.EachTs<OnDestroy>() {
            @Override
            public boolean each(int position, OnDestroy onDestroy) {
                onDestroy.destroy();
                return false;
            }
        });
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<Bus> busMap = new ArrayList<>();

    protected void addBus(Bus bus) {
        busMap.add(bus);
        BusStation.add(bus);
    }

    private void removeBuses() {
        Ts.ls(busMap, new BaseTs.EachTs<Bus>() {
            @Override
            public boolean each(int position, Bus bus) {
                BusStation.remove(bus);
                return false;
            }
        });
        busMap.clear();
        busMap = null;
    }


}
