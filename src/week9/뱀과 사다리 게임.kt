package week9

import java.util.LinkedList
import java.util.StringTokenizer

sealed interface BoardType16928
data class Ladder16928(val toIdx: Int) : BoardType16928
data class Snake16928(val toIdx: Int) : BoardType16928
object Space16928 : BoardType16928

data class Node16928(val idx: Int, val visitCount: Int)

fun main() {
    val board = Array<BoardType16928>(101) { Space16928 }
    val visitOrder = IntArray(101) {  Int.MAX_VALUE }
    val (ladderCount, snakeCount) = readln().split(" ").map(String::toInt)

    repeat(ladderCount) {
        val st = StringTokenizer(readln())
        val ladderStart = st.nextToken().toInt()
        board[ladderStart] = Ladder16928(st.nextToken().toInt())
    }
    repeat(snakeCount) {
        val st = StringTokenizer(readln())
        val snakeStart = st.nextToken().toInt()
        board[snakeStart] = Snake16928(st.nextToken().toInt())
    }

    val queue = LinkedList<Node16928>()
    visitOrder[1] = 0
    queue.offer(Node16928(1, 0))

    while (queue.isNotEmpty()) {
        val (currentIdx, visitCount) = queue.poll()

        for (i in 1..6) {
            val nextIdx = when (board[currentIdx + i]) {
                is Ladder16928 -> (board[currentIdx + i] as Ladder16928).toIdx
                is Snake16928 -> (board[currentIdx + i] as Snake16928).toIdx
                is Space16928 -> currentIdx + i
            }

            if (nextIdx == 100) {
                print(visitCount + 1)
                return
            }

            if (visitOrder[nextIdx] <= visitCount + 1) continue
            visitOrder[nextIdx] = visitCount + 1
            queue.offer(Node16928(nextIdx, visitCount + 1))
        }
    }
}