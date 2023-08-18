package cooltu.lib4j.data.bean;

public class Scale {

    private double scale;

    public Scale(double scale) {
        this.scale = scale;
    }

    public double getDoubleSize(double size) {
        return size * scale;
    }

    public int getSize(double size) {
        return (int) getDoubleSize(size);
    }

}
