package solo

import java.util.*

private lateinit var numbers: IntArray
private lateinit var operatorsCount: IntArray
private var maxResult = Int.MIN_VALUE
private var minResult = Int.MAX_VALUE

fun main() {
    val n = readln().toInt()
    numbers = readln().split(" ").map(String::toInt).toIntArray()
    operatorsCount = readln().split(" ").map(String::toInt).toIntArray()
    calculate(1, numbers[0])
    val a = Stack<Int>()
    println(maxResult)
    print(minResult)
}

private fun calculate(index: Int, accResult: Int) {
    if (index == numbers.size) {
        maxResult = maxOf(maxResult, accResult)
        minResult = minOf(minResult, accResult)
        return
    }

    if (operatorsCount[0] != 0) {
        operatorsCount[0]--
        calculate(index + 1, accResult + numbers[index])
        operatorsCount[0]++
    }

    if (operatorsCount[1] != 0) {
        operatorsCount[1]--
        calculate(index + 1, accResult - numbers[index])
        operatorsCount[1]++
    }

    if (operatorsCount[2] != 0) {
        operatorsCount[2]--
        calculate(index + 1, accResult * numbers[index])
        operatorsCount[2]++
    }

    if (operatorsCount[3] != 0) {
        operatorsCount[3]--
        val newResult = if (accResult < 0) {
            -(-accResult / numbers[index])
        } else {
            accResult / numbers[index]
        }
        calculate(index + 1, newResult)
        operatorsCount[3]++
    }
}