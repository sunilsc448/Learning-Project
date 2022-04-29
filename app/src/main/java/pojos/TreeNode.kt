package pojos

class TreeNode{
    var left: TreeNode? = null
    var right: TreeNode? = null
    var `val`:Int? = null
    constructor( `val`: Int?) {
        this.`val` = `val`
    }
    constructor(`val`: Int?, left: TreeNode?, right: TreeNode?){
        this.`val` =  `val`
        this.left = left
        this.right = right
    }

    fun insert(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) {
            return TreeNode(key)
        }
        if (root.`val`!! > key) {
            root.left = insert(root.left, key)
        } else {
            root.right = insert(root.right, key)
        }
        return root
    }

    var isLeft = true
    fun insertBinaryTreeNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) {
            isLeft = !isLeft
            return TreeNode(key)
        }

        if (isLeft) {
            root.left = insertBinaryTreeNode(root.left, key)
        } else {
            root.right = insertBinaryTreeNode(root.right,key)
        }
        return root
    }

    @JvmOverloads
    fun test(a: Int, b:Int, c:Int = 0){

    }
}