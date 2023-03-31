package core.processor.worker.model;

import java.io.File;
import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.file.read.FileReader;
import core.constant.Constant;
import core.processor.lib.tools.NameTools;
import core.processor.modelinterface.LayoutModelInterface;
import core.processor.worker.model.base.BaseModel;

public class LayoutModel extends BaseModel implements LayoutModelInterface {
    public LayoutModel(JavaInfo info) {
        super(info);
    }

    @Override
    public List<String> getTempLines() {
        String path = NameTools.getLayoutPath(Constant.DEFAULT_TEMP_LAYOUT);
        File file = new File(path);
        if (file.exists()) {
            return FileReader.from(file).readLine();
        }
        return super.getTempLines();
    }
}
/* model_temp_start
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ccc"
        android:fitsSystemWindows="true"
        android:orientation="vertical">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="50dp">

            <TextView
                android:id="@+id/titleTv"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center"
                android:padding="10dp"
                android:text="title"
                android:textColor="#000"
                android:textSize="14sp" />

            <TextView
                android:id="@+id/backBt"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:gravity="center"
                android:padding="10dp"
                android:text="返回"
                android:textColor="#000"
                android:textSize="14sp" />

            <TextView
                android:id="@+id/addBt"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_alignParentRight="true"
                android:gravity="center"
                android:padding="10dp"
                android:text="添加"
                android:textColor="#000"
                android:textSize="14sp" />
        </RelativeLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#fff"
            android:orientation="vertical">

        </LinearLayout>


    </LinearLayout>
</RelativeLayout>
model_temp_end */
