/*

1504번 - 특정한 최단 경로
https://www.acmicpc.net/problem/1504

다익스트라를 사용해서 1-v1-v2-n과 1-v2-v1-n의 거리 중 작은 값을 출력하면 된다.
n이 작기 때문에 플로이드 와샬을 사용해도 시간초과가 되지 않는다.

*/

package _20240107

import java.util.*

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i()
    val graph = Array(n + 1) { LongArray(n + 1) { 10000000 } }
    repeat(i()) {
        val u = i()
        val v = i()
        val c = i().toLong()
        graph[u][v] = c
        graph[v][u] = c
    }
    fun dijkstra(start: Int): LongArray {
        val distance = LongArray(n + 1) { 10000000 }
        val queue = PriorityQueue<Int>() { o1, o2 -> (distance[o1] - distance[o2]).toInt() }
        distance[start] = 0
        queue.add(start)
        while (queue.isNotEmpty()) {
            val v = queue.remove()
            for (i in 1..n) {
                if (distance[i] > distance[v] + graph[v][i]) {
                    distance[i] = distance[v] + graph[v][i]
                    queue.add(i)
                }
            }
        }
        return distance
    }

    val v1 = i()
    val v2 = i()
    val start1 = dijkstra(1)
    val startV1 = dijkstra(v1)
    val startV2 = dijkstra(v2)
    val answer = minOf(start1[v1] + startV1[v2] + startV2[n], start1[v2] + startV2[v1] + startV1[n])
    print(if (answer < 10000000) answer else -1)
}

// 플로이드 와샬
fun main2() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i()
    val graph = Array(n + 1) { LongArray(n + 1) { 100000000 } }
    repeat(i()) {
        val u = i()
        val v = i()
        val c = i().toLong()
        if (graph[u][v] > c) {
            graph[u][v] = c
            graph[v][u] = c
        }
    }
    repeat(n + 1) {
        graph[it][it] = 0
    }
    val v1 = i()
    val v2 = i()

    for (b in 1..n) for (a in 1..n) for (c in 1..n) {
        graph[a][c] = minOf(graph[a][c], graph[a][b] + graph[b][c])
    }
    val answer = minOf(graph[1][v1] + graph[v1][v2] + graph[v2][n], graph[1][v2] + graph[v2][v1] + graph[v1][n])
    print(if (answer < 100000000) answer else -1)
}

/*

*/