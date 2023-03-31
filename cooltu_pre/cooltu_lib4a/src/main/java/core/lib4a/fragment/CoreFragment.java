package core.lib4a.fragment;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.lib4a.act.CoreUiInterface;
import core.lib4a.act.OnActBack;
import core.lib4a.act.OnDestroy;
import core.lib4a.act.WhenKeyDown;
import core.lib4a.bus.Bus;
import core.lib4a.bus.BusStation;
import core.lib4a.permission.PermissionBack;
import core.lib4a.tools.ToastTool;

public class CoreFragment extends Fragment implements CoreUiInterface {

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeBuses();
    }

    @Override
    public void toast(String msg) {
        ToastTool.toast(msg);
    }

    @Override
    public void addWhenKeyDown(WhenKeyDown whenKeyDown) {

    }

    @Override
    public void removeWhenKeyDown(WhenKeyDown whenKeyDown) {

    }

    @Override
    public void addPermissionBack(PermissionBack permissionBack) {

    }

    @Override
    public void removePermissionBack(PermissionBack permissionBack) {

    }

    @Override
    public void addOnActBack(OnActBack back) {

    }

    @Override
    public void removeOnActBack(OnActBack back) {

    }

    @Override
    public void add(OnDestroy onDestroy) {

    }

    @Override
    public void destroyAll() {

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
        Ts.ls(busMap, new Each<Bus>() {
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
