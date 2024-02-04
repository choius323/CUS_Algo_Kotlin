/*

8983번 - 사냥꾼
https://www.acmicpc.net/problem/8983
분류 : 



*/

package _20230528

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val m = r()
    val n = r()
    val l = r()
    val shooters = IntArray(m) { r() }.sortedArray()
    var count = 0
    repeat(n) {
        val x = r()
        val y = r()
        var left = -1
        var right = shooters.size
        while (left + 1 < right) {
            val mid = (left + right) / 2
            val dist = kotlin.math.abs(shooters[mid] - x) + y
            if (dist <= l) {
                count++
                break
            } else if (shooters[mid] < x) {
                left = mid
            } else {
                right = mid
            }
        }
    }
    print(count)
}

/*

*/