package solo

private lateinit var minCostSum: Array<IntArray>
private lateinit var cost: Array<IntArray>

fun main() {
    val n = readln().toInt()
    cost = Array(n) {
        readln().split(" ").map(String::toInt).toIntArray()
    }
    minCostSum = Array(n) {
        IntArray(1.shl(n)) { -1 }
    }

    print(getMinCostSumFrom(0, 1))
}

fun getMinCostSumFrom(city: Int, visitState: Int): Int {
    if (visitState == minCostSum[0].lastIndex) {
        return if (cost[city][0] == 0) Int.MAX_VALUE else cost[city][0]
    }
    if (minCostSum[city][visitState] != -1) {
        return minCostSum[city][visitState]
    }

    var costSumFromHere = Int.MAX_VALUE
    for (nextCity in cost.indices) {
        if (cost[city][nextCity] == 0) continue
        if ((visitState.shr(nextCity) % 2) == 1) continue
        val nextVisitState = visitState.or(1.shl(nextCity))
        val minCostSumFromNext = getMinCostSumFrom(nextCity, nextVisitState)
        if (minCostSumFromNext == Int.MAX_VALUE) continue
        costSumFromHere = minOf(costSumFromHere, cost[city][nextCity] + minCostSumFromNext)
    }

    minCostSum[city][visitState]  = costSumFromHere
    return costSumFromHere
}