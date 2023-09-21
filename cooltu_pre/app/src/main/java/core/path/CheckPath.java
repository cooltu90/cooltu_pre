package core.path;

import com.codingtu.cooltu.lib4a.tools.SDCardTool;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public String root;
    public String ExtraInfo;
    public com.codingtu.cooltu.lib4a.bean.PathBean weather_txt;


    public static CheckPath obtain(String company, String taskName) {
        return root(SDCardTool.getSDCard()
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
        this.root = root;
        this.ExtraInfo =
                this.root
                        + addPrexSeparator("ExtraInfo")
        ;
        this.weather_txt = new com.codingtu.cooltu.lib4a.bean.PathBean(
                this.ExtraInfo
                        + addPrexSeparator("weather.txt")
                , "txt"
        );

    }
}
