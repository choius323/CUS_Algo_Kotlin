package _20230622

/*

7576번 - 토마토
https://www.acmicpc.net/problem/7576
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val x: Int, val y: Int)

    val r = { nextToken();nval.toInt() }
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val m = r()
    val n = r()
    var count = 0
    val queue = ArrayDeque<Pos>()
    val storage = Array(n) { y ->
        BooleanArray(m) { x ->
            when (r()) {
                -1 -> true
                1 -> queue.add(Pos(x, y))
                else -> {
                    count++
                    false
                }
            }
        }
    }
    var day = 0
    while (queue.isNotEmpty()) {
        if (count == 0) return print(day)
        repeat(queue.size) {
            val (x, y) = queue.removeFirst()
            for (d in 0..3) {
                val nx = x + dx[d]
                val ny = y + dy[d]
                if (nx !in 0 until m || ny !in 0 until n || storage[ny][nx]) continue
                storage[ny][nx] = true
                queue.add(Pos(nx, ny))
                count--
            }
        }
        day++
    }
    print(-1)
}

/*

*/