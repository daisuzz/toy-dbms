package index.btree;

public class BTreeNode {

    int t;

    // current number of keys.
    int n;

    int[] keys;

    BTreeNode[] bTreeNodes;

    boolean isLeaf;

    public BTreeNode(int t, final boolean isLeaf) {
        this.t = t;
        this.n = 0;
        this.keys = new int[2 * t - 1];
        this.bTreeNodes = new BTreeNode[2 * t];
        this.isLeaf = isLeaf;
    }

    /**
     * traverse a sub tree.
     */
    public void traverse() {

        int i;

        for (i = 0; i < n; i++) {
            if (!isLeaf) {
                bTreeNodes[i].traverse();
            }
            System.out.println(keys[i]);
        }

        if (!isLeaf) {
            bTreeNodes[i].traverse();
        }
    }

    /**
     * search a sub tree node by key.
     *
     * @param k key
     * @return a sub tree node
     */
    BTreeNode search(int k) {

        int i = 0;

        // find first key gte to k
        while (i < n && keys[i] < k) {
            i++;
        }

        // if the found key equals to k
        if (keys[i] == k) {
            return this;
        }

        // if key is not found and this node is leaf
        if (isLeaf) {
            return null;
        }

        // go to child node
        return bTreeNodes[i].search(k);
    }

    /**
     * insert a new key into a sub tree.
     */
    void insertNonFull(int k) {

        int i = n - 1;

        if (isLeaf) {

            // move all greater keys to right
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }

            // insert the ney key.
            keys[i + 1] = k;
            n = n + 1;
        }

        if (!isLeaf) {

            // find target child for insertion.
            while (i >= 0 && keys[i] > k) {
                i--;
            }

            // if target child for insertion is full, split it.
            if (bTreeNodes[i + 1].n == 2 * t - 1) {

                splitChild(i + 1, bTreeNodes[i + 1]);

                if (keys[i + 1] < k) {
                    i++;
                }
            }
            bTreeNodes[i + 1].insertNonFull(k);
        }
    }

    /**
     * split child node y of this node.
     *
     * @param index index of y.
     * @param y     child node of this node
     */
    void splitChild(int index, BTreeNode y) {

        // create new node which stores t-1 keys of y.
        BTreeNode z = new BTreeNode(y.t, y.isLeaf);
        z.n = t - 1;

        // copy the last t-1 keys of y to z.
        if (z.n >= 0) System.arraycopy(y.keys, t, z.keys, 0, z.n);

        // copy the last t children of y to z.
        if (!y.isLeaf) {
            if (t >= 0) System.arraycopy(y.bTreeNodes, t, z.bTreeNodes, 0, t);
        }

        y.n = t - 1;

        // create space for new child.
        if (n - index >= 0) System.arraycopy(bTreeNodes, index + 1, bTreeNodes, index + 2, n - index);

        bTreeNodes[index + 1] = z;

        // find the location of new key and move all greater keys to right.
        if (n - index >= 0) System.arraycopy(keys, index, keys, index + 1, n - index);

        // copy the middle key of y to this node.
        keys[index] = y.keys[t - 1];
        n = n + 1;
    }
}
