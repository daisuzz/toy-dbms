package index.bplustree;

import index.key.IntegerKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BPlusTreeTest {

    @Test
    void insert() {

        /*
        [  30    ,   60   ]
        |        |        |
        |        |        |
        |        |        |
        [10,20]->[40,50]->[70,80,90]
        */
        BPlusTree t = new BPlusTree(3);
        t.insert(new IntegerKey(10));
        t.insert(new IntegerKey(20));
        t.insert(new IntegerKey(30));
        t.insert(new IntegerKey(40));
        t.insert(new IntegerKey(50));
        t.insert(new IntegerKey(60));
        t.insert(new IntegerKey(70));
        t.insert(new IntegerKey(80));
        t.insert(new IntegerKey(90));

        t.traverse();

        assertNull(t.search(new IntegerKey(1)));
        assertNotNull(t.search(new IntegerKey(10)));
    }

    @Test
    void remove() {

        BPlusTree t = new BPlusTree(3);
        t.insert(new IntegerKey(10));
        t.insert(new IntegerKey(20));
        t.insert(new IntegerKey(30));
        t.insert(new IntegerKey(40));
        t.insert(new IntegerKey(50));
        t.insert(new IntegerKey(60));
        t.insert(new IntegerKey(70));
        t.insert(new IntegerKey(80));
        t.insert(new IntegerKey(90));

        t.traverse();

        t.remove(new IntegerKey(40));
        t.traverse();

        t.remove(new IntegerKey(70));
        t.traverse();

        t.remove(new IntegerKey(80));
        t.traverse();

        t.remove(new IntegerKey(90));
        t.traverse();

    }
}
