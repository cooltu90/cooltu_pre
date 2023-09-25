package com.codingtu.cooltu.lib4a.path;

import android.graphics.Bitmap;

import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;

public class PathImageFile extends PathBaseFile {
    public PathImageFile(String root, String type) {
        super(root, type);
    }

    public Bitmap bitmap() {
        return FileBitmap.obtain(this.root).bitmap();
    }

    public Bitmap bitmap(int w, int h) {
        return FileBitmap.obtain(this.root).size(w, h).bitmap();
    }

    public Bitmap bitmap(WH wh) {
        return FileBitmap.obtain(this.root).size(wh).bitmap();
    }

    public Bitmap bitmap(int size) {
        return FileBitmap.obtain(this.root).size(size).bitmap();
    }


    public void bitmap(Bitmap bitmap, int percent) {
        SDCardTool.bitMapToFile(bitmap, this.root, percent);
    }

    public void bitmap(Bitmap bitmap) {
        bitmap(bitmap, 100);
    }


}
