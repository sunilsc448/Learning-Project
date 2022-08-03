package DSAlgo

import pojos.ListNode

class LinkedListSamples {

    init {

    }

    private fun getNode1(): ListNode {
        var node1 = ListNode(9)
        var node2 = ListNode(9)
        var node3 = ListNode(9)
        var node4 = ListNode(9)
        var node5 = ListNode(9)
        var node6 = ListNode(9)
        var node7 = ListNode(9)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node7
        return node1
    }

    private fun getNode2(): ListNode {
        var node1 = ListNode(9)
        var node2 = ListNode(9)
        var node3 = ListNode(9)
        var node4 = ListNode(9)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        return node1
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var list1 = l1; var list2 = l2
        val head = ListNode(0)
        var current: ListNode? = head

        var carry = 0
        while (list1 != null && list2 != null){
            var sum = list1.`val` + list2.`val` + carry
            carry = sum / 10
            sum %= 10
            val newNode = ListNode(sum)
            current?.next = newNode
            current = current?.next
            list1 = list1.next
            list2 = list2.next
        }

        if(list1 != null){
            if(carry != 0){
                current?.next = addTwoNumbers(list1, ListNode(carry))
            }else{
                current?.next = list1
            }
        }else if(list2 != null){
            if(carry != 0){
                current?.next = addTwoNumbers(list2, ListNode(carry))
            }else{
                current?.next = list2
            }
        }else if(carry != 0){
            current?.next = ListNode(carry)
        }

        return head.next
    }

    fun isPalindrome(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }
        slow = reverse(slow)
        fast = head
        while (slow != null) {
            if (slow.`val` != fast!!.`val`) {
                return false
            }
            slow = slow.next
            fast = fast.next
        }
        return true
    }

    fun reverse(node: ListNode?): ListNode? {
        var node = node
        var prev: ListNode? = null
        while (node != null) {
            val newNode = node.next
            node.next = prev
            prev = node
            node = newNode
        }
        return prev
    }
}