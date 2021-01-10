package index.btree;

/**
 * @see <a href="https://www.geeksforgeeks.org/introduction-of-b-tree-2/">Introduction of B-Tree</a>
 */
public class BTree {

    public BTreeNode root;

    public int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    /**
     * traverse B Tree.
     */
    public void traverse() {
        if (this.root != null) {
            root.traverse();
        }
        System.out.println();
    }

    /**
     * search a B Tree node by the key.
     *
     * @param k key
     * @return a B Tree node
     */
    public BTreeNode search(int k) {
        if (this.root == null) {
            return null;
        }
        return root.search(k);
    }

    /**
     * insert a new key into this B tree.
     *
     * @param k a new key
     */
    public void insert(int k) {

        // if B tree is empty, allocate memory for root and then insert key.
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = root.n + 1;
            return;
        }

        // if root is not full, insert key into root.
        if (root.n != 2 * t - 1) {
            root.insertNonFull(k);
        }

        // if root is full, split child
        if (root.n == 2 * t - 1) {

            BTreeNode s = new BTreeNode(t, false);
            s.bTreeNodes[0] = root;

            s.splitChild(0, root);

            int i = 0;
            if (s.keys[0] < k) {
                i++;
            }
            s.bTreeNodes[i].insertNonFull(k);

            root = s;
        }
    }
}