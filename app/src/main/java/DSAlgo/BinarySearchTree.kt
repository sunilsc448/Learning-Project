package DSAlgo

import pojos.TreeNode

class BinarySearchTree {
    var count = 0
    var preOrderCounter = 0
    var preOrderArray:IntArray? = null

    init {
//        trimBST(BinarySearchTree.getBSTree(intArrayOf(1,2,3,4,5,6,7, 8, 9, 10, 11)), -13, 14)
//
//        val finalNode = BinarySearchTree.getBSTree(intArrayOf(1,2,3,4,5,6,7, 8, 9, 10, 11))
//        searchBST(finalNode, 8)
//
//        val smallestElement = kthSmallest(BinarySearchTree.getBSTree(intArrayOf(1,2,3,4,5,6,7, 8, 9, 10, 11)), 6)
//        println(smallestElement)

//        preOrderTraversal(BinaryTree.createBinaryTree(intArrayOf(1, 3, 2)))
//        preOrderArray?.forEach {
//            println("preorder BST item is $it")
//        }
    }

    companion object{
        fun getBSTree(inputs: IntArray): TreeNode? {
            var root: TreeNode? = null
            for (i in inputs.indices){
                val item = inputs.get(i)
                if(i == 0){
                    root= TreeNode(item)
                }else{
                    root = root?.insert(root,item)
                }
            }
            return root
        }
    }

    fun preOrderTraversal(root: TreeNode?) {
        if(root == null)
            return
        preOrderArray!!.set(preOrderCounter++,root.`val`!!)
        preOrderTraversal(root.left)
        preOrderTraversal(root.right)
    }

    private fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
        if(root == null)
            return null

        root.left = trimBST(root.left, low, high)
        root.right = trimBST(root.right, low, high)
        if(root.`val`!! < low){
            return root.right
        }else if(root.`val`!! > high){
            return root.left
        }
        return root
    }

    private fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root == null){
            return root
        }
        if(root?.`val`?.equals(`val`)!!){
            return root
        }
        val leftNode =  searchBST(root?.left,`val`)
        if(leftNode == null){
            val rightNode = searchBST(root?.right,`val`)
            return rightNode
        }
        return leftNode
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int? {
        val result = kthSmallestFunction(root, k)
        if(result == null){
            return null
        }else{
            return result.`val`
        }
    }

    fun kthSmallestFunction(root: TreeNode?, k: Int): TreeNode? {
        if(root == null)return root
        val left = kthSmallestFunction(root.left, k)
        if(left != null){
            return left
        }
        count++
        if(count == k)
            return root

        return kthSmallestFunction(root.right, k)
    }
}