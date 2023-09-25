package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {
    public CheckDeleteLabelPath DeleteLabel;
    public CheckExtraInfoPath ExtraInfo;

    public static CheckPath obtain(String company, String taskName) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("EnvCheckData")
                + addPrexSeparator("tasks")
                + addPrexSeparator(company)
                + addPrexSeparator(taskName)
        );
    }

    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
        this.DeleteLabel = new CheckDeleteLabelPath(
                this.root
                        + addPrexSeparator("DeleteLabel")
        );
        this.ExtraInfo = new CheckExtraInfoPath(
                this.root
                        + addPrexSeparator("ExtraInfo")
        );

    }
    public CheckLabelPath label(String labelName) {
        return new CheckLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }

}
