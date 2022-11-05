package week6

/*

17070번 - 파이프 옮기기 1
https://www.acmicpc.net/problem/17070



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val house = List(n) { MutableList(n) { r() } }
    val queue = ArrayDeque<Pipe>()
    //dp로 위치와 방향에 따라 경우의 수를 저장

    fun Pipe.canMove() = head.x < n && head.y < n && house[head] == 0

    queue.add(Pipe(Pos(1, 0), 0))
    var count = 0
    while (queue.isNotEmpty()) {
        val pipe = queue.removeFirst()
        if (pipe.head == Pos(n - 1, n - 1)) {
            count++
        } else {
            var next = Pipe(pipe.head + Pos(1, 0), 0)
            if ((pipe.dir == 0 || pipe.dir == 1) && next.canMove()) {
                queue.add(next)
            }

            next = Pipe(pipe.head + Pos(1, 1), 1)
            if (next.canMove() && house[next.head + Pos(-1, 0)] == 0 && house[next.head + Pos(0, -1)] == 0) {
                queue.add(next)
            }

            next = Pipe(pipe.head + Pos(0, 1), 2)
            if ((pipe.dir == 1 || pipe.dir == 2) && next.canMove()) {
                queue.add(next)
            }
        }
    }
    print(count)
}

private operator fun <T> List<List<T>>.get(pos: Pos) = this[pos.y][pos.x]

private data class Pipe(val head: Pos, val dir: Int)

private data class Pos(val x: Int, val y: Int) {
    operator fun plus(pos: Pos) = Pos(x + pos.x, y + pos.y)
}

/*

*/