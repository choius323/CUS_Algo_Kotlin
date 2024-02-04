/*

1941번 - 소문난 칠공주
https://www.acmicpc.net/problem/1941



*/

package _20230319

fun main() {
    data class Pos(val x: Int, val y: Int)

    val n = 5
    val table = Array(n) { readln() }
    val visited = Array(n) { IntArray(n) }

    fun isValid(array: Array<Pos>): Boolean {
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
        var countS = 0
        var countVisit = 0
        val queue = ArrayDeque<Pos>()

        array.first().let {
            queue.add(it)
            visited[it.y][it.x] = 2
        }
        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            countVisit++
            if (table[now.y][now.x] == 'S') countS++
            for (d in 0..3) {
                val x = now.x + dx[d]
                val y = now.y + dy[d]
                if (x in 0 until n && y in 0 until n && visited[y][x] == 1) {
                    visited[y][x] = 2
                    queue.add(Pos(x, y))
                }
            }
        }
        array.forEach {
            visited[it.y][it.x] = 1
        }

        return countVisit == 7 && countS >= 4
    }

    fun makeArray(array: Array<Pos>, startIdx: Int): Int {
        if (array.size == 7) {
            return if (isValid(array)) 1 else 0
        }
        var count = 0
        for (idx in startIdx until 25) {
            val x = idx % 5
            val y = idx / 5
            if (visited[y][x] == 0) {
                visited[y][x] = 1
                count += makeArray(array + Pos(x, y), idx + 1)
                visited[y][x] = 0
            }
        }
        return count
    }

    print(makeArray(arrayOf(), 0))
}

/*

*/