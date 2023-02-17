package week9

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/131703
 */
class SolutionReverseCoin {

    lateinit var source: Array<IntArray>
    lateinit var destination: Array<IntArray>
    val rowSize by lazy { source.size }
    val colSize by lazy { source[0].size }
    var minReverseCount = 21

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        source = beginning
        destination = target
        reverse(0, 0)
        return if (minReverseCount > 20) -1 else minReverseCount
    }

    fun reverse(i: Int, reverseCount: Int) {
        if (i >= rowSize + colSize) {
            if (doSourceEqualsDestination()) {
                minReverseCount = minOf(minReverseCount, reverseCount)
            }
            return
        }

        reverse(i + 1, reverseCount) // 안뒤집고 다음 행,열로 넘어감

        // 행 or 열 뒤집고 다음으로 넘어가고 되돌아와서 원상복귀 시키기
        if (i < rowSize) {
            source.reverseRow(i)
            reverse(i + 1, reverseCount + 1)
            source.reverseRow(i)
        } else {
            source.reverseCol(i - rowSize)
            reverse(i + 1, reverseCount + 1)
            source.reverseCol(i - rowSize)
        }
    }

    fun doSourceEqualsDestination(): Boolean {
        for (i in source.indices) {
            for (j in source[0].indices) {
                if (source[i][j] != destination[i][j]) return false
            }
        }

        return true
    }
}

fun Array<IntArray>.reverseRow(row: Int) {
    for (col in this[row].indices) {
        this[row][col] = 1 - this[row][col]
    }
}

fun Array<IntArray>.reverseCol(col: Int) {
    for (row in this.indices) {
        this[row][col] = 1 - this[row][col]
    }
}