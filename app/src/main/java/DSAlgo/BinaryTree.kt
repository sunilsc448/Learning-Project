package DSAlgo

import pojos.TreeNode
import java.util.*

class BinaryTree {
    var preOrderCounter = 0
    var preOrderArray:IntArray? = null

    init {

    }

    companion object {
        fun createBinaryTree(inputs: IntArray): TreeNode? {
            var root: TreeNode? = TreeNode(inputs[0])
            var queue: Queue<TreeNode> = LinkedList() //way of creating queue
            queue.add(root)

            for (i in 1 until inputs.size - 1 step 2) {
                var node = queue.poll()
                val itemLeft = inputs.get(i)
                val itemRight = inputs.get(i + 1)
                var leftNode = TreeNode(itemLeft)
                var rightNode = TreeNode(itemRight)
                queue.add(leftNode)
                queue.add(rightNode)
                node.left = leftNode
                node.right = rightNode
            }
            return root
        }
    }


}