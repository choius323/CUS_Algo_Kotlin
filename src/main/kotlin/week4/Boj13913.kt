/*

Boj13913번 - 숨바꼭질 4
https://www.acmicpc.net/problem/Boj13913

*/

package week4

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val k = r()
    val queue = ArrayDeque<Int>()
    val visited = MutableList<Int?>(maxOf(k, n, 2) * 2) { null }

    fun visit(prev: Int, next: Int) {
        queue.add(next)
        visited[next] = prev
    }

    fun findPath() {
        val sb = StringBuilder()
        var count = 0
        var now = n
        while (now != k) {
            sb.append("$now ")
            count++
            now = visited[now]!!
        }
        println("$count\n$sb$k")
    }

    visit(k, k)
    var count = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val now = queue.removeFirst()
            if (now == n) return findPath()
            if (now > n / 2 && now % 2 == 0 && visited[now / 2] == null) visit(now, now / 2)
            if (now > 0 && visited[now - 1] == null) visit(now, now - 1)
            if (now < maxOf(n, k) * 2 && visited[now + 1] == null) visit(now, now + 1)
        }
        count++
    }
}


/*

*/

