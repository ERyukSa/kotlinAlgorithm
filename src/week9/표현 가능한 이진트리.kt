package week9

import java.util.LinkedList

class Solution {
    fun solution(numbers: LongArray): IntArray {
        return numbers
            .map(this::convertToBinary)
            .map { if (isValidTree(it, 0, it.lastIndex)) 1 else 0 }
            .toIntArray()
    }

    private fun isValidTree(binaryCode: String, start: Int, end: Int): Boolean {
        if (start == end) {
            return true
        }

        val mid = (start + end) / 2
        if (binaryCode[mid] == '0') {
            return false
        }

        return isValidTree(binaryCode, start, mid - 1)  && isValidTree(binaryCode, mid + 1, end)
    }

    private fun convertToBinary(num: Long): String {
        val linkedList = LinkedList<Long>()
        var number = num
        while (number > 1) {
            linkedList.addFirst(number % 2)
            number /= 2
        }
        linkedList.addFirst(number)
        if (linkedList.size % 2 == 0) linkedList.addFirst(0)
        return linkedList.joinToString("")
    }
}