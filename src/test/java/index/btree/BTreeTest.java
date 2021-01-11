package index.btree;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTreeTest {

    @Nested
    class IntegerItemTest {
        @Test
        void insertIntegerItem() {

            BTree t = new BTree(3);
            t.insert(new IntegerItem(10));
            t.insert(new IntegerItem(20));
            t.insert(new IntegerItem(30));
            t.insert(new IntegerItem(40));
            t.insert(new IntegerItem(50));
            t.insert(new IntegerItem(60));
            t.insert(new IntegerItem(70));
            t.insert(new IntegerItem(80));
            t.insert(new IntegerItem(90));

            t.traverse();

            assertNull(t.search(new IntegerItem(1)));
            assertNotNull(t.search(new IntegerItem(10)));
        }

        @Test
        void deleteIntegerItem() {
            BTree t = new BTree(3);

            t.insert(new IntegerItem(1));
            t.insert(new IntegerItem(3));
            t.insert(new IntegerItem(7));
            t.insert(new IntegerItem(10));
            t.insert(new IntegerItem(11));
            t.insert(new IntegerItem(13));
            t.insert(new IntegerItem(14));
            t.insert(new IntegerItem(15));
            t.insert(new IntegerItem(18));
            t.insert(new IntegerItem(16));
            t.insert(new IntegerItem(19));
            t.insert(new IntegerItem(24));
            t.insert(new IntegerItem(25));
            t.insert(new IntegerItem(26));
            t.insert(new IntegerItem(21));
            t.insert(new IntegerItem(4));
            t.insert(new IntegerItem(5));
            t.insert(new IntegerItem(20));
            t.insert(new IntegerItem(22));
            t.insert(new IntegerItem(2));
            t.insert(new IntegerItem(17));
            t.insert(new IntegerItem(12));
            t.insert(new IntegerItem(6));

            t.traverse();

            t.remove(new IntegerItem(6));
            t.traverse();

            t.remove(new IntegerItem(13));
            t.traverse();

            t.remove(new IntegerItem(7));
            t.traverse();

            t.remove(new IntegerItem(4));
            t.traverse();

            t.remove(new IntegerItem(2));
            t.traverse();

            t.remove(new IntegerItem(16));
            t.traverse();
        }
    }

    @Nested
    class StringItemTest {
        @Test
        void insertIntegerItem() {

            BTree t = new BTree(3);
            t.insert(new StringItem("e"));
            t.insert(new StringItem("f"));
            t.insert(new StringItem("g"));
            t.insert(new StringItem("h"));
            t.insert(new StringItem("i"));
            t.insert(new StringItem("a"));
            t.insert(new StringItem("b"));
            t.insert(new StringItem("c"));
            t.insert(new StringItem("d"));

            t.traverse();

            assertNull(t.search(new StringItem("z")));
            assertNotNull(t.search(new StringItem("a")));
        }

        @Test
        void deleteIntegerItem() {
            BTree t = new BTree(3);

            t.insert(new StringItem("1"));
            t.insert(new StringItem("3"));
            t.insert(new StringItem("7"));
            t.insert(new StringItem("10"));
            t.insert(new StringItem("11"));
            t.insert(new StringItem("13"));
            t.insert(new StringItem("14"));
            t.insert(new StringItem("15"));
            t.insert(new StringItem("18"));
            t.insert(new StringItem("16"));
            t.insert(new StringItem("19"));
            t.insert(new StringItem("24"));
            t.insert(new StringItem("25"));
            t.insert(new StringItem("26"));
            t.insert(new StringItem("21"));
            t.insert(new StringItem("4"));
            t.insert(new StringItem("5"));
            t.insert(new StringItem("20"));
            t.insert(new StringItem("22"));
            t.insert(new StringItem("2"));
            t.insert(new StringItem("17"));
            t.insert(new StringItem("12"));
            t.insert(new StringItem("6"));

            t.traverse();

            t.remove(new StringItem("6"));
            t.traverse();

            t.remove(new StringItem("13"));
            t.traverse();

            t.remove(new StringItem("7"));
            t.traverse();

            t.remove(new StringItem("4"));
            t.traverse();

            t.remove(new StringItem("2"));
            t.traverse();

            t.remove(new StringItem("16"));
            t.traverse();
        }
    }
}
