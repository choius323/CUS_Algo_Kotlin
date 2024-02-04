package _20230205/*

1405번 - 미친 로봇
https://www.acmicpc.net/problem/1405

이동하는 확률이 25,25,25,25 일 때 왼쪽을 제외한 곳으로 이동할 확률을 구하면, 1-0.25이다.
같은 확률일 때 가운데로 돌아올 확률을 구하면, 1-0.25*0.25-0.25*0.25-0.25*0.25-0.25*0.25이다.
이렇게 같은 곳으로 돌아왔을 때의 확률을 1에서 계속 빼주면 답이 된다.

DFS로 모든 이동을 확인하며 n번 이동하면 이전 상태로 돌아갔다.
상하좌우로 이동할 때는 이동하려는 곳이 이미 이동했던 곳이면 확률을 답에서 빼고, 이동하지 않았던 곳이면 DFS로 탐색을 진행했다.
방문했던 곳은 2차원 배열로 확인했고 1<=n<=14 이므로 항상 밖으로 나가지 않게 크기를 설정했다.

*/

fun main() = System.`in`.bufferedReader().run {
    val input = readLine().split(" ").map { it.toInt() }
    val n = input[0]
    val (ep, wp, sp, np) = input.drop(1).map { it * 0.01 }
    val percentArray = doubleArrayOf(wp, ep, sp, np)
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var answer = 1.0
    val visited = Array(30) { BooleanArray(30) }
    visited[15][15] = true

    fun dfs(x: Int, y: Int, count: Int, percent: Double) {
        if (count == n) return

        for (d in 0..3) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            val nextPercent = percent * percentArray[d]
            if (visited[ny][nx]) {
                answer -= nextPercent
            } else {
                visited[ny][nx] = true
                dfs(nx, ny, count + 1, nextPercent)
                visited[ny][nx] = false
            }
        }
    }

    dfs(15, 15, 0, 1.0)
    print(answer)
}

/*

*/