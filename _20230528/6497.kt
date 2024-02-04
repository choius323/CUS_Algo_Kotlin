/*

6497번 - 전력난
https://www.acmicpc.net/problem/6497
분류 : 



*/

package _20230528

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Edge(val u: Int, val v: Int, val weight: Int)

    val r = { nextToken();nval.toInt() }
    while (true) {
        val m = r()
        if (m == 0) return
        val n = r()
        val parents = IntArray(m) { it }
        val edges = Array(n) { Edge(r(), r(), r()) }.sortedArrayWith(compareBy { it.weight })

        fun findRoot(u: Int): Int = if (parents[u] != u) findRoot(parents[u]) else u

        fun unionFind(u: Int, v: Int): Boolean {
            val root1 = findRoot(u)
            val root2 = findRoot(v)

            if (root1 != root2) {
                parents[root2] = root1
            }
            return root1 != root2
        }

        var count = 0
        var sumElect = 0
        for ((u, v, dist) in edges) {
            if (unionFind(u, v)) {
                count++
                sumElect += dist
            }
            if (count == m - 1) break
        }
        println(edges.sumOf { it.weight } - sumElect)
    }
}

/*

*/