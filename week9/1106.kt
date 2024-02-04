/*

1106번 - 호텔
https://www.acmicpc.net/problem/1106



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Ad(val cost: Int, val num: Int)

    val r = { nextToken();nval.toInt() }

    val c = r()
    val n = r()
    val ads = List(n) { Ad(r(), r()) }.sortedByDescending { it.cost }
    var invest = 100000
    var dp = IntArray(invest + 1)
    for ((cost, num) in ads) {
        val next = IntArray(invest + 1)
        for (nCost in cost..invest) {
            next[nCost] = maxOf(dp[nCost], dp[nCost - cost] + num, next[nCost - cost] + num)
            if (next[nCost] >= c) {
                invest = nCost
                break
            }
        }
        dp = next
    }
    print(invest)
}

/*

*/