package index.btree;

public class IntegerItem implements Item {

    Integer v;

    IntegerItem(Integer v) {
        this.v = v;
    }

    @Override
    public boolean less(final Item i) {
        return v < ((IntegerItem) i).v;
    }

    @Override
    public boolean greater(final Item i) {
        return v > ((IntegerItem) i).v;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof IntegerItem) {
            return v.equals(((IntegerItem) obj).v);
        }
        return false;
    }

    @Override
    public String toString() {
        return v.toString();
    }
}
