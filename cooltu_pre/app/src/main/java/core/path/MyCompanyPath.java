package core.path;

public class MyCompanyPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public static MyCompanyPath obtain(String type) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("MyApp")
                + addPrexSeparator(type)
                + addPrexSeparator("xxx")
        );
    }

    public static MyCompanyPath root(String root) {
        return new MyCompanyPath(root);
    }

    public MyCompanyPath(String root) {
        super(root);

    }

}

