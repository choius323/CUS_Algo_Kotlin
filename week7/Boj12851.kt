import java.math.BigDecimal

/*

12851번 - 숨바꼭질 2
https://www.acmicpc.net/problem/12851



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    var repeat = 0
    val n = r()
    val k = r()
    BigDecimal("123")
    val queue = ArrayDeque<Int>()
    val dp = MutableList(maxOf(n + 1, (k * 2))) { 0 }
    var count = 0
    queue.add(n)
    dp[n] = 1
    while (queue.isNotEmpty()) {
        val visited = mutableSetOf<Int>()
        repeat(queue.size) {
            val pos = queue.removeFirst()
            if (pos == k) {
                count++
                return@repeat
            }
            for (next in listOf(pos - 1, pos + 1, pos * 2)) {
                if (next in dp.indices && (dp[next] == 0 || next in visited)) {
                    visited += next
                    dp[next] += dp[pos]
                }
            }
        }
        if (count > 0) return print("$repeat\n${dp[k]}")
        repeat++
        queue.addAll(visited)
    }
}

/*

*/