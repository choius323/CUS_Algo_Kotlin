package _20231002

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Edge(val u: Int, val v: Int, val dist: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val parents = IntArray(n) { it }

    fun find(a: Int): Int = if (parents[a] == a) {
        a
    } else {
        parents[a] = find(parents[a])
        parents[a]
    }

    fun union(a: Int, b: Int): Boolean {
        val pa = find(a)
        val pb = find(b)

        if (pa == pb) {
            return false
        }
        parents[pa] = pb
        return true
    }

    val pq = java.util.PriorityQueue<Edge>(compareBy(Edge::dist))
    repeat(m) {
        val u = r() - 1
        val v = r() - 1
        val dist = r()
        pq.add(Edge(u, v, dist))
    }

    var count = 0
    var sum = 0
    while (pq.isNotEmpty() && count < n - 2) {
        val edge = pq.poll()
        if (union(edge.u, edge.v)) {
            count++
            sum += edge.dist
        }
    }
    print(sum)
}