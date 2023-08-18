package cooltu.lib4j.ts.each;

public interface MapEach<K, V> {
    public boolean each(int position, K k, V v);
}