package index.key;

public class IntegerKey implements Key {

    Integer v;

    public IntegerKey(Integer v) {
        this.v = v;
    }

    @Override
    public boolean less(final Key i) {
        return v < ((IntegerKey) i).v;
    }

    @Override
    public boolean greater(final Key i) {
        return v > ((IntegerKey) i).v;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof IntegerKey) {
            return v.equals(((IntegerKey) obj).v);
        }
        return false;
    }

    @Override
    public String toString() {
        return v.toString();
    }
}
