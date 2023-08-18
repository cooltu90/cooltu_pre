package cooltu.lib4j.ts.eachgetter;

public interface EachGetter<T> {
    public T get(int position);

    public int count();
}
