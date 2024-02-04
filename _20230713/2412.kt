package _20230713

/*

2412번 - 암벽 등반
https://www.acmicpc.net/problem/2412
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val x: Int, val y: Int)

    fun binarySearch(left: Int, right: Int, condition: (Int) -> Boolean): Pair<Int, Int> {
        var l = left
        var r = right
        while (l + 1 < r) {
            val mid = (l + r) / 2
            if (condition(mid)) {
                r = mid
            } else {
                l = mid
            }
        }
        return l to r
    }

    val r = { nextToken();nval.toInt() }
    val n = r()
    val t = r()
    val grooves = Array(n) { Pos(r(), r()) }.sortedArrayWith(compareBy(Pos::x, Pos::y))
    val visited = BooleanArray(n)
    val queue = ArrayDeque<Pos>()
    queue.add(Pos(0, 0))
    var count = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val now = queue.removeFirst()
            if (now.y == t) return print(count)
            var left = -1
            var right = n
            left = binarySearch(left, right) { mid ->
                grooves[mid].x >= now.x - 2
            }.second
            right = binarySearch(left, right) { mid ->
                grooves[mid].x > now.x + 2
            }.first
            for (i in left..right) {
                if (visited[i].not() && grooves[i].x - now.x in -2..2 && grooves[i].y - now.y in -2..2) {
                    visited[i] = true
                    queue.add(grooves[i])
                }
            }
        }
        count++
    }
    print(-1)
}


/*
6 3
1 1
1 2
1 3
2 1
5 1
4 2
*/