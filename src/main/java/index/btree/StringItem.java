package index.btree;

public class StringItem implements Item {


    String v;

    StringItem(String v) {
        this.v = v;
    }

    @Override
    public boolean less(final Item i) {
        return v.compareTo(((StringItem) i).v) < 0;
    }

    @Override
    public boolean greater(final Item i) {
        return v.compareTo(((StringItem) i).v) > 0;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof StringItem) {
            return v.equals(((StringItem) obj).v);
        }
        return false;
    }

    @Override
    public String toString() {
        return v;
    }
}
