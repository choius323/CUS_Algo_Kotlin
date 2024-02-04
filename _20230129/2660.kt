package _20230129

/*

2660번 - 회장뽑기
https://www.acmicpc.net/problem/2660

모든 회원 간의 관계를 입력받고, BFS로 탐색하며 가장 멀리 있는 회원의 거리를 구했다.
그리고 그 값이 가장 낮은 회원의 점수와 목록을 출력한다.

모든 회원간의 거리를 알아야 하기 때문에 플로이드-워셜을 쓰는 것이 더 빠르다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val graph = Array(n + 1) { BooleanArray(n + 1) }
    while (true) {
        val a = r()
        if (a < 0) break
        val b = r()
        graph[a][b] = true
        graph[b][a] = true
    }

    val bosses = ArrayList<Int>()
    var bossScore = 50
    for (start in 1..n) {
        val visited = BooleanArray(n + 1)
        visited[start] = true
        val queue = ArrayDeque<Int>()
        queue.add(start)
        var maxDist = -1
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val member = queue.removeFirst()
                for (next in 1..n) {
                    if (graph[member][next] && visited[next].not()) {
                        queue.add(next)
                        visited[next] = true
                    }
                }
            }
            maxDist++
        }
        if (bossScore > maxDist) {
            bosses.clear()
            bosses.add(start)
            bossScore = maxDist
        } else if (bossScore == maxDist) {
            bosses.add(start)
        }
    }
    print("$bossScore ${bosses.size}\n${bosses.joinToString(" ")}")
}

/*

*/