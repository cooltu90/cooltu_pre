package core.path;

public class CheckExtraInfoPath extends com.codingtu.cooltu.lib4a.path.BasePath {
    public CheckExtraInfoDeleteLabelPath DeleteLabel;


    public static CheckExtraInfoPath root(String root) {
        return new CheckExtraInfoPath(root);
    }

    public CheckExtraInfoPath(String root) {
        super(root);
        this.DeleteLabel = new CheckExtraInfoDeleteLabelPath(
                this.root
                        + addPrexSeparator("DeleteLabel")
        );

    }

}

