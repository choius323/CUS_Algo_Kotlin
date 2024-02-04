package _20231002

/*

20311번 - 화학 실험
https://www.acmicpc.net/problem/20311
분류 : 자료 구조, 그리디 알고리즘, 정렬, 해 구성하기, 우선순위 큐



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Color(val count: Int, val type: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val k = r()
    val pq = java.util.PriorityQueue(compareByDescending(Color::count))
    repeat(k) {
        pq.add(Color(r(), it + 1))
    }
    val sb = StringBuilder()
    var lastType = -1
    while (pq.isNotEmpty()) {
        var color: Color? = null
        if (pq.peek().type == lastType) {
            val keep = pq.remove()
            if (pq.isNotEmpty()) {
                color = pq.poll()
            }
            pq.add(keep)
        } else {
            color = pq.poll()
        }
        if (color == null) {
            return print(-1)
        } else {
            sb.append(color.type).append(' ')
            if (color.count > 1) {
                pq.add(Color(color.count - 1, color.type))
            }
            lastType = color.type
        }
    }
    print(sb)
}

/*

*/