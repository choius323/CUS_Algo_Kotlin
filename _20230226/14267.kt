/*

14267번 - 회사 문화 1
https://www.acmicpc.net/problem/14267

각 직원의 부하를 저장하고, 칭찬 수치를 저장하는 배열을 만든다.
그 후에 사장부터 각 직원의 모든 부하를 탐색하며 칭찬 수치를 더한 뒤 출력한다.

*/

package _20230226

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val graph = Array(n) { mutableListOf<Int>() }
    val dp = IntArray(n)
    r()
    repeat(n - 1) { graph[r() - 1] += it + 1 }
    repeat(m) { dp[r() - 1] += r() }

    fun dfs(now: Int) {
        for (next in graph[now]) {
            dp[next] += dp[now]
            dfs(next)
        }
    }
    dfs(0)

    print(dp.joinToString(" "))
}

/*

*/