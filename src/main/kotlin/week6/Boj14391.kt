package week6

/*

14391번 - 종이 조각
https://www.acmicpc.net/problem/14391



*/

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val nums = List(n) { readln().toCharArray().map { it.digitToInt() } }
    val visited = List(n) { MutableList(m) { false } }
    var answer = 0
    var num = 0
    var sum = 0
    val dx = List(m + 1) { it } + List(n) { 0 }
    val dy = List(m + 1) { 0 } + List(n) { it + 1 }
    for (x in 0 until m) for (y in 0 until n) {
        for (d in dx.indices) {
            val nx = dx[d]
            val ny = dy[d]
            if (visited[ny][nx].not()) {
                visited[ny][nx] = true

            }
        }
    }
}

/*

*/