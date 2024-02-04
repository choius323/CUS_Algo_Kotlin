package _20240107

/*

1757번 - 달려달려
https://www.acmicpc.net/problem/1757

 */

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val distArray = intArrayOf(0) + IntArray(n) { r() }
    val dp = Array(n + 1) { LongArray(m + 1) } //시간,지침지수
    for (time in 1 until n) {
        dp[time][0] = maxOf(dp[time][0], dp[time - 1][0])
        for (tired in 0..m) {
            if (tired < m) {
                dp[time][tired + 1] = maxOf(dp[time][tired + 1], dp[time - 1][tired] + distArray[time])
            }
            if (time + tired <= n) {
                dp[time + tired][0] = maxOf(dp[time + tired][0], dp[time][tired])
            }
        }
    }
    print(dp.maxOf { it.first() })
}