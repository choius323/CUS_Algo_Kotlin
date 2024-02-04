package _20230129

/*

14938번 - 서강그라운드
https://www.acmicpc.net/problem/14938

모든 지점 간의 거리를 구하고, 탐색할 수 있는 거리인 지점에 있는 아이템의 합을 구한다.
n이 100 이하이기 때문에 플로이드-워셜로 거리를 구해도 n^3으로 시간이 오래 걸리지 않는다.

아이템을 구하는 과정에서 for 2개를 사용하는 것이 fold 같은 고차 함수를 사용하는 것보다 빠르고 메모리를 적게 사용한다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val i = { nextToken();nval.toInt() }
    val n = i()
    val m = i()
    val r = i()
    val items = IntArray(n) { i() }
    val dist = Array(n) { IntArray(n) { 2000 } }
    repeat(n) { dist[it][it] = 0 }

    repeat(r) {
        val a = i() - 1
        val b = i() - 1
        val l = i()
        if (dist[a][b] > l) {
            dist[a][b] = l
            dist[b][a] = l
        }
    }

    for (b in 0 until n) for (a in 0 until n) for (c in 0 until n) {
        dist[a][c] = minOf(dist[a][c], dist[a][b] + dist[b][c])
    }


    val sumItems: (Int, Int, Int) -> Int =
        { index, sum, d -> sum + if (d <= m) items[index] else 0 }
    print(dist.maxOf { it.foldIndexed(0, sumItems) })

//    print(dist.maxOf { it.foldIndexed(0) { index, sum, d -> sum + if (d <= m) items[index] else 0 } })

//    var answer = 0
//    for (a in 0 until n) {
//        var sum = 0
//        for (b in 0 until n) {
//            if (dist[a][b] <= m) {
//                sum += items[b]
//            }
//        }
//        answer = maxOf(sum, answer)
//    }
//    print(answer)
}

/*

*/