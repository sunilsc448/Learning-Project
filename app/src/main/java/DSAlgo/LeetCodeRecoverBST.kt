package DSAlgo

import pojos.TreeNode

class LeetCodeRecoverBST {
    init {
        val finalNode = BinarySearchTree.getBSTree(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
    }
}

class Solution {
    var firstElement: TreeNode? = null
    var secondElement: TreeNode? = null
    var prevElement: TreeNode? = null
    fun recoverTree(root: TreeNode?): Unit {
        if(root==null) return;
        inOrderTraverse(root)
        val temp = firstElement?.`val`
        firstElement?.`val` = secondElement?.`val`
        secondElement?.`val` = temp
    }

    fun inOrderTraverse(root:TreeNode?) {
        if(root == null)return
        inOrderTraverse(root.left)
        if(firstElement == null && (prevElement == null || prevElement?.`val`!! >= root.`val`!!)) {
            firstElement = prevElement
        }
        if(firstElement != null && prevElement?.`val`!!>= root.`val`!!){
            secondElement = root
        }
        prevElement = root
        inOrderTraverse(root.right)
    }

}