package solo

private lateinit var minCostSum: Array<IntArray>
private lateinit var ddr: IntArray
private val cost = arrayOf(
    intArrayOf(0, 2, 2, 2, 2),
    intArrayOf(0, 1, 3, 4, 3),
    intArrayOf(0, 3, 1, 3, 4),
    intArrayOf(0, 4, 3, 1, 3),
    intArrayOf(0, 3, 4, 3, 1)
)

fun main() {
    ddr = readln().split(" ").map(String::toInt).toIntArray()
    minCostSum = Array(ddr.size) {
        IntArray(5) { -1 }
    }
    print(2 + getMinSum(0, 0))
}

private fun getMinSum(i: Int, supportFoot: Int): Int {
    if (minCostSum[i][supportFoot] != -1) {
        return minCostSum[i][supportFoot]
    }
    if (i == ddr.lastIndex) {
        return 0
    }

    val currentTarget = ddr[i]
    val nextTarget = ddr[i + 1]
    var minCostSumFromHere = cost[currentTarget][nextTarget] + getMinSum(i + 1, supportFoot)
    if (currentTarget != nextTarget) {
        minCostSumFromHere = minOf(
            minCostSumFromHere,
            cost[supportFoot][nextTarget] + getMinSum(i + 1, currentTarget)
        )
    }

    minCostSum[i][supportFoot] = minCostSumFromHere
    return minCostSumFromHere
}