package cooltu.lib4j.ts.getter;

public interface Getter<K, V> {
    public boolean get(K k, V v);
}
