package week9

/**
 * 백준 9328번 열쇠
 * https://www.acmicpc.net/problem/9328
 * 그래프 탐색 + 구현 문제
 * DFS 사용시 탐색 리셋 구현이 더 복잡하고 느리기 때문에 BFS를 사용하는 게 좋다
 */
import java.util.LinkedList

data class Point(val y: Int, val x: Int)

fun main() {
    val t = readln().toInt()

    repeat(t) {
        val (height, width) = readln().split(" ").map(String::toInt)
        val building = Array(height + 2) {
            CharArray(width + 2) { '.' }
        }
        for (row in 1..height) {
            val eachRow = readln().toCharArray()
            for (col in eachRow.indices) {
                building[row][col + 1] = eachRow[col]
            }
        }

        val currentKeys = readln().filter { it != '0' }.toHashSet()
        println(getMaxDocumentCount(building, currentKeys))
    }
}

fun getMaxDocumentCount(building: Array<CharArray>, currentKeys: MutableSet<Char>): Int {
    val keyCountOnVisit = Array(building.size) {
        IntArray(building[0].size) { -1 }
    }
    val dRow = intArrayOf(-1, 1, 0, 0)
    val dCol = intArrayOf(0, 0, -1, 1)

    var documentCount = 0
    val q = LinkedList<Point>()
    q.offer(Point(0, 0))

    while (q.isNotEmpty()) {
        val (row, col) = q.poll()

        for (i in 0 until 4) {
            val nextRow = row + dRow[i]
            val nextCol = col + dCol[i]

            if (nextRow !in building.indices || nextCol !in building[0].indices || building[nextRow][nextCol] == '*') continue
            if (keyCountOnVisit[nextRow][nextCol] ==  currentKeys.size) continue

            keyCountOnVisit[nextRow][nextCol] = currentKeys.size

            if (building[nextRow][nextCol] == '$') {
                documentCount += 1
                building[nextRow][nextCol] = '.'
            } else if (building[nextRow][nextCol].isLowerCase()) {
                val key = building[nextRow][nextCol]
                building[nextRow][nextCol] = '.'
                if (currentKeys.contains(key).not()) {
                    currentKeys.add(key)
                    q.clear()
                    q.offer(Point(0, 0))
                    continue
                }
            } else if (building[nextRow][nextCol].isUpperCase()) {
                val key = building[nextRow][nextCol].lowercaseChar()
                if (currentKeys.contains(key).not()) continue
                building[nextRow][nextCol] = '.'
            }

            q.offer(Point(nextRow, nextCol))
        }
    }
    return documentCount
}