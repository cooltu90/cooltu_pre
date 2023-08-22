package com.codingtu.cooltu.lib4a.act;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

public abstract class CoreTagActivity extends CoreActivity {

    private View[] bts;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateComplete() {
        super.onCreateComplete();
        bts = getBts();

        ViewPager2 vp = getViewPager();
        vp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return CoreTagActivity.this.createFragment(position);
            }

            @Override
            public int getItemCount() {
                return CoreTagActivity.this.getItemCount();
            }
        });
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (radioGroup != null) {
                    if (position != radioGroup.getSelected()) {
                        radioGroup.setSelected(position);
                    }
                }
            }
        });


        Ts.ls(bts, new BaseTs.EachTs<View>() {
            @Override
            public boolean each(int position, View view) {
                view.setTag(R.id.tag_0, position);
                return false;
            }
        });
        radioGroup = RadioGroup.obtain(this).setBts(bts)
                .setOnSetItem(new RadioGroup.OnSetItem() {
                    @Override
                    public void setSelected(View view) {
                        onSelected((Integer) view.getTag(R.id.tag_0), view);
                    }

                    @Override
                    public void setSelectno(View view) {
                        onSelectno((Integer) view.getTag(R.id.tag_0), view);
                    }
                }).addOnSelectChange(new RadioGroup.OnSelectChange() {
                    @Override
                    public void onChange(int i) {
                        if (vp.getCurrentItem() != i) {
                            vp.setCurrentItem(i);
                        }
                    }
                }).setSelected(getDefaultSelected());
    }

    protected int getDefaultSelected() {
        return 0;
    }

    protected int getItemCount() {
        return CountTool.count(bts);
    }

    protected abstract void onSelectno(int position, View view);

    protected abstract void onSelected(int position, View view);

    protected abstract View[] getBts();

    protected abstract Fragment createFragment(int position);

    protected abstract ViewPager2 getViewPager();
}
