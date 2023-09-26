package core.path;

public class CheckLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {
    public com.codingtu.cooltu.lib4a.path.PathImageFile handle_jpg;
    public com.codingtu.cooltu.lib4a.path.PathImageFile handle_jpg_pnc;


    public static CheckLabelPath root(String root) {
        return new CheckLabelPath(root);
    }

    public CheckLabelPath(String root) {
        super(root);
        this.handle_jpg = new com.codingtu.cooltu.lib4a.path.PathImageFile(
                this.root
                        + addPrexSeparator("handle.jpg")
                , "jpg"
        );
        this.handle_jpg_pnc = new com.codingtu.cooltu.lib4a.path.PathImageFile(
                this.root
                        + addPrexSeparator("handle.jpg.pnc")
                , "pnc"
        );

    }
    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu_pre.bean.User> label_txt(String labelName) {
        return new com.codingtu.cooltu.lib4a.path.PathBeanFile(
                this.root
                        + addPrexSeparator(labelName + ".txt")
                , "txt", com.codingtu.cooltu_pre.bean.User.class
        );
    }
    public com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs<com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu_pre.bean.User>> label_txt_list(java.lang.String type) {
        com.codingtu.cooltu_pre.path.filter.LabelFilter filter = new com.codingtu.cooltu_pre.path.filter.LabelFilter();
        filter.type = type;

        return com.codingtu.cooltu.lib4j.ts.Ts.ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file.getName())) {
                return label_txt(file.getName());
            }
            return null;
        });
    }

}
