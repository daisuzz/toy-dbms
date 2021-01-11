package index.bplustree;

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
        t.insert(10);
        t.insert(20);
        t.insert(30);
        t.insert(40);
        t.insert(50);
        t.insert(60);
        t.insert(70);
        t.insert(80);
        t.insert(90);

        t.traverse();

        assertNull(t.search(1));
        assertNotNull(t.search(10));
    }

    @Test
    void remove() {

        BPlusTree t = new BPlusTree(3);
        t.insert(10);
        t.insert(20);
        t.insert(30);
        t.insert(40);
        t.insert(50);
        t.insert(60);
        t.insert(70);
        t.insert(80);
        t.insert(90);

        t.traverse();

        t.remove(40);
        t.traverse();

        t.remove(70);
        t.traverse();

        t.remove(80);
        t.traverse();

        t.remove(90);
        t.traverse();

    }
}
