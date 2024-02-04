package _20240128

/*

13975번 - 파일 합치기 3
https://www.acmicpc.net/problem/13975
분류 : 자료 구조, 그리디 알고리즘, 우선순위 큐

우선순위 큐에 모든 파일의 크기를 넣고 가장 작은 파일 2개를 합쳐나간다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val sb = StringBuilder()
    repeat(r()) { _ ->
        val pq = java.util.PriorityQueue(List(r()) { r().toLong() })
        var total = 0L
        while (pq.size > 1) {
            val size = pq.remove() + pq.remove()
            pq.add(size)
            total += size
        }
        sb.appendLine(total)
    }
    print(sb)
}

/*

*/