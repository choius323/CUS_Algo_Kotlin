/*

12784번 - 인하니카 공화국
https://www.acmicpc.net/problem/12784
분류 : 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색, 트리, 깊이 우선 탐색, 트리에서의 다이나믹 프로그래밍

두 섬을 연결하는 다리를 최소한의 개수로 만들어 모든 섬 간의 왕래가 가능하도록 만들었다. -> 트리 구조로 이루어져 있다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Edge(val u: Int, val v: Int, val d: Int)

    val r = { nextToken();nval.toInt() }
    repeat(r()) testCase@{ _ ->
        val n = r()
        val m = r()
        val islands = Array(n + 1) { mutableListOf<Edge>() }
        repeat(m) {
            val u = r()
            val v = r()
            val d = r()
            islands[u].add(Edge(u, v, d))
            islands[v].add(Edge(v, u, d))
        }
        if (n == 1) {
            println(0)
            return@testCase
        }
        val visited = BooleanArray(n + 1)

        fun dfs(idx: Int): Int {
            visited[idx] = true
            var sum = 0
            for (nextEdge in islands[idx]) {
                if (visited[nextEdge.v]) continue
                sum += minOf(dfs(nextEdge.v), nextEdge.d)
            }
            return if (sum == 0) 21 else sum
        }
        println(dfs(1))
    }
}

/*

*/