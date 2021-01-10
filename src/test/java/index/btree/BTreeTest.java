package index.btree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTreeTest {

    @Test
    void insert() {

        BTree t = new BTree(3);
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
    void delete(){
        BTree t = new BTree(3);

        t.insert(1);
        t.insert(3);
        t.insert(7);
        t.insert(10);
        t.insert(11);
        t.insert(13);
        t.insert(14);
        t.insert(15);
        t.insert(18);
        t.insert(16);
        t.insert(19);
        t.insert(24);
        t.insert(25);
        t.insert(26);
        t.insert(21);
        t.insert(4);
        t.insert(5);
        t.insert(20);
        t.insert(22);
        t.insert(2);
        t.insert(17);
        t.insert(12);
        t.insert(6);

        t.traverse();

        t.remove(6);
        t.traverse();

        t.remove(13);
        t.traverse();

        t.remove(7);
        t.traverse();

        t.remove(4);
        t.traverse();

        t.remove(2);
        t.traverse();

        t.remove(16);
        t.traverse();
    }
}
