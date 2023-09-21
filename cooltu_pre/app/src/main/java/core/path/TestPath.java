package core.path;

import com.codingtu.cooltu.lib4a.bean.PathBean;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;

public class TestPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public String root;
    public String ExtraInfo;
    public com.codingtu.cooltu.lib4a.bean.PathBean weather_txt;


    public static TestPath obtain(String company, String taskName) {
        return root(SDCardTool.getSDCard()
                + addPrexSeparator("EnvCheckData")
                + addPrexSeparator("tasks")
                + addPrexSeparator(company)
                + addPrexSeparator(taskName)

        );
    }

    public static TestPath root(String root) {
        return new TestPath(root);
    }

    public TestPath(String root) {
        this.root = root;
        this.ExtraInfo =
                this.root
                        + addPrexSeparator("ex")
        ;
        this.weather_txt = new PathBean(
                this.ExtraInfo
                        + addPrexSeparator("weather.txt")
                , "txt"
        );
    }
}
