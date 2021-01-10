package index.btree;

public class BTreeNode {

    int t;

    // the current number of keys
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
    void traverse() {

        int i;

        for (i = 0; i < n; i++) {
            if (!isLeaf) {
                bTreeNodes[i].traverse();
            }
            System.out.print(keys[i] + " ");
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

        // find first key greater or equal to k
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


    /**
     * find first key index that is greater or equals to k
     *
     * @param k key
     * @return first key index that is greater or equals to k.
     */
    int findKey(int k) {
        int index = 0;
        while (index < n && keys[index] < k) {
            index++;
        }
        return index;
    }

    /**
     * remove key in subtree rooted with this node.
     *
     * @param k key
     */
    void remove(int k) {

        int index = findKey(k);

        if (index < n && keys[index] == k) {
            if (isLeaf) {
                removeFromLeaf(index);
            } else {
                removeFromNonLeaf(index);
            }
            return;
        }

        if (isLeaf) {
            System.out.println("The key " + k + " does not exist in this tree.");
            return;
        }

        if (bTreeNodes[index].n < t) {
            fill(index);
        }

        if (index >= n) {
            bTreeNodes[index - 1].remove(k);
        } else {
            bTreeNodes[index].remove(k);
        }
    }

    /**
     * remove key present in index-th position in this leaf node
     *
     * @param index index of key
     */
    void removeFromLeaf(int index) {

        // move all keys after the index-th position one place backward.
        if (n - (index + 1) >= 0) System.arraycopy(keys, index + 1, keys, index, n - (index + 1));

        n--;
    }

    /**
     * remove key present in index-th position in this branch node
     *
     * @param index index of key
     */
    void removeFromNonLeaf(int index) {

        int k = keys[index];

        // 2(a)
        if (bTreeNodes[index].n >= t) {
            int predecessor = getPredecessor(index);
            keys[index] = predecessor;
            bTreeNodes[index].remove(predecessor);
            return;
        }

        // 2(b)
        if (bTreeNodes[index + 1].n >= t) {
            int successor = getSuccessor(index);
            keys[index] = successor;
            bTreeNodes[index + 1].remove(successor);
        }

        // 2(c)
        merge(index);
        bTreeNodes[index].remove(k);
    }

    /**
     * get the predecessor of the key that is present in index-th in this node
     *
     * @param index index of key
     * @return index of the predecessor key
     */
    int getPredecessor(int index) {

        // get the right most leaf node
        BTreeNode current = bTreeNodes[index];
        while (!current.isLeaf) {
            current = current.bTreeNodes[current.n];
        }

        // return the last key of the leaf node
        return current.keys[current.n - 1];
    }

    /**
     * get the successor of the key that is present in index-th in this node
     *
     * @param index index of key
     * @return index of the predecessor key
     */
    int getSuccessor(int index) {

        // get the left most leaf node
        BTreeNode current = bTreeNodes[index + 1];
        while (!current.isLeaf) {
            current = current.bTreeNodes[0];
        }


        // return the first key of the leaf node
        return current.keys[0];
    }

    /**
     * fill up the child node present in index-th position in the btreeNodes[]
     * if that child has less than t-1 keys
     *
     * @param index index of key
     */
    void fill(int index) {

        // 3(a) if prev sibling child node has more than t-1 keys, borrow a key from that child
        if (index != 0 && bTreeNodes[index - 1].n >= t) {
            borrowFromPrev(index);
            return;
        }

        // 3(a) if next sibling child node has more than t-1 keys, borrow a key from that child
        if (index != n && bTreeNodes[index + 1].n >= t) {
            borrowFromNext(index);
            return;
        }

        // 3(b)
        if (index != n) {
            merge(index);
        } else {
            merge(index - 1);
        }
    }

    /**
     * borrow a key from the btreeNodes[index-1]-th node and place it in btreeNodes[index]-th node.
     *
     * @param index index of key
     */
    void borrowFromPrev(int index) {

        BTreeNode child = bTreeNodes[index];
        BTreeNode sibling = bTreeNodes[index - 1];

        // move all keys in child one step ahead
        if (child.n - 1 + 1 >= 0) System.arraycopy(child.keys, 0, child.keys, 1, child.n - 1 + 1);

        // if child is branch node, move all its child pointers one step ahead
        if (!child.isLeaf) {
            if (child.n + 1 >= 0) System.arraycopy(child.bTreeNodes, 0, child.bTreeNodes, 1, child.n + 1);
        }

        // set child's first key equal to current node (index-1)-th key
        child.keys[0] = keys[index - 1];

        // move sibling's last child node to this node's first child node.
        if (!child.isLeaf) {
            child.bTreeNodes[0] = sibling.bTreeNodes[sibling.n];
        }

        // move the last key from the sibling to the parent
        keys[index - 1] = sibling.keys[sibling.n - 1];
        child.n += 1;
        sibling.n -= 1;
    }

    /**
     * borrow a key from the btreeNodes[index+1]-th node and place it in btreeNodes[index]-th node.
     *
     * @param index index of key
     */
    void borrowFromNext(int index) {

        BTreeNode child = bTreeNodes[index];
        BTreeNode sibling = bTreeNodes[index + 1];

        // move the index-th key from this node to the child last key
        child.keys[child.n] = keys[index];

        // move sibling's first child to the child's last child
        if (!child.isLeaf) {
            child.bTreeNodes[child.n + 1] = sibling.bTreeNodes[0];
        }

        // move the first key from sibling to the index-th key from this node
        keys[index] = sibling.keys[0];

        // move all keys in sibling one step behind
        if (sibling.n - 1 >= 0) System.arraycopy(sibling.keys, 1, sibling.keys, 0, sibling.n - 1);

        // move the child pointers one step behind
        if (!sibling.isLeaf) {
            if (sibling.n >= 0) System.arraycopy(sibling.bTreeNodes, 1, sibling.bTreeNodes, 0, sibling.n);
        }

        child.n += 1;
        sibling.n -= 1;
    }

    /**
     * merge index-th child of the node with (index+1)-th child of the node
     *
     * @param index index of key
     */
    void merge(int index) {

        BTreeNode child = bTreeNodes[index];
        BTreeNode sibling = bTreeNodes[index + 1];

        child.keys[t - 1] = keys[index];

        if (sibling.n >= 0) {
            System.arraycopy(sibling.keys, 0, child.keys, t, sibling.n);
        }

        if (!child.isLeaf) {
            if (sibling.n + 1 >= 0) System.arraycopy(sibling.bTreeNodes, 0, child.bTreeNodes, t, sibling.n + 1);
        }

        if (n + 1 - (index + 2) >= 0) {
            System.arraycopy(bTreeNodes, index + 2, bTreeNodes, index + 2 - 1, n + 1 - (index + 2));
        }

        child.n += sibling.n + 1;
        n--;
    }

}
