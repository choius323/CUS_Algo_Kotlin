/*

2411번 - 아이템 먹기
https://www.acmicpc.net/problem/2411

아이템을 x와 y좌표 기준으로 오름차순 정렬을 하고 순서대로 도착하는 경우의 수를 모두 곱하면 된다.
최종 목적지(n, m)도 아이템처럼 취급해서 리스트에 같이 넣어주는 게 따로 처리하지 않아도 돼서 편하다.
장애물은 연결 리스트처럼 List와 ArrayList로 구현했다.
모든 좌표는 y x 좌표 순으로 값이 입력되는 것을 주의해야 한다.
다음 아이템까지 이동은 BFS를 사용해서 경로의 개수를 찾았다.
최종 답은 (시작->item1 경로)*(item1->item2 경로)*...*(item(A-1)->itemA 경로)이다.

*/

package _20230305

/*fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val y: Int, val x: Int) // 좌표용 클래스

    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i() // 세로 길이
    val m = i() // 가로 길이
    val a = i() // 아이템 수
    val b = i() // 장애물 수
    val items = List(a) { Pos(i(), i()) }.sortedBy { it.y }.sortedBy { it.x } + listOf(Pos(n, m))
    // 아이템 리스트를 x, y좌표로 정렬한 뒤 순서대로 방문한다. 최종 목적지도 아이템으로 취급한다.
    val blocks = List(101) { ArrayList<Int>() } // 장애물
    repeat(b) { blocks[i()].add(i()) }

    val queue = ArrayDeque<Pos>()
    queue.add(Pos(1, 1))
    var answer = 1
    for (item in items) { // 모든 아이템을 순서대로 방문
        var count = 0
        while (queue.isNotEmpty()) { // BFS로 다음 아이템까지 탐색. 다음 아이템은 큐에 넣지 않음.
            val q = queue.removeFirst()
            var nPos = Pos(q.y, q.x + 1)
            if (nPos == item) // 목적지이면 count++. 큐에 넣지 않음
                count += 1
            else if (nPos.x <= item.x && nPos.y <= item.y && nPos.x !in blocks[nPos.y]) // 목적지보다 왼쪽아래 있고 장애물이 아니면
                queue.add(nPos)

            nPos = Pos(q.y + 1, q.x)
            if (nPos == item)
                count += 1
            else if (nPos.x <= item.x && nPos.y <= item.y && nPos.x !in blocks[nPos.y])
                queue.add(nPos)
        }
        answer *= count // 이전까지 나온 값에 새로운 아이템으로 가는 경우의 수를 곱한다.
        if (count == 0) break
        queue.add(item) // 현재 아이템을 시작으로 다음 아이템까지 이동
    }
    print(answer)
}*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val x: Int, val y: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val a = r()
    val b = r()
    val board = Array(n + 1) { IntArray(m + 1) }
    val goals = (Array(a) { Pos(y = r(), x = r()) } + Pos(m, n))
        .sortedArrayWith(compareBy({ it.x }, { it.y }))
    repeat(b) { board[r()][r()] = -1 }
    val queue = ArrayDeque<Pos>()
    queue.add(Pos(1, 1))
    board[1][1] = 1

    val dx = intArrayOf(1, 0)
    val dy = intArrayOf(0, 1)
    for (goal in goals) {
        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            for (d in dx.indices) {
                val nx = now.x + dx[d]
                val ny = now.y + dy[d]
                if (nx in 1..goal.x && ny in 1..goal.y && board[ny][nx] != -1) {
                    if (board[ny][nx] == 0)
                        queue.add(Pos(nx, ny))
                    board[ny][nx] += board[now.y][now.x]
                }
            }
        }
        queue.add(goal)
    }
    print(board[n][m])
}

/*
5 8 4 4
1 2
2 5
3 5
4 7
1 5
2 2
2 7
3 6

4


5 8 4 4
1 3
2 5
3 5
4 7
1 2
2 2
2 1
3 6

0


100 100 1 0
100 1
*/