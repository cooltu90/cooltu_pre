package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface LayoutModelInterface {

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        lines.add("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
        lines.add("    android:layout_width=\"match_parent\"");
        lines.add("    android:layout_height=\"match_parent\">");
        lines.add("");
        lines.add("    <LinearLayout");
        lines.add("        android:layout_width=\"match_parent\"");
        lines.add("        android:layout_height=\"match_parent\"");
        lines.add("        android:background=\"#ccc\"");
        lines.add("        android:fitsSystemWindows=\"true\"");
        lines.add("        android:orientation=\"vertical\">");
        lines.add("");
        lines.add("        <RelativeLayout");
        lines.add("            android:layout_width=\"match_parent\"");
        lines.add("            android:layout_height=\"50dp\">");
        lines.add("");
        lines.add("            <TextView");
        lines.add("                android:id=\"@+id/titleTv\"");
        lines.add("                android:layout_width=\"match_parent\"");
        lines.add("                android:layout_height=\"match_parent\"");
        lines.add("                android:gravity=\"center\"");
        lines.add("                android:padding=\"10dp\"");
        lines.add("                android:text=\"title\"");
        lines.add("                android:textColor=\"#000\"");
        lines.add("                android:textSize=\"14sp\" />");
        lines.add("");
        lines.add("            <TextView");
        lines.add("                android:id=\"@+id/backBt\"");
        lines.add("                android:layout_width=\"wrap_content\"");
        lines.add("                android:layout_height=\"match_parent\"");
        lines.add("                android:gravity=\"center\"");
        lines.add("                android:padding=\"10dp\"");
        lines.add("                android:text=\"返回\"");
        lines.add("                android:textColor=\"#000\"");
        lines.add("                android:textSize=\"14sp\" />");
        lines.add("");
        lines.add("            <TextView");
        lines.add("                android:id=\"@+id/addBt\"");
        lines.add("                android:layout_width=\"wrap_content\"");
        lines.add("                android:layout_height=\"match_parent\"");
        lines.add("                android:layout_alignParentRight=\"true\"");
        lines.add("                android:gravity=\"center\"");
        lines.add("                android:padding=\"10dp\"");
        lines.add("                android:text=\"添加\"");
        lines.add("                android:textColor=\"#000\"");
        lines.add("                android:textSize=\"14sp\" />");
        lines.add("        </RelativeLayout>");
        lines.add("");
        lines.add("        <LinearLayout");
        lines.add("            android:layout_width=\"match_parent\"");
        lines.add("            android:layout_height=\"match_parent\"");
        lines.add("            android:background=\"#fff\"");
        lines.add("            android:orientation=\"vertical\">");
        lines.add("");
        lines.add("        </LinearLayout>");
        lines.add("");
        lines.add("");
        lines.add("    </LinearLayout>");
        lines.add("</RelativeLayout>");
        return lines;
    }
}
