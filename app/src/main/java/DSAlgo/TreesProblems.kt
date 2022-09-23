package DSAlgo

import java.util.*


class TreesProblems {
    init {
        val fourNode_char = TreeNode('d')
        val fiveNode_char = TreeNode('e')
        val sixNode_char = TreeNode('f')
        val sevenNode_char = TreeNode('g')
        val threeNode_char = TreeNode('c', sixNode_char, sevenNode_char)
        val twoNode_char = TreeNode('b', fourNode_char, fiveNode_char)
        val oneNode_char = TreeNode('a', twoNode_char, threeNode_char)

        val eightNode = TreeNode(8)
        val nineNode = TreeNode(9)
        val tenNode = TreeNode(10)
        val elevenNode = TreeNode(11)
        val twelveNode = TreeNode(12)
        val thirteenNode = TreeNode(13)
        val fourteenNode = TreeNode(14)
        val fifteenNode = TreeNode(15)

//        val fourNode = TreeNode(4,eightNode, nineNode)
//        val fiveNode = TreeNode(5,tenNode, elevenNode)
//        val sixNode = TreeNode(6, twelveNode, thirteenNode)
//        val sevenNode = TreeNode(7,fourteenNode, fifteenNode)

        val fourNode = TreeNode(4)
        val fiveNode = TreeNode(5)
        val sixNode = TreeNode(6)
        val sevenNode = TreeNode(7)

        val threeNode = TreeNode(3, sixNode, sevenNode)
        val twoNode = TreeNode(2, fourNode, fiveNode)
        val oneNode = TreeNode(1, twoNode, threeNode)


        val frtn = TreeNode(14)
        val tn = TreeNode(10)
        val twl = TreeNode(12, tn, frtn)
        val fr = TreeNode(4)
        val eght = TreeNode(8, fr, twl)
        val twtnyon = TreeNode(21)
        val twtnytw = TreeNode(22)
        val twnty = TreeNode(20, eght, twtnytw)

//        preOrderTraversalRecursion(oneNode_char)
//        preOrderTraversalIteration(oneNode_char)
//        inOrderTraversalRecursion(oneNode_char)
//        inOrderTraversalIteration(oneNode_char)
//        postOrderTraversalRecursion(oneNode_char)
//        postOrderTraversalIteration(oneNode_char)
//        levelOrderTraversal(oneNode_char)
//        levelOrderLeetCode(oneNode)
//        levelOrderReverseOrderTraversal(oneNode_char)

//        val maxValue = findMaximumElementBT(oneNode)  //7
//        val maxValue = findMaximumElementIterationBT(oneNode)  //7
//        val isItemExist = searchElementBTIteration(oneNode, 11)
//        println(isItemExist)

//        insertElementIteration(oneNode, 8)
//        insertElementIteration(oneNode, 9)
//        println(oneNode)

//        val size = sizeOfBTRecursion(oneNode)
//        println(size)

//        val size = sizeOfBTIteration(oneNode)
//        println(size)

//        val size = heightOfBTRecursion(oneNode)
//        println(size)

//        val size = heightOfBTIteration(oneNode)
//        println(size)

//        val deepestNode = findDeepestNodeOfATree(oneNode)
//        println(deepestNode)

//        deleteANodeFromBT(oneNode, TreeNode(3, null, null))
//        println(oneNode)

//        val count = countFullNodesInBT(oneNode)
//        println(count)

//        val areIdentical = areBTsIdentical(oneNode, twoNode)
//        println(areIdentical)

//        val short = findShortestPath(oneNode)
//        println(short)

//        val maxSumLevel = findLevelThatHasMaxSum(oneNode)
//        println(maxSumLevel)

//        printAllPathsFromRooToLeaf(oneNode, IntArray(5), 0)

//        val isSumExist = checkIfSumExistsInAnyPath(oneNode, IntArray(5), 0, 22)
//        println(isSumExist)

//        val isSumExist = checkIfSumExistsInAnyPathApproach2(oneNode,11)
//        println(isSumExist)

//        val sum = sumOfAllElementsBT(oneNode)
//        println(sum)

//        val mirrored = mirrorOfATree(oneNode)
//        println(mirrored )

//        val lcaNode = leastCommonAncestorOfTwoNodes(oneNode, 6,7)
//        println(lcaNode)

//        isAncestorsOfANode(oneNode, 5)

//        zigzagTraversal(oneNode)

//        val map = verticalSumOfBT(oneNode, 0, HashMap())
//        println(map)

//        val tree = expressionTree("abc*+D/")
//        println(tree)

//        constructBTFromArray(intArrayOf(1,2,3,4,5,6,7))

//        val tree = constructBTFromIOArrayAndPOArray(charArrayOf('d','b','e','a','f','c'), charArrayOf('a','b','d','e','c','f'),0, 5)
//        println(tree)

//          val tree = constructBTFromIOAndLOArray(intArrayOf(4, 8, 10, 12, 14, 20, 22), intArrayOf(20, 8, 22, 4, 12, 10, 14),0, 6, null)
//          println(tree)

//        val inorderSuccessor = inOrderSuccessorBST(twnty, twtnytw)
//        println(inorderSuccessor)

//        val inorderPredecessor = inOrderPredecessorBST(twnty, twtnytw)
//        println(inorderPredecessor)

//        val node = searchElementBSTIteration(twnty, 12)
//        println(node)

//        val tree = insertElementBST(twnty, 9)
//        println(tree)

//        val tree = deleteElementBST(twnty, 22)
//        println(tree)

//        val diameter = diameterOfBST(twnty)
//        println(diameter)

//        val kthSmallest = kthSmallestElementBST(twnty, 9)
//        println(kthSmallest)

//        val pruneBST = pruneBST(twnty, 8, 20)
//        println(pruneBST)

//        val isBST = isValidBST(twnty)
//        println(isBST)
    }

    //a,b,d,e,c,f,g
    private fun preOrderTraversalRecursion(root:TreeNode<Char>?){
        if(root == null)return
        println("preorder traversing item is ${root.data}")
        preOrderTraversalRecursion(root.left)
        preOrderTraversalRecursion(root.right)
    }

    //a,b,d,e,c,f,g
    private fun preOrderTraversalIteration(root:TreeNode<Char>?){
        if(root == null)
            return
        val stack = MyStack<TreeNode<Char>>()
        var temp = root
        while (true){
            while (temp != null){
                println("preorder traversing item is ${temp.data}")
                stack.push(temp)
                temp = temp.left
            }
            if(stack.isEmpty())
                break
            val popped = stack.pop()
            temp = popped?.right
        }
    }

    //d,b e,a,f,c,g
    private fun inOrderTraversalRecursion(root:TreeNode<Char>?){
        if(root == null)return
        inOrderTraversalRecursion(root.left)
        println("inorder traversing item is ${root.data}")
        inOrderTraversalRecursion(root.right)
    }

    //d,b e,a,f,c,g
    private fun inOrderTraversalIteration(root:TreeNode<Char>?){
        if(root == null)
            return
        val stack = MyStack<TreeNode<Char>>()
        var temp = root
        while (true){
            while (temp != null){
                stack.push(temp)
                temp = temp.left
            }
            if(stack.isEmpty())
                break
            val popped = stack.pop()
            println("inorder traversing item is ${popped?.data}")
            temp = popped?.right
        }
    }

    //d,e b,f,g,c,a
    private fun postOrderTraversalRecursion(root:TreeNode<Char>?){
        if(root == null)return
        postOrderTraversalRecursion(root.left)
        postOrderTraversalRecursion(root.right)
        println("postorder traversing item is ${root.data}")
    }

    //d,e b,f,g,c,a
    private fun postOrderTraversalIteration(root:TreeNode<Char>?){
        if(root == null)
            return
        val stack = MyStack<TreeNode<Char>?>()
        stack.push(root)
        var prev:TreeNode<Char>? = null
        while(!stack.isEmpty()){
            val current:TreeNode<Char>? = stack.peek()
            if(prev == null || prev.left == current || prev.right == current){
                if(current?.left != null){
                    stack.push(current.left)
                }else if(current?.right != null){
                    stack.push(current.right)
                }
            }else if(current?.left == prev){
                if(current.right != null) {
                    stack.push(current.right)
                }
            }else{
                val popped = stack.pop()
                println("postorder traversing item is ${popped?.data}")
            }
            prev = current
        }
    }

    //a,b,c,d,e,f,g
    //with level order traversal logic we can search, search max element, find size
    private fun levelOrderTraversal(root: TreeNode<Char>?){
        if(root == null)
            return
        val queue:MyQueue<TreeNode<Char>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            println("levelorder traversing item is ${deQueued?.data}")
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }
            if(deQueued?.right != null){
                queue.enqueue(deQueued.right)
            }
        }
    }

    fun levelOrderLeetCode(root: TreeNode<Int>?): List<List<Int>> {
        if(root == null)
            return emptyList()
        val parentList = mutableListOf<List<Int>>()
        var childList = mutableListOf<Int>()
        val queue:Queue<TreeNode<Int>?> = LinkedList()
        queue.offer(root)
        queue.offer(null)
        parentList.add(listOf(root.data!!))
        while (!queue.isEmpty()){
            val deQueued = queue.poll()
            if(deQueued == null){
                if(!queue.isEmpty()){
                    val copy: MutableList<Int> = ArrayList()
                    copy.addAll(childList)
                    parentList.add(copy)
                    childList.clear()
                    childList = mutableListOf()

                    queue.offer(null)
                }
            }else{
                deQueued.left?.let{
                    childList.add(it.data!!)
                    queue.offer(it)
                }
                deQueued.right?.let{
                    childList.add(it.data!!)
                    queue.offer(it)
                }
            }
        }
        return parentList
    }

    //d,e,f,g,b,c,a
    private fun levelOrderReverseOrderTraversal(root: TreeNode<Char>?){
        if(root == null)
            return
        val stack = MyStack<TreeNode<Char>>()
        val queue:MyQueue<TreeNode<Char>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued?.right != null){
                queue.enqueue(deQueued.right)
            }
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }
            stack.push(deQueued!!)
        }
        while (!stack.isEmpty()){
            println("LevelOrder Reverse item is ${stack.pop()?.data}")
        }
    }

    private fun findMaximumElementBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        val value = root.data
        val leftValue = findMaximumElementBT(root.left)
        val rightValue = findMaximumElementBT(root.right)
        var maxValue = 0
        if(leftValue > rightValue)
            maxValue = leftValue
        else
            maxValue = rightValue

        if(value!! > maxValue){
            maxValue = value
        }
        return maxValue
    }

    private fun findMaximumElementIterationBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0
        var maxValue = 0
        val queue:MyQueue<TreeNode<Int>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued?.data!! > maxValue){
                maxValue = deQueued.data!!
            }
            if(deQueued.left != null){
                queue.enqueue(deQueued.left)
            }
            if(deQueued.right != null){
                queue.enqueue(deQueued.right)
            }
        }

        return maxValue
    }

    private fun searchElementBTRecursion(root: TreeNode<Int>?, key:Int):Boolean{
        if(root == null)return false

        if(root.data == key){
            return true
        }

        val isLeft =  searchElementBTRecursion(root.left, key)
        val isRight = searchElementBTRecursion(root.right, key)
        return isLeft || isRight
    }

    private fun searchElementBTIteration(root: TreeNode<Int>?, key: Int):Boolean{
        if(root == null)
            return false
        val queue:Queue<TreeNode<Int>> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()){
            val dequeued = queue.poll()
            if(dequeued?.data == key){
                return true
            }
            if(dequeued?.left != null)
                queue.offer(dequeued.left)
            if(dequeued?.right != null)
                queue.offer(dequeued.right)
        }
        return false
    }

    private fun insertElementIteration(root: TreeNode<Int>?, key:Int){
        if(root == null)
            return
        val newNode = TreeNode(key)
        val queue:MyQueue<TreeNode<Int>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }else{
                deQueued?.left = newNode
                return
            }
            if(deQueued.right != null){
                queue.enqueue(deQueued.right)
            }else{
                deQueued.right = newNode
                return
            }
        }
    }

    private fun sizeOfBTRecursion(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0
        return sizeOfBTRecursion(root.left) + 1 + sizeOfBTRecursion(root.right)
    }

    private fun sizeOfBTIteration(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0
        var count = 0
        val queue:Queue<TreeNode<Int>?> = LinkedList()
        queue.offer(root)
        while (!queue.isEmpty()){
            count++
            val deQueued = queue.poll()
            if(deQueued?.left != null){
                queue.offer(deQueued.left)
            }
            if(deQueued?.right != null){
                queue.offer(deQueued.right)
            }
        }
        return count
    }

    private fun heightOfBTRecursion(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        val leftHeight = heightOfBTRecursion(root.left)
        val rightHeight = heightOfBTRecursion(root.right)

        return if(leftHeight > rightHeight)
            leftHeight + 1
        else
            rightHeight + 1
    }

    private fun heightOfBTIteration(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        var level = 0

        val queue= MyQueue<TreeNode<Int>?>()
        queue.enqueue(root)
        queue.enqueue(null)

        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued == null){
                if(!queue.isEmpty()){
                    queue.enqueue(null)
                }
                level++
            }else{
                if(deQueued.left != null){
                    queue.enqueue(deQueued.left)
                }
                if(deQueued.right != null){
                    queue.enqueue(deQueued.right)
                }
            }
        }

        return level
    }

    private fun findDeepestNodeOfATree(root:TreeNode<Int>?):TreeNode<Int>?{
        if(root == null){
            return root
        }

        val queue:Queue<TreeNode<Int>?> = LinkedList()
        queue.offer(root)
        var temp:TreeNode<Int>? = null

        while (!queue.isEmpty()){
            temp = queue.poll()
            if(temp?.left != null){
                queue.offer(temp.left)
            }
            if(temp?.right != null){
                queue.offer(temp.right)
            }
        }

        return temp
    }

    private fun deleteANodeFromBT(root: TreeNode<Int>?, node:TreeNode<Int>){
        if(root?.data == node.data){
            if(root?.left != null && root.right != null){
                val leftDeepestNode = findDeepestNodeOfATree(root.left)
                root.data = leftDeepestNode?.data!!
                leftDeepestNode.apply {
                    left = null
                    right = null
                    data = null
                }
            }else if(root?.left == null && root?.right == null) {
                root?.apply {
                    left = null
                    right = null
                    data = null
                }
            }else if(root.left == null){
                val rightNode = root.right
                root.apply {
                    left = rightNode?.left
                    right = rightNode?.right
                    data = rightNode?.data
                }
            }else{
                val leftNode = root.left
                root.apply {
                    left = leftNode?.left
                    right = leftNode?.right
                    data = leftNode?.data
                }
            }
            return
        }else{
            deleteANodeFromBT(root?.left, node)
            deleteANodeFromBT(root?.right, node)
        }
    }

    private fun countLeavesInBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        var counter = 0
        val queue:MyQueue<TreeNode<Int>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued?.left == null && deQueued?.right == null){
                counter++
            }
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }
            if(deQueued?.right != null){
                queue.enqueue(deQueued.right)
            }
        }
        return counter
    }

    private fun countHalfNodesInBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        var counter = 0
        val queue:MyQueue<TreeNode<Int>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if((deQueued?.left == null && deQueued?.right != null) || (deQueued?.right == null && deQueued?.left != null)){
                counter++
            }
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }
            if(deQueued?.right != null){
                queue.enqueue(deQueued.right)
            }
        }
        return counter
    }

    private fun countFullNodesInBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0

        var counter = 0
        val queue:MyQueue<TreeNode<Int>?> = MyQueue()
        queue.enqueue(root)
        while (!queue.isEmpty()){
            val deQueued = queue.dequeue()
            if(deQueued?.left != null && deQueued.right != null){
                counter++
            }
            if(deQueued?.left != null){
                queue.enqueue(deQueued.left)
            }
            if(deQueued?.right != null){
                queue.enqueue(deQueued.right)
            }
        }
        return counter
    }

    private fun areBTsIdentical(root1: TreeNode<Int>?, root2: TreeNode<Int>?):Boolean{
        if(root1 == null && root2 == null){
            return true
        }else if(root1 == null || root2 == null){
            return false
        }else{
            return (root1.data == root2.data)
                    && areBTsIdentical(root1.left, root2.left)
                    && areBTsIdentical(root1.right, root2.right)
        }
    }

    private fun findShortestPath(root: TreeNode<Int>?):Int{
        if(root == null)return 0
        if(root.left == null && root.right == null)
            return 1
        else if(root.left == null){
            return findShortestPath(root.right) + 1
        } else if(root.right == null){
            return findShortestPath(root.left) + 1
        }else{
            return Math.min(findShortestPath(root.left), findShortestPath(root.right)) + 1
        }
    }

    private fun findLevelThatHasMaxSum(root: TreeNode<Int>?):Int{
        if(root == null)return -1

        var level = 0; var maxLevel = 0; var sum = 0; var maxSum = 0
        val queue:Queue<TreeNode<Int>?> = LinkedList()
        queue.offer(root)
        queue.offer(null)
        while (!queue.isEmpty()){
            val dequeued = queue.poll()
            if(dequeued == null){
                if(sum > maxSum){
                    maxSum = sum
                    maxLevel = level
                }
                level++
                sum = 0
                if(!queue.isEmpty()){
                    queue.offer(null)
                }
            }else{
                sum += dequeued.data!!
                dequeued.left?.let{
                    queue.offer(it)
                }
                dequeued.right?.let{
                    queue.offer(it)
                }
            }
        }
        return maxLevel
    }

    private fun printAllPathsFromRooToLeaf(root: TreeNode<Int>?, pathArr:IntArray, pathIndex:Int){
        if(root == null)
            return

        var localPathIndex = pathIndex

        pathArr[localPathIndex++] =  root.data!!

        if(root.left == null && root.right == null){
            print("path ")
            for (i in pathArr.indices){
                if(pathArr[i] > 0)
                    print("${pathArr[i]}, ")
            }
            println()
        }else{
            printAllPathsFromRooToLeaf(root.left, pathArr, localPathIndex)
            printAllPathsFromRooToLeaf(root.right, pathArr, localPathIndex)
        }
    }

    private fun checkIfSumExistsInAnyPath(root: TreeNode<Int>?, pathArr:IntArray, pathIndex:Int, sum:Int):Boolean{
        if(root == null)
            return false

        var localPathIndex = pathIndex

        pathArr[localPathIndex++] =  root.data!!

        if(root.left == null && root.right == null){
            print("path ")
            var localSum = 0
            for (i in pathArr.indices){
                localSum += pathArr[i]
                if(pathArr[i] > 0)
                    print("${pathArr[i]}, ")
            }
            println()
            return localSum == sum
        }else{
            return checkIfSumExistsInAnyPath(root.left, pathArr, localPathIndex, sum)
                    || checkIfSumExistsInAnyPath(root.right, pathArr, localPathIndex, sum)
        }
    }

    private fun checkIfSumExistsInAnyPathApproach2(root: TreeNode<Int>?, sum:Int):Boolean{
        if(root == null)
            return sum == 0

        val remaining = sum - root.data!!
        return checkIfSumExistsInAnyPathApproach2(root.left, remaining) || checkIfSumExistsInAnyPathApproach2(root.right, remaining)
    }

    private fun sumOfAllElementsBT(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0
        return root.data!! + sumOfAllElementsBT(root.left) + sumOfAllElementsBT(root.right)
    }

    private fun mirrorOfATree(root: TreeNode<Int>?):TreeNode<Int>?{
        if(root != null){
            mirrorOfATree(root.left)
            mirrorOfATree(root.right)
            val temp = root.left
            root.left = root.right
            root.right = temp
        }
        return root
    }

    private fun leastCommonAncestorOfTwoNodes(root: TreeNode<Int>?, key1:Int, key2:Int):TreeNode<Int>?{
        if(root == null)
            return null

        if(root.data == key1 || root.data == key2){
            return root
        }else{
            val left = leastCommonAncestorOfTwoNodes(root.left, key1, key2)
            val right = leastCommonAncestorOfTwoNodes(root.right, key1, key2)
            return if(left != null && right != null){
                root
            }else if(left != null){
                left
            }else{
                right
            }
        }
    }

    private fun isAncestorsOfANode(root: TreeNode<Int>?, key: Int):Boolean{
        if(root == null)
            return false
        return if(root.data == key || isAncestorsOfANode(root.left, key) || isAncestorsOfANode(root.right, key)){
            println(root.data)
            true
        }else{
            false
        }
    }

    private fun zigzagTraversal(root: TreeNode<Int>?){
        if(root == null)return

        var stack = Stack<TreeNode<Int>?>()
        stack.push(root)
        var auxStack = Stack<TreeNode<Int>?>()
        var isLeft = true
        while (!stack.isEmpty()){
            val popped = stack.pop()
            println("${popped?.data!!}, ")
            if(isLeft) {
                if (popped.left != null) {
                    auxStack.push(popped.left)
                }
                if (popped.right != null) {
                    auxStack.push(popped.right)
                }
            }else{
                if (popped.right != null) {
                    auxStack.push(popped.right)
                }
                if (popped.left != null) {
                    auxStack.push(popped.left)
                }
            }

            if(stack.isEmpty()){
                val temp = stack
                stack = auxStack
                auxStack = temp
                isLeft = !isLeft
            }
        }
    }

    private fun verticalSumOfBT(root: TreeNode<Int>?, index:Int, map:HashMap<Int, Int>):HashMap<Int, Int>{
        if(root == null)
            return map

        val sum = if(map.containsKey(index)) map.get(index)!! else 0
        map.put(index, sum + root.data!!)
        verticalSumOfBT(root.left, index - 1, map)
        verticalSumOfBT(root.right, index + 1, map)
        return map
    }

    private fun expressionTree(expression: String):TreeNode<Char>?{
        val stack: Stack<TreeNode<Char>?> = Stack()
        expression.forEach {
            if(isOperator(it)){
                val pop1 = stack.pop()
                val pop2 = stack.pop()
                if(pop1 == null || pop2 == null){
                    return null
                }
                val node = TreeNode(it, pop1, pop2)
                stack.push(node)
            }else{
                stack.push(TreeNode(it))
            }
        }
        return stack.pop()
    }

    private fun isOperator(char: Char): Boolean {
        return char == '+' || char == '-' || char == '/' || char == '*'
    }

    private fun constructBTFromArray(arr:IntArray):TreeNode<Int>?{
        var tree:TreeNode<Int>? = null
        val size = arr.size
        var index = 0
        val queue:Queue<TreeNode<Int>> = LinkedList()
        queue.offer(TreeNode(arr[index++]))
        while (index < size){
            val node = queue.poll()
            if(tree == null){
                tree = node
            }
            node?.left = TreeNode(arr[index++])
            queue.offer(node?.left)

            if(index < size){
                node?.right = TreeNode(arr[index++])
                queue.offer(node?.right)
            }else{
                node?.right = null
            }
        }
        return tree
    }

    //preorder = ('a','b','d','e','c','f')
    //inorder = ('d','b','e','a','f','c')
    //read https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/ for detailed explanation
    private var preOrderIndexGlobal = 0
    private fun constructBTFromIOArrayAndPOArrayChar(inOrderArr:CharArray, preOrderArr:CharArray, start:Int, end:Int):TreeNode<Char>?{
        if(start > end){
           return null
        }
        val data = preOrderArr[preOrderIndexGlobal++]
        val node = TreeNode(data)
        if(start == end)
            return node

        val inorderIndex = getInOrderIndexChar(inOrderArr, start, end, data)
        node.left = constructBTFromIOArrayAndPOArrayChar(inOrderArr, preOrderArr, start, inorderIndex - 1)
        node.right = constructBTFromIOArrayAndPOArrayChar(inOrderArr, preOrderArr, inorderIndex + 1, end)
        return node
    }

    private fun getInOrderIndexChar(inOrderArr: CharArray, inStart: Int, inEnd: Int, data: Char): Int {
        for (i in inStart..inEnd){
            if(inOrderArr[i] == data){
                return i
            }
        }
        return 0
    }

    private fun constructBTFromIOArrayAndPOArrayInt(inOrderArr:IntArray, preOrderArr:IntArray, start:Int, end:Int):TreeNode<Int>?{
        if(start > end){
            return null
        }
        val data = preOrderArr[preOrderIndexGlobal++]
        val node = TreeNode(data)
        if(start == end)
            return node

        val inorderIndex = getInOrderIndexInt(inOrderArr, start, end, data)
        node.left = constructBTFromIOArrayAndPOArrayInt(inOrderArr, preOrderArr, start, inorderIndex - 1)
        node.right = constructBTFromIOArrayAndPOArrayInt(inOrderArr, preOrderArr, inorderIndex + 1, end)
        return node
    }

    private fun getInOrderIndexInt(inOrderArr: IntArray, inStart: Int, inEnd: Int, data: Int): Int {
        for (i in inStart..inEnd){
            if(inOrderArr[i] == data){
                return i
            }
        }
        return 0
    }

    //    inOrderArr = intArrayOf(4, 8, 10, 12, 14, 20, 22)
//    levelOrderArr = intArrayOf(20, 8, 22, 4, 12, 10, 14)
    private fun constructBTFromIOAndLOArray(inOrderArr:IntArray, levelOrderArr:IntArray, inStart:Int, inEnd:Int, node: TreeNode<Int>?):TreeNode<Int>?{
        var tempNode = node
        if(inStart > inEnd){
            return null
        }

//        val data = inOrderArr[inStart]
//        val node = TreeNode(data)
        if (inStart == inEnd)
            return TreeNode(inOrderArr[inStart])

        var found = false
        var index = 0

        for (i in 0 until levelOrderArr.size - 1) {
            val data: Int = levelOrderArr[i]
            for (j in inStart until inEnd) {
                if (data == inOrderArr.get(j)) {
                    tempNode = TreeNode(data)
                    index = j
                    found = true
                    break
                }
            }
            if (found)
                break
        }

        tempNode?.left = constructBTFromIOAndLOArray(inOrderArr, levelOrderArr, inStart, index - 1, tempNode)
        tempNode?.right = constructBTFromIOAndLOArray(inOrderArr, levelOrderArr,index + 1, inEnd, tempNode)
        return tempNode
    }

    private fun searchElementBSTRecursion(root: TreeNode<Int>?, key:Int):TreeNode<Int>?{
        if(root == null)
            return null

        if(root.data!! > key)
            return searchElementBSTRecursion(root.left, key)
        else if(root.data!! < key)
            return searchElementBSTRecursion(root.right, key)
        else
            return root
    }

    private fun searchElementBSTIteration(root: TreeNode<Int>?, key:Int):TreeNode<Int>?{
        if(root == null)
            return null
        var temp = root
        while (temp != null){
            if(temp.data!! > key){
                temp = temp.left
            }else if(temp.data!! < key){
                temp = temp.right
            }else{
                return temp
            }
        }
        return null
    }

    private fun findMinBST(root: TreeNode<Int>?):TreeNode<Int>?{
        if(root == null)return null
        return if(root.left == null)
            root
        else
            findMinBST(root.left)
    }

    private fun findMaxBST(root: TreeNode<Int>?):TreeNode<Int>?{
        if(root == null)return null
        return if(root.right == null)
            root
        else
            findMaxBST(root.right)
    }

    private fun inOrderSuccessorBST(root: TreeNode<Int>?, node:TreeNode<Int>?):TreeNode<Int>?{
        if(root == null || node == null)return null

        if(node.right != null) {
            return findMinBST(node.right)
        }else{
            var successor:TreeNode<Int>? = null
            var temp = root
            while (temp != null){
                if (node.data!! < temp.data!!) {
                    successor = temp
                    temp = temp.left
                }
                else if (node.data!! > temp.data!!)
                    temp = temp.right
                else
                    break;
            }
            return successor
        }
    }

    private fun inOrderPredecessorBST(root: TreeNode<Int>?, node:TreeNode<Int>?):TreeNode<Int>?{
        if(root == null || node == null)return null

        if(node.left != null) {
            return findMaxBST(node.left)
        }else{
            var successor:TreeNode<Int>? = null
            var temp = root
            while (temp != null){
                if (node.data!! < temp.data!!) {
                    temp = temp.left
                }
                else if (node.data!! > temp.data!!) {
                    successor = temp
                    temp = temp.right
                }
                else
                    break;
            }
            return successor
        }
    }

    private fun insertElementBST(root: TreeNode<Int>?, key: Int):TreeNode<Int>{
        var temp = root
        if(temp == null){
            temp = TreeNode(key)
        }else if(temp.data!! > key){
            temp.left = insertElementBST(temp.left, key)
        }else if(temp.data!! < key){
            temp.right = insertElementBST(temp.right, key)
        }
        return temp
    }

    private fun deleteElementBST(root: TreeNode<Int>?, key: Int):TreeNode<Int>?{
        var temp = root
        if(temp == null)return null
        else if(temp.data!! > key){
            temp.left = deleteElementBST(temp.left, key)
        }else if(temp.data!! < key){
            temp.right = deleteElementBST(temp.right, key)
        }else{
//            case 1
            if(temp.left == null && temp.right == null){
                temp = null
            }else if(temp.left == null){
                temp = temp.right
            }else if(temp.right == null){
                temp = temp.left
            }else{
                val minRight = findMinBST(temp.right)
                temp.data = minRight?.data
                temp.right = deleteElementBST(temp.right, minRight?.data!!)
            }
        }
        return temp
    }

    private fun diameterOfBST(root: TreeNode<Int>?):Int{
        if(root == null)
            return 0
        val leftHeight = heightOfBTRecursion(root.left)
        val rightHeight = heightOfBTRecursion(root.right)
        val leftDiameter = diameterOfBST(root.left)
        val rightDiameter = diameterOfBST(root.right)
        return Math.max((leftHeight + rightHeight + 1), Math.max(leftDiameter, rightDiameter))
    }

    var kthSmallestCounter = 0
    private fun kthSmallestElementBST(root: TreeNode<Int>?, k:Int):TreeNode<Int>?{
        if(root == null)
            return null

        val left = kthSmallestElementBST(root.left, k)

        if(left != null)
            return left

        kthSmallestCounter++

        if(k == kthSmallestCounter)
            return root

        return kthSmallestElementBST(root.right, k)
    }

    private fun pruneBST(root: TreeNode<Int>?, a:Int, b:Int):TreeNode<Int>?{
        if(root == null)
            return null

        root.left = pruneBST(root.left, a, b)
        root.right = pruneBST(root.right, a, b)

        if(root.data!! < a){
            return root.right
        }

        if(root.data!! > b){
            return root.left
        }

        return root
    }

    private fun isBST(root: TreeNode<Int>?):Boolean{
        if(root == null)return true

        /* False if left is > than node */
        if (root.left != null && root.left?.data!! >= root.data!!)
            return false

        /* False if left is > than node */
        if (root.right != null && root.right?.data!! <= root.data!!)
            return false

        if (!isBST(root.left) || !isBST(root.right))
            return false

        return true
    }

    private fun isValidBST(root: TreeNode<Int>?): Boolean {
        fun isValid(curr: TreeNode<Int>?, low: Long, high: Long): Boolean{
            if(curr == null) return true
            val inRange = curr.data!! > low  && curr.data!! < high
            val left = isValid(curr.left, low, curr.data!!.toLong())
            val right = isValid(curr.right, curr.data!!.toLong(), high)
            return inRange && left && right
        }
        return isValid(curr = root, low = Long.MIN_VALUE, high = Long.MAX_VALUE)
    }
}

data class TreeNode<T>(var data:T?, var left:TreeNode<T>? = null, var right:TreeNode<T>? = null)