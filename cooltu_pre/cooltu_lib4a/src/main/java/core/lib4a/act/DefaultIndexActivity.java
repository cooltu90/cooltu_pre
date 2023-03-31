package core.lib4a.act;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.MapEach;
import core.lib4a.tools.ViewTool;

public abstract class DefaultIndexActivity extends CoreIndexActivity {

    private int[] selectedIcons;
    private int[] unselectIcons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedIcons = getSelectedIcons();
        unselectIcons = getUnselectIcons();
    }

    protected abstract int[] getSelectedIcons();

    protected abstract int[] getUnselectIcons();

    @Override
    protected void createClick(int position, Integer id) {
        View view = btMap.get(id);
        view.setTag(position);
    }

    @Override
    protected void changeBt(int currentTag) {
        Ts.ls(btMap, new MapEach<Integer, View>() {
            @Override
            public boolean each(int position, Integer integer, View view) {
                ViewGroup group = (ViewGroup) view;
                ImageView iv = (ImageView) group.getChildAt(0);
                TextView tv = (TextView) group.getChildAt(1);
                int p = (int) view.getTag();
                if (view.getId() == currentTag) {
                    ViewTool.setTextColor(tv, getBarTextSelectedColor());
                    iv.setBackgroundResource(selectedIcons[p]);
                } else {
                    ViewTool.setTextColor(tv, getBarTextUnselectColor());
                    iv.setBackgroundResource(unselectIcons[p]);
                }
                return false;
            }
        });
    }

    protected abstract int getBarTextUnselectColor();

    protected abstract int getBarTextSelectedColor();
}
