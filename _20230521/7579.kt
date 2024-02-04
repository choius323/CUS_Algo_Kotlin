/*

7579번 - 앱
https://www.acmicpc.net/problem/7579
분류 : 



*/

package _20230521

fun main2() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val useArray = IntArray(n) { r() }
    val cancelArray = IntArray(n) { r() }
    val dp = IntArray(10101)
    var maxC = 0
    for ((use, cancel) in useArray.zip(cancelArray).sortedWith(compareBy({ it.second }, { it.first }))) {
        for (c in maxC downTo 0) {
            dp[c + cancel] = maxOf(dp[c + cancel], dp[c] + use)
        }
        maxC += cancel
    }
    for (i in dp.indices) {
        if (dp[i] >= m) return print(i)
    }
}

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val arrays = Array(2) { IntArray(n) { r() } }
    val dp = IntArray(10101)
    var maxC = 0
    for (i in 0 until n) {
        val use = arrays[0][i]
        val cancel = arrays[1][i]
        for (c in maxC downTo 0) {
            dp[c + cancel] = maxOf(dp[c + cancel], dp[c] + use)
        }
        maxC += cancel
    }
    for (i in dp.indices) {
        if (dp[i] >= m) return print(i)
    }
}

/*

5 60
30 10 20 35 40
3 0 3 5 4

6



*/