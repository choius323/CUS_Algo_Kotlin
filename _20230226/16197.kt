/*

16197번 - 두 동전
https://www.acmicpc.net/problem/16197

두 동전 중 한 개만 떨어져야 하고, 벽이 있는 방향으로 이동했을 때 다른 동전이 이동할 수 있으면 그 경우도 계산해야 한다.
BFS로 가장 먼저 조건을 만족하는 경우의 수를 찾았다.

*/

package _20230226

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

private data class Coin(val x: Int, val y: Int) {
    operator fun plus(d: Int) = Coin(x + dx[d], y + dy[d])
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) { readln() }
    val queue = ArrayDeque<List<Coin>>()

    val temp = mutableListOf<Coin>()
    for (y in 0 until n) for (x in 0 until m) {
        if (board[y][x] == 'o') {
            temp.add(Coin(x, y))
            board[y] = board[y].replaceFirst('o', '.')
        }
    }
    queue.add(temp.toList())

    fun Coin.isInBoard(): Boolean = x in 0 until m && y in 0 until n

    var count = 1
    while (queue.isNotEmpty()) {
        repeat(queue.size) { _ ->
            val now = queue.removeFirst()
            for (d in 0..3) {
                val next = now.map { it + d }.toMutableList()
                if (next[0].isInBoard() xor next[1].isInBoard()) {
                    return print(count)
                } else {
                    var isMove = false
                    for ((i, coin) in next.withIndex()) {
                        if (coin.isInBoard() && board[coin.y][coin.x] == '.') {
                            isMove = true
                        } else {
                            next[i] = now[i]
                        }
                    }
                    if (isMove) {
                        queue.add(next.toList())
                    }
                }
            }
        }
        count++
        if (count > 10) return print(-1)
    }
    print(-1)
}

/*

*/