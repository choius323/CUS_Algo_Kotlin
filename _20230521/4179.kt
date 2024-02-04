/*

4179번 - 불!
https://www.acmicpc.net/problem/4179
분류 : 



*/

package _20230521

fun main() = System.`in`.bufferedReader().run {
    data class Pos(val x: Int, val y: Int, val isFire: Boolean)
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val queue = ArrayDeque<Pos>()
    val maze = Array(r) { readLine().toCharArray() }
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val visited = Array(r) { BooleanArray(c) }
    for (y in 0 until r) for (x in 0 until c) {
        when (maze[y][x]) {
            '#' -> visited[y][x] = true
            'F' -> {
                queue.addFirst(Pos(x, y, true))
                visited[y][x] = true
            }

            'J' -> {
                maze[y][x] = '.'
                queue.add(Pos(x, y, false))
                visited[y][x] = true
            }
        }
    }
    var count = 1
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (x, y, isFire) = queue.removeFirst()
            if (isFire.not() && (y == 0 || y == r - 1 || x == 0 || x == c - 1)) return print(count)
            for (d in 0..3) {
                val nx = x + dx[d]
                val ny = y + dy[d]
                if (nx in 0 until c && ny in 0 until r && visited[ny][nx].not()) {
                    queue.add(Pos(nx, ny, isFire))
                    visited[ny][nx] = true
                    if (isFire) maze[ny][nx] = 'F'
                }
            }
        }
        count++
    }
    print("IMPOSSIBLE")
}

/*
4 4
####
#JF#
#..#
#..#

3


4 4
####
#JF#
#.F#
#..#

IMPOSSIBLE


4 5
#####
#J..#
#...#
#..F#

*/