/*

2206번 - 벽 부수고 이동하기
https://www.acmicpc.net/problem/2206

길을 진행하면서 벽을 만났을 때 고려할 것은 이번에 벽을 부술지, 다음에 벽을 부술지 선택해야 한다.
벽을 부수고 빠르게 지나갔던 길을 벽을 안 부수고 진행해서 늦었을 때 그 경우를 버리게 되면, 다음에 필요한 벽을 부수지 못한다.
따라서 벽을 부수지 않은 경우는 많이 늦더라도 버리고 진행하면 안 된다.

1<=m<=1000이기 때문에 Int로 받은 다음 String으로 변환하면 결과가 이상하게 나올 수 있다. 조심하자...

*/

package _20230521

/*fun main() = System.`in`.bufferedReader().run {
    data class Data(val x: Int, val y: Int, val hit: Int = 1)

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = Array(n) { readLine() }
    val visit = Array(n) { IntArray(m) }
    val queue = ArrayDeque<Data>() //x, y, 1:안부숨 or 2:부숨
    var count = 0
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    queue.add(Data(0, 0))
    visit[0][0] = 3
    while (queue.isNotEmpty()) {
        count += 1
        repeat(queue.size) { _ ->
            val (x, y, hit) = queue.removeFirst()
            if (x == m - 1 && y == n - 1) {
                print(count)
                return@run
            } else {
                repeat(4) {
                    val nx = x + dx[it]
                    val ny = y + dy[it]
                    if (nx in 0 until m && ny in 0 until n) {
                        if (map[ny][nx] == '0' && visit[ny][nx] and hit == 0) {
                            queue.add(Data(nx, ny, hit))
                            visit[ny][nx] += hit
                        } else if (map[ny][nx] == '1' && hit == 1) {
                            queue.add(Data(nx, ny, 2))
                            visit[ny][nx] += 2
                        }
                    }
                }
            }
        }
    }
    println(-1)
}*/

fun main() = System.`in`.bufferedReader().run {
    data class Pos(val x: Int, val y: Int, val isBreak: Int)

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = Array(n) { readLine() }
    val visited = Array(n) { Array(m) { BooleanArray(2) } }
    val queue = ArrayDeque<Pos>()
    queue.add(Pos(0, 0, 0))
    visited[0][0][0] = true
    var count = 1
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val pos = queue.removeFirst()
            if (pos.x == m - 1 && pos.y == n - 1) return print(count)
            for (d in 0..3) {
                val nx = pos.x + dx[d]
                val ny = pos.y + dy[d]
                if (nx in 0 until m && ny in 0 until n) {
                    if (map[ny][nx] == '0' && visited[ny][nx][pos.isBreak].not()) {
                        visited[ny][nx][pos.isBreak] = true
                        queue.add(Pos(nx, ny, pos.isBreak))
                    }
                    if (map[ny][nx] == '1' && visited[ny][nx][1].not() && pos.isBreak == 0) {
                        visited[ny][nx][1] = true
                        queue.add(Pos(nx, ny, 1))
                    }
                }
            }
        }
        count++
    }
    print(-1)
}

/*
5 6
010100
010110
000110
111110
111110

14


6 5
01000
00110
10110
00111
01111
01000

12


5 5
00000
11101
00001
01111
00010


6 4
0000
1110
0110
0000
0111
0000

*/