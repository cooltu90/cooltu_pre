package cooltu.lib4j.data.bean;

public class KV<K, V> {
    public K k;
    public V v;

    public KV() {
    }

    public KV(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public String toString() {
        return "KV{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
