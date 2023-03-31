package core.lib4a.view.combine;

import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.tools.CountTool;
import core.lib4a.act.Destroys;
import core.lib4a.act.OnDestroy;

public class RadioGroup implements OnDestroy, View.OnClickListener {
    private boolean hasNull;
    private int selected = -1;
    private View[] bts;
    private int[] bgs;
    private List<OnSelectChange> onSelectChanges;
    private OnSetItem onSetItem;

    public RadioGroup() {

    }

    public static interface OnSelectChange {
        public void onChange(int selected);
    }

    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static RadioGroup obtain(Destroys destroys) {
        RadioGroup radioGroup = new RadioGroup();
        destroys.add(radioGroup);
        return radioGroup;
    }


    public RadioGroup setHasNull(boolean hasNull) {
        this.hasNull = hasNull;
        return this;
    }

    public RadioGroup setBgs(int... bgs) {
        this.bgs = bgs;
        initBgs();
        return this;
    }

    public RadioGroup setBts(View... bts) {
        this.bts = bts;
        for (int i = 0; i < bts.length; i++) {
            bts[i].setTag(R.id.tag_0, i);
            bts[i].setOnClickListener(this);
        }
        initBgs();
        return this;
    }

    public RadioGroup setBts(ViewGroup vp) {
        if (vp.getChildCount() > 0) {
            this.bts = new View[vp.getChildCount()];
            for (int i = 0; i < vp.getChildCount(); i++) {
                bts[i] = vp.getChildAt(i);
                bts[i].setTag(R.id.tag_0, i);
                bts[i].setOnClickListener(this);
            }
        }
        initBgs();
        return this;
    }

    private void initBgs() {
        if (bgs != null && bts != null && bgs.length == 2) {
            int[] ints = new int[bts.length << 1];
            for (int i = 0; i < ints.length; i += 2) {
                ints[i] = bgs[0];
                ints[i + 1] = bgs[1];
            }
            this.bgs = ints;
        }
    }

    public RadioGroup addOnSelectChange(OnSelectChange onSelectChange) {
        if (onSelectChanges == null) {
            onSelectChanges = new ArrayList<>();
        }
        onSelectChanges.add(onSelectChange);
        return this;
    }


    public RadioGroup setOnSetItem(OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public RadioGroup(Destroys destroys, boolean hasNull, int selected, int[] bgs, View... bts) {
        this(destroys, hasNull, selected, bgs, null, bts);
    }

    public RadioGroup(Destroys destroys, boolean hasNull, int selected, int[] bgs, OnSetItem onSelectChangeItem, View... bts) {
        destroys.add(this);
        this.selected = selected;
        this.hasNull = hasNull;
        this.onSetItem = onSelectChangeItem;
        this.bgs = bgs;
        this.bts = bts;
        if (bgs.length == 2) {
            int[] ints = new int[bts.length << 1];
            for (int i = 0; i < ints.length; i += 2) {
                ints[i] = bgs[0];
                ints[i + 1] = bgs[1];
            }
            this.bgs = ints;
        }

        for (int i = 0; i < bts.length; i++) {
            bts[i].setTag(R.id.tag_0, i);
            bts[i].setOnClickListener(this);
        }

        change();
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag(R.id.tag_0);
        setSelected(index);
    }


    public RadioGroup setSelected(int index) {
        if (index == selected) {
            if (hasNull) {
                setSelectedReal(-1);
            }
        } else {
            setSelectedReal(index);
        }
        return this;
    }

    private void setSelectedReal(int index) {
        this.selected = index;
        change();
        for (int i = 0; i < CountTool.count(onSelectChanges); i++) {
            onSelectChanges.get(i).onChange(this.selected);
        }
    }

    private void change() {
        for (int i = 0; i < bts.length; i++) {
            if (selected == i) {
                if (onSetItem != null) {
                    onSetItem.setSelected(bts[i]);
                } else {
                    bts[i].setBackgroundResource(bgs[i * 2 + 1]);
                }
            } else {
                if (onSetItem != null) {
                    onSetItem.setSelectno(bts[i]);
                } else {
                    bts[i].setBackgroundResource(bgs[i * 2]);
                }
            }
        }
    }


    public int getSelected() {
        return selected;
    }


    @Override
    public void destroy() {
        for (int i = 0; i < bts.length; i++) {
            bts[i].setOnClickListener(null);
            bts[i] = null;
        }
        bts = null;
        if (onSelectChanges != null) {
            onSelectChanges.clear();
            onSelectChanges = null;
        }
        onSetItem = null;
    }
}
