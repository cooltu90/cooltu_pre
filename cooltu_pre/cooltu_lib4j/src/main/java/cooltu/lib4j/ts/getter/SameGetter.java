package cooltu.lib4j.ts.getter;

public abstract class SameGetter<S, T> implements Getter<Integer, T> {

    public S target;

    public SameGetter(S target) {
        this.target = target;
    }

    @Override
    public boolean get(Integer integer, T t) {
        return same(integer, t, target);
    }

    public abstract boolean same(Integer index, T t, S target);
}
