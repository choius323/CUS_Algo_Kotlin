package _20240107

/*

14503번 - 로봇 청소기
https://www.acmicpc.net/problem/14503
분류 : 구현, 시뮬레이션

뒤로 갔을 때 맵 밖으로 나갔는지 확인하는 게 아니라, 벽으로 올라갔는 지 확인해야 한다.
사실 사방이 벽으로 둘러 싸여있기 때문에 인덱스 검사를 할 필요가 없다.

처음엔 BFS인줄 알고 접근했는데 그냥 구현 문제였다.

*/


private const val WALL = 1
private const val DUST = 0
private const val EMPTY = -1

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(-1, 0, 1, 0)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    var y = r()
    var x = r()
    var dir = r()
    val map = Array(n) { IntArray(m) { r() } }

    fun findAround(): Boolean {
        for (d in dx.indices) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            if (map[ny][nx] == DUST) return true
        }
        return false
    }

    fun move(multi: Int) {
        x += dx[dir] * multi
        y += dy[dir] * multi
    }

    fun findForward(): Boolean {
        val nx = x + dx[dir]
        val ny = y + dy[dir]
        return map[ny][nx] == DUST
    }

    var count = 0
    while (true) {
        if (map[y][x] == DUST) count++
        map[y][x] = EMPTY
        if (findAround()) {
            dir = if (dir == 0) 3 else (dir - 1)
            if (findForward()) {
                move(1)
            }
        } else {
            move(-1)
            if (map[y][x] == WALL) break
        }
    }
    print(count)
}

/*
6 7
1 1 1
1 1 1 1 1 1 1
1 0 1 0 0 1 1
1 0 0 1 0 0 1
1 1 0 1 0 0 1
1 0 0 0 0 1 1
1 1 1 1 1 1 1

13
*/