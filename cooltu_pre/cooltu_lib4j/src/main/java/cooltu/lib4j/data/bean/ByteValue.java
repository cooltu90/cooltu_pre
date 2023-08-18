package cooltu.lib4j.data.bean;

public class ByteValue {

    private byte value;

    public static ByteValue obtain(byte v) {
        ByteValue value = new ByteValue();
        value.value = v;
        return value;
    }

    public int toInt() {
        return 0 | (value & 0xff);
    }

}
