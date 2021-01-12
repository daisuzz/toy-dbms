package index.key;

public class StringKey implements Key {

    String v;

    public StringKey(String v) {
        this.v = v;
    }

    @Override
    public boolean less(final Key i) {
        return v.compareTo(((StringKey) i).v) < 0;
    }

    @Override
    public boolean greater(final Key i) {
        return v.compareTo(((StringKey) i).v) > 0;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof StringKey) {
            return v.equals(((StringKey) obj).v);
        }
        return false;
    }

    @Override
    public String toString() {
        return v;
    }
}
