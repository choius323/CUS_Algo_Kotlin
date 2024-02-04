/*

2470번 - 두 용액
https://www.acmicpc.net/problem/2470



*/

import kotlin.math.abs

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val list = List(n) { r() }.sorted()
    var left = 0
    var right = n - 1
    var minLiquids = 0 to 0
    var min = Int.MAX_VALUE
    while (left < right) {
        val now = list[right] + list[left]
        if (abs(now) < min) {
            min = abs(now)
            minLiquids = list[left] to list[right]
        }
        if (now < 0) left++ else right--
    }
    print("${minLiquids.first} ${minLiquids.second}")
}

/*

*/