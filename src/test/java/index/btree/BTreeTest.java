package index.btree;

import index.key.IntegerKey;
import index.key.StringKey;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTreeTest {

    @Nested
    class IntegerKeyTest {
        @Test
        void insertIntegerItem() {

            BTree t = new BTree(3);
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
        void deleteIntegerItem() {
            BTree t = new BTree(3);

            t.insert(new IntegerKey(1));
            t.insert(new IntegerKey(3));
            t.insert(new IntegerKey(7));
            t.insert(new IntegerKey(10));
            t.insert(new IntegerKey(11));
            t.insert(new IntegerKey(13));
            t.insert(new IntegerKey(14));
            t.insert(new IntegerKey(15));
            t.insert(new IntegerKey(18));
            t.insert(new IntegerKey(16));
            t.insert(new IntegerKey(19));
            t.insert(new IntegerKey(24));
            t.insert(new IntegerKey(25));
            t.insert(new IntegerKey(26));
            t.insert(new IntegerKey(21));
            t.insert(new IntegerKey(4));
            t.insert(new IntegerKey(5));
            t.insert(new IntegerKey(20));
            t.insert(new IntegerKey(22));
            t.insert(new IntegerKey(2));
            t.insert(new IntegerKey(17));
            t.insert(new IntegerKey(12));
            t.insert(new IntegerKey(6));

            t.traverse();

            t.remove(new IntegerKey(6));
            t.traverse();

            t.remove(new IntegerKey(13));
            t.traverse();

            t.remove(new IntegerKey(7));
            t.traverse();

            t.remove(new IntegerKey(4));
            t.traverse();

            t.remove(new IntegerKey(2));
            t.traverse();

            t.remove(new IntegerKey(16));
            t.traverse();
        }
    }

    @Nested
    class StringKeyTest {
        @Test
        void insertIntegerItem() {

            BTree t = new BTree(3);
            t.insert(new StringKey("e"));
            t.insert(new StringKey("f"));
            t.insert(new StringKey("g"));
            t.insert(new StringKey("h"));
            t.insert(new StringKey("i"));
            t.insert(new StringKey("a"));
            t.insert(new StringKey("b"));
            t.insert(new StringKey("c"));
            t.insert(new StringKey("d"));

            t.traverse();

            assertNull(t.search(new StringKey("z")));
            assertNotNull(t.search(new StringKey("a")));
        }

        @Test
        void deleteIntegerItem() {
            BTree t = new BTree(3);

            t.insert(new StringKey("1"));
            t.insert(new StringKey("3"));
            t.insert(new StringKey("7"));
            t.insert(new StringKey("10"));
            t.insert(new StringKey("11"));
            t.insert(new StringKey("13"));
            t.insert(new StringKey("14"));
            t.insert(new StringKey("15"));
            t.insert(new StringKey("18"));
            t.insert(new StringKey("16"));
            t.insert(new StringKey("19"));
            t.insert(new StringKey("24"));
            t.insert(new StringKey("25"));
            t.insert(new StringKey("26"));
            t.insert(new StringKey("21"));
            t.insert(new StringKey("4"));
            t.insert(new StringKey("5"));
            t.insert(new StringKey("20"));
            t.insert(new StringKey("22"));
            t.insert(new StringKey("2"));
            t.insert(new StringKey("17"));
            t.insert(new StringKey("12"));
            t.insert(new StringKey("6"));

            t.traverse();

            t.remove(new StringKey("6"));
            t.traverse();

            t.remove(new StringKey("13"));
            t.traverse();

            t.remove(new StringKey("7"));
            t.traverse();

            t.remove(new StringKey("4"));
            t.traverse();

            t.remove(new StringKey("2"));
            t.traverse();

            t.remove(new StringKey("16"));
            t.traverse();
        }
    }
}
