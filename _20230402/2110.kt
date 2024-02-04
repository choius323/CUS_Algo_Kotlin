/*

2110번 - 공유기 설치
https://www.acmicpc.net/problem/2110
분류 : 



*/

package _20230402

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val c = r()
    val routers = IntArray(n) { r() }.sortedArray()
    var left = 1
    var right = routers.last() - routers.first()
    var answer = 0
    while (left <= right) {
        val mid = (right + left) / 2
        var count = 1
        var prev = routers[0]
        for (i in 1 until routers.size) {
            if (routers[i] - prev >= mid) {
                count++
                prev = routers[i]
            }
        }
        if (count >= c) {
            answer = maxOf(answer, mid)
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    print(answer)
}

/*

*/