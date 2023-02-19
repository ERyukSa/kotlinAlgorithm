package week9

import java.util.StringTokenizer

private class Node(val food: String, val children: MutableMap<String, Node> = HashMap()) {
    override fun hashCode(): Int {
        return food.hashCode()
    }
}

private val resultSb = StringBuilder() // 결과 전체 문자열

fun main() {
    val pathCount = readln().toInt()
    val routeNode = Node("root")

    repeat(pathCount) {
        val st = StringTokenizer(readln())
        var node = routeNode

        repeat(st.nextToken().toInt()) {
            val food = st.nextToken()
            if (node.children.containsKey(food).not()) {
                node.children[food] = Node(food)
            }
            node = node.children[food]!!
        }
    }

    routeNode.children.values.sortedBy { it.food }.forEach { childNode ->
        dfsPrint(childNode, 0)
    }

    print(resultSb.toString())
}

private fun dfsPrint(node: Node, level: Int) {
    repeat(2 * level) {
        resultSb.append("-")
    }
    resultSb.append("${node.food}\n")

    node.children.values.sortedBy { it.food }.forEach { childNode ->
        dfsPrint(childNode, level + 1)
    }
}