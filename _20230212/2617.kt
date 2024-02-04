/*

2617번 - 구슬 찾기
https://www.acmicpc.net/problem/2617

플로이드-워셜을 사용해 풀었다.
자신보다 무겁거나 가벼운 구슬의 개수가 n/2 보다 많다면 가운데에 올 수 없다는 점을 생각해야 한다.
구슬간의 무게 관계를 단방향 그래프로 생각하면, 한쪽 방향으로 탐색하며 닿는 점들이 모두 자신보다 무겁거나 가벼운 구슬이 된다.
따라서 그래프에서 갈 수 있는 점을 모두 확인하고, 그 개수가 n/2 보다 큰 점의 개수를 정방향, 양방향 그래프에서 확인한다.

BFS나 DFS로 각 점에서 완전탐색을 하는 방법을 사용해도 된다.

*/

package _20230212

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val heavy = Array(n) { BooleanArray(n) }
    val light = Array(n) { BooleanArray(n) }
    repeat(r()) {
        val a = r() - 1
        val b = r() - 1
        heavy[a][b] = true
        light[b][a] = true
    }

    for (k in 0 until n) for (i in 0 until n) for (j in 0 until n) {
        if (heavy[i][k] && heavy[k][j])
            heavy[i][j] = true
        if (light[i][k] && light[k][j])
            light[i][j] = true
    }

    var answer = 0
    for (i in 0 until n) {
        if (heavy[i].count { it } > n / 2 || light[i].count { it } > n / 2) {
            answer++
        }
    }
    print(answer)
}

/*

*/