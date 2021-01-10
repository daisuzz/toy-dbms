package index.btree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTreeTest {

    @Test
    void insert() {

        BTree btree = new BTree(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(30);
        btree.insert(40);
        btree.insert(50);
        btree.insert(60);
        btree.insert(70);
        btree.insert(80);
        btree.insert(90);

        btree.traverse();

        assertNull(btree.search(1));
        assertNotNull(btree.search(10));
    }
}
