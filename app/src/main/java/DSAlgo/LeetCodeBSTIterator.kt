package DSAlgo

import pojos.TreeNode

class LeetCodeBSTIterator {
    init {
        val root = BinarySearchTree.getBSTree(intArrayOf(7, 3, 15, 9, 20))
        val bSTIterator = BSTIterator(root)
    }
}

class BSTIterator(root: TreeNode?) {
    var head: TreeNode? = root
    var currentIndex = 0
    var items:ArrayList<Int> = ArrayList()

    init{
        inOrderTraversal(head)
        items.forEach{
            println("item is >>> $it")
        }
    }

    fun next(): Int {
        return items[currentIndex++]
    }

    fun hasNext(): Boolean {
        return currentIndex < items.size
    }

    fun inOrderTraversal(rootLocal: TreeNode?){
        if(rootLocal == null)return
        inOrderTraversal(rootLocal.left)
        if(rootLocal.`val` != null) {
            items.add(rootLocal.`val`!!)
        }
        inOrderTraversal(rootLocal.right)
    }
}