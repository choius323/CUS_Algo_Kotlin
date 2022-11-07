/*

강의실 배정
https://www.acmicpc.net/problem/11000

5
1 3
1 5
2 4
3 4
3 5

 */

package week4

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Lecture(val s: Int, val t: Int)

    val i = { nextToken(); nval.toInt() }

    val lecture = List(i()) { Lecture(i(), i()) }
    val room = java.util.PriorityQueue<Int>()

    lecture.sortedWith(compareBy({ it.s }, { it.t })).forEach {
        if ((room.peek() ?: -1) <= it.s) room.poll()
        room.add(it.t)
    }
    print(room.size)
}