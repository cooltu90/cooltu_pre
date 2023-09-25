package com.codingtu.cooltu_pre.path.test;

import com.codingtu.cooltu.lib4a.path.BasePath;
import com.codingtu.cooltu.lib4a.path.PathBeanFile;
import com.codingtu.cooltu.lib4a.path.PathTextFile;
import com.codingtu.cooltu_pre.bean.User;

public class CheckDeleteLabelPath extends BasePath {

    public PathTextFile i4l;
    public PathBeanFile<User> user_txt;

    public CheckDeleteLabelPath(String root) {
        super(root);
        this.i4l = new PathTextFile(
                this.root
                        + addPrexSeparator("i4l.txt")
                , "txt"
        );
        this.user_txt = new PathBeanFile(
                this.root
                        + addPrexSeparator("")
                , "", User.class
        );
    }

    public CheckDeleteLabelLabelPath label(String labelName) {
        return new CheckDeleteLabelLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }

}
