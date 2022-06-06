package KotlinSamples

class LinkedListImplementation {
    init {
        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)
        node1.next = node2
        node2.next = node3

        println("Linked node is $node1")

        val list = MyLinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)

        //optimised way
//        list.push(1).push(2).push(3)

        list.append(4)
        list.append(5)

        println("Linked list $list")

        val nodeAt = list.nodeAt(0)

        println("Linked nodeat0 $nodeAt")

        list.insertAfter(4, nodeAt!!)

        println("Linked list after insert nodeAt0 $list")

        list.pop()

        println("Linked list after pop $list")

        list.pop()
        list.push(1)

        println("Linked list after pop and push $list")

        list.remove(list.nodeAt(3)!!)

        println("Linked list after remove $list")

        list.removeAfter(list.nodeAt(1)!!)

        println("Linked list after remove $list")

        list.popLast()
        list.popLast()

        println("Linked list after pop last pop last $list")
    }
}

class MyLinkedList<T>{
    private var head:Node<T>? = null
    private var tail:Node<T>? = null
    private var size = 0

    fun isEmpty() = (size == 0)

    override fun toString(): String {
        return if(isEmpty())"Empty List" else head.toString()
    }

    //TC : O(1)
    fun push(input:T):MyLinkedList<T>{
        head = Node(input, head)
        if(tail == null)
            tail = head
        size++

        //better way
        return this
    }

    //TC : O(1)
    fun append(input:T){
        if(isEmpty()) {
            push(input)
            return
        }

        tail?.next = Node(input)
        tail = tail?.next
        size++
    }

    //TC : O(index)
    fun nodeAt(index:Int):Node<T>?{
        var currentNode = head
        var currentIndex = 0
        while (currentNode != null && currentIndex < index){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    //TC : O(1)
    fun insertAfter(input:T, afterNode:Node<T>):Node<T>{
        if(afterNode == tail){
            append(input)
            return tail!!
        }
        val newNode = Node(input, afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    //TC: O(1)
    fun pop():T?{
        if(!isEmpty())size--
        val poppedNode = head
        head = head?.next

        if(isEmpty())
            tail = null

        return poppedNode?.value
    }

    fun popLast():T?{
        val head = head ?: return null
        if(head.next == null)return pop()
        size--

        var current = head
        var prev = head
        var next = head.next
        while (next != null){
            prev = current
            current = next
            next = current.next
        }

        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(input:Node<T>):T?{
        val result = input.next?.value
        if (input.next == tail) {
            tail = input
        }

        if (input.next != null) {
            size--
        }
        input.next = input.next?.next
        return result
    }

    fun remove(input:Node<T>){
        if(input == head){
            head = head?.next
            return
        }
        var current  = head
        var prev = head

        while (current != null){
            prev = current
            current = current.next
            if(current == input){
                break
            }
        }

        prev?.next = current?.next
    }
}

class Node<T>(val value:T, var next:Node<T>? = null){
    override fun toString(): String {
        return if (next == null)
            value.toString()
        else
             "$value -> ${next}"
    }
}

class Node2<T>(val value: T, var next: Node2<T>? = null ,var prev:Node2<T>? = null){
//    override fun toString(): String {
//        return if(next == null){
//            value.toString()
//        }else{
//            "$value -> prev: $prev, next:$next"
//        }
//    }
}

