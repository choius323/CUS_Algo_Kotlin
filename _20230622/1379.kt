package _20230622

/*

1379번 - 강의실 2
https://www.acmicpc.net/problem/1379
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Lecture(val num: Int, val start: Int, val end: Int, var roomNum: Int)

    val r = { nextToken();nval.toInt() }
    val lectures = Array(r()) { Lecture(r(), r(), r(), 0) }.sortedBy(Lecture::start)
    val queue = java.util.PriorityQueue(compareBy(Lecture::end))
    for (lecture in lectures) {
        lecture.roomNum = if (queue.isEmpty() || lecture.start < queue.peek().end) {
            queue.size + 1
        } else {
            queue.poll().roomNum
        }
        queue.add(lecture)
    }
    print("${queue.size}\n${lectures.sortedBy(Lecture::num).joinToString("\n") { "${it.roomNum}" }}")
}

/*
8
1 1 5
4 5 7
3 3 5
5 5 9
6 1 10
7 9 10
2 1 3
8 7 10

3
1 0 10000000
2 0 1
3 0 5
*/