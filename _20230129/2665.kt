package _20230129

/*

2665번 - 미로만들기
https://www.acmicpc.net/problem/2665

BFS로 바둑판을 탐색하며 목적지에 가장 먼저, 최소한의 검은 방을 지나 도착하는 방법을 찾는다.
평소와 같은 방법으로 큐를 사용하게 되면 먼저 도착하는 방법과 관계 없이 검은 방을 많이 지나가게 되는 방법 들도 큐에 저장되게 된다.
따라서 우선순위 큐를 사용해 검은 방을 가장 적게 지나가는 방법들 먼저 확인한다.
또 방문했던 지점을 저장할 때는 그 지점을 지날 때 검은 방을 가장 적게 지날 수 있는 횟수를 저장한다.

*/

fun main() = System.`in`.bufferedReader().run {
    data class Move(val x: Int, val y: Int, val count: Int)

    val n = readLine().toInt()
    val board = Array(n) { readLine() } //검은방 0, 흰방 1
    val visited = Array(n) { IntArray(n) { 2500 } }
    val queue = java.util.PriorityQueue<Move> { o1, o2 -> o1.count.compareTo(o2.count) }
    visited[0][0] = 0
    queue.add(Move(0, 0, 0))
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        for (d in 0..3) {
            val nx = now.x + dx[d]
            val ny = now.y + dy[d]
            if (nx in 0 until n && ny in 0 until n) {
                val nCount = now.count + if (board[ny][nx] == '0') 1 else 0
                if (nCount < visited[ny][nx]) {
                    if (nx == n - 1 && ny == n - 1)
                        return print(nCount)
                    queue.add(Move(nx, ny, nCount))
                    visited[ny][nx] = nCount
                }
            }
        }
    }
}

/*

*/