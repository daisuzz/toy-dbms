package index.btree;

public interface Item {

    boolean less(Item i);

    boolean greater(Item i);
}
