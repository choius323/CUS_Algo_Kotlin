/*

1774번 - 우주신과의 교감
https://www.acmicpc.net/problem/1774
분류 : 



*/

package _20230528

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Edge(val u: Int, val v: Int, val weight: Double)
    data class Pos(val x: Int, val y: Int) {
        fun getDist(other: Pos): Double =
            kotlin.math.sqrt((x - other.x).toDouble() * (x - other.x) + (y - other.y).toDouble() * (y - other.y))
    }

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val parents = IntArray(n) { it }
    val posArray = Array(n) { Pos(r(), r()) }
    val edgeArray = mutableListOf<Edge>()

    fun findParent(a: Int): Int =
        if (parents[a] == a) a else findParent(parents[a])

    fun union(a: Int, b: Int): Boolean {
        val parentA = findParent(a)
        val parentB = findParent(b)

        if (parentA != parentB) {
            parents[parentB] = parentA
        }

        return parentA != parentB
    }

    repeat(m) {
        val u = r() - 1
        val v = r() - 1
        union(u, v)
    }
    for (a in 0 until n) for (b in a + 1 until n) {
        if (findParent(a) == findParent(b)) continue
        edgeArray.add(Edge(a, b, posArray[a].getDist(posArray[b])))
    }
    var sum = .0
    for ((u, v, weight) in edgeArray.sortedWith(compareBy { it.weight }).toTypedArray()) {
        if (union(u, v)) sum += weight
    }
    print(String.format("%.2f", sum))
}

/*

*/