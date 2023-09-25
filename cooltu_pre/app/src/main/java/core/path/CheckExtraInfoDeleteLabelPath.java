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

}

