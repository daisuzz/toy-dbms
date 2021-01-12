package index.bplustree;

import index.key.Key;

public class BPlusTree {

    public BPlusTreeNode root;

    public int t;

    public BPlusTree(int t) {
        this.root = null;
        this.t = t;
    }

    /**
     * traverse B+ Tree.
     */
    public void traverse() {
        if (this.root != null) {
            root.traverse();
        }
        System.out.println();
    }

    /**
     * search a B+ Tree node by the key.
     *
     * @param k key
     * @return a B+ Tree node
     */
    public BPlusTreeNode search(Key k) {
        if (root == null) {
            return null;
        }
        return root.search(k);
    }

    /**
     * insert a new key into this B+ tree.
     *
     * @param k a new key
     */
    public void insert(Key k) {

        // if B+ tree is empty, allocate memory for root and then insert key.
        if (root == null) {
            root = new BPlusTreeNode(t, true);
            root.keys[0] = k;
            root.currentKeyNumbers++;
            return;
        }

        // if root is not full, insert key into root.
        if (!root.isFull()) {
            root.insertNonFull(k);
            return;
        }

        // if root is full, split child
        if (root.isFull()) {

            BPlusTreeNode s = new BPlusTreeNode(t, false);
            s.bTreeNodes[0] = root;

            s.splitChild(0, root);

            int i = 0;
            if (s.keys[0].less(k)) {
                i++;
            }
            s.bTreeNodes[i].insertNonFull(k);

            root = s;
        }
    }

    /**
     * remove key in this B+ tree.
     *
     * @param k key
     */
    void remove(Key k){

        if(root == null){
            System.out.println("The tree is empty");
            return;
        }

        root.remove(k);

        if(root.isEmpty()){
            if(root.isLeaf){
                root = null;
            }else{
                root = root.bTreeNodes[0];
            }
        }
    }
}
