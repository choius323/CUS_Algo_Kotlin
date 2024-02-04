/*

2461번 - 대표선수
https://www.acmicpc.net/problem/2461
분류 : 



*/

package _20230402

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Student(val num: Int, val stat: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val students = Array(n) { MutableList(m) { r() }.sorted() }
    val indices = IntArray(n)
    val pq = java.util.PriorityQueue<Student> { o1, o2 -> o1.stat.compareTo(o2.stat) }
    for (i in 0 until n) pq.add(Student(i, students[i][0]))
    var min = pq.peek().stat
    var max = pq.maxOf { it.stat }
    var answer = max - min
    while (true) {
        val (num, stat) = pq.remove()
        min = stat
        answer = minOf(answer, max - min)
        indices[num]++
        if (indices[num] >= m) break
        pq.add(Student(num, students[num][indices[num]]))
        max = maxOf(max, students[num][indices[num]])
    }
    print(answer)
}

/*

*/