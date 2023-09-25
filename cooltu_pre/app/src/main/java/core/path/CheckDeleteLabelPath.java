package core.path;

public class CheckDeleteLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {
    public com.codingtu.cooltu.lib4a.path.PathTextFile i4l_txt;
    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu_pre.bean.User> user_txt;


    public static CheckDeleteLabelPath root(String root) {
        return new CheckDeleteLabelPath(root);
    }

    public CheckDeleteLabelPath(String root) {
        super(root);
        this.i4l_txt = new com.codingtu.cooltu.lib4a.path.PathTextFile(
                this.root
                        + addPrexSeparator("i4l.txt")
                , "txt"
        );
        this.user_txt = new com.codingtu.cooltu.lib4a.path.PathBeanFile(
                this.root
                        + addPrexSeparator("user.txt")
                , "txt", com.codingtu.cooltu_pre.bean.User.class
        );

    }
    public CheckDeleteLabelLabelPath label(String labelName) {
        return new CheckDeleteLabelLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }

}

