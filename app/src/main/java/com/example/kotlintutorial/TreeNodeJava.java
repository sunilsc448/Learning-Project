package com.example.kotlintutorial;

class TreeNodeJava {
    public TreeNodeJava left = null;
    public TreeNodeJava right = null;
    public int key;
    public TreeNodeJava(int key){
        this.key = key;
    }

    public TreeNodeJava(int key, TreeNodeJava left, TreeNodeJava right){
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public TreeNodeJava insertBSTNode(TreeNodeJava root, int key){
        if (root == null) {
            return new TreeNodeJava(key);
        }
        if (root.key > key) {
            root.left = insertBSTNode(root.left, key);
        } else {
            root.right = insertBSTNode(root.right, key);
        }
        return root;
    }
}
