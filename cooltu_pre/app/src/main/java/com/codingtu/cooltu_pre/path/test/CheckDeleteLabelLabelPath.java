package com.codingtu.cooltu_pre.path.test;

import com.codingtu.cooltu.lib4a.path.BasePath;
import com.codingtu.cooltu.lib4a.path.PathBeanFile;
import com.codingtu.cooltu.lib4a.path.PathImageFile;
import com.codingtu.cooltu.lib4a.path.PathTextFile;
import com.codingtu.cooltu_pre.bean.User;

public class CheckDeleteLabelLabelPath extends BasePath {

    public PathImageFile handle_jpg;

    public CheckDeleteLabelLabelPath(String root) {
        super(root);
        this.handle_jpg = new PathImageFile(
                this.root
                        + addPrexSeparator("handle.jpg")
                , "txt"
        );
    }


    public PathBeanFile<User> label_txt(String labelName) {
        return new PathBeanFile(
                this.root
                        + addPrexSeparator(labelName + ".txt")
                , "txt", User.class
        );
    }

}
