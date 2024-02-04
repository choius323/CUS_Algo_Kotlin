/*

2251번 - 물통
https://www.acmicpc.net/problem/2251

BFS로 물을 옮길 수 있는 경우의 수를 모두 탐색하며, a가 0일 때 나올 수 있는 c의 값을 저장한다.
중복 탐색을 방지하기 위해 3차원 배열을 만들어 각 인덱스를 물의 용량으로 사용해서 확인했던 경우를 저장했다.
물을 옮길 때 한 물통의 물을 다 쓰거나, 옮길 물통의 양이 꽉 찰때 까지만 옮길 수 있다는 점을 유의해야 한다.

*/

package _20230219

fun main() {
    data class Bucket(val a: Int, val b: Int, val c: Int)

    fun List<Int>.toBucket() = Bucket(get(0), get(1), get(2))

    val full = readln().split(" ").map { it.toInt() }.toBucket()
    val queue = ArrayDeque<Bucket>()
    val visited = Array(full.a + 1) { Array(full.b + 1) { BooleanArray(full.c + 1) } }
    val answer = mutableSetOf(full.c)

    visited[0][0][full.c] = true
    queue.add(Bucket(0, 0, full.c))

    fun move(a: Int, b: Int, c: Int) {
        if (a in 0..full.a && b in 0..full.b && c in 0..full.c && visited[a][b][c].not()) {
            queue.add(Bucket(a, b, c))
            visited[a][b][c] = true
            if (a == 0) answer += c
        }
    }

    while (queue.isNotEmpty()) {
        val (a, b, c) = queue.removeFirst()
        var move = 0

        move = minOf(a, full.b - b)
        move(a - move, b + move, c)

        move = minOf(a, full.c - c)
        move(a - move, b, c + move)

        move = minOf(b, full.a - a)
        move(a + move, b - move, c)

        move = minOf(b, full.c - c)
        move(a, b - move, c + move)

        move = minOf(c, full.a - a)
        move(a + move, b, c - move)

        move = minOf(c, full.b - b)
        move(a, b + move, c - move)
    }

    println(answer.sorted().joinToString(" "))
}

/*

*/