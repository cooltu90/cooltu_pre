package core.path;

public class CheckExtraInfoDeleteLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {


    public static CheckExtraInfoDeleteLabelPath root(String root) {
        return new CheckExtraInfoDeleteLabelPath(root);
    }

    public CheckExtraInfoDeleteLabelPath(String root) {
        super(root);

    }
    public CheckExtraInfoDeleteLabelLabelPath label(String labelName) {
        return new CheckExtraInfoDeleteLabelLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }
    public com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs<CheckExtraInfoDeleteLabelLabelPath> labelList(java.lang.String type) {
        com.codingtu.cooltu_pre.path.filter.LabelFilter filter = new com.codingtu.cooltu_pre.path.filter.LabelFilter();
        filter.type = type;

        return com.codingtu.cooltu.lib4j.ts.Ts.ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file.getName())) {
                return label(file.getName());
            }
            return null;
        });
    }

}
