package cooltu.lib4j.data.bean;

public class JavaInfo {
    public String path;
    public String name;
    public String pkg;
    public String fullName;

    @Override
    public String toString() {
        return "JavaInfo{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", pkg='" + pkg + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
