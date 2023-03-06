package solo

data class Edge(val nextDir: Int, val cost: Int)

private lateinit var minCostSum: Array<Array<IntArray>>
private lateinit var numbers: IntArray
private val cost = arrayOf(
    intArrayOf(0, 2, 2, 2, 2),
    intArrayOf(0, 1, 3, 4, 3),
    intArrayOf(0, 3, 1, 3, 4),
    intArrayOf(0, 4, 3, 1, 3),
    intArrayOf(0, 3, 4, 3, 1)
)

fun main() {
    numbers = intArrayOf(0) + readln().split(" ").map(String::toInt).toIntArray()
    minCostSum = Array(numbers.size) {
        Array(5) {
            IntArray(5) { -1 }
        }
    }
    print(getMinSum(0, 0, 0))
}

private fun getMinSum(idx: Int, mainFoot: Int, supportFoot: Int): Int {
    if (minCostSum[idx][mainFoot][supportFoot] != -1) {
        return minCostSum[idx][mainFoot][supportFoot]
    }
    if (idx == numbers.lastIndex) {
        return 0
    }

    val nextFoot = numbers[idx + 1]
    val minCostSumFromHere = minOf(
        cost[mainFoot][nextFoot] + getMinSum(idx + 1, nextFoot, supportFoot),
        cost[supportFoot][nextFoot] + getMinSum(idx + 1, nextFoot, mainFoot)
    )
    minCostSum[idx][mainFoot][supportFoot] = minCostSumFromHere
    return minCostSumFromHere
}