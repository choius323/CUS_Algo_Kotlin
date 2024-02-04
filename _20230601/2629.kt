/*

2629번 - 양팔저울
https://www.acmicpc.net/problem/2629
분류 : 



*/

package _20230601

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val weights = IntArray(r()) { r() }
    val dp = BooleanArray(40001)
    dp[0] = true
    var maxSumWeights = 0
    for (weight in weights) {
        var tempMax = maxSumWeights
        for (i in maxSumWeights downTo 0) {
            val sumWeight = weight + i
            if (dp[i].not() || sumWeight > 40000) continue
            dp[sumWeight] = true
            tempMax = maxOf(tempMax, sumWeight)
        }
        maxSumWeights = tempMax
    }
    repeat(r()) {
        val marble = r()
        if (dp[marble]) return@repeat print("Y ")
        for (weight in 0..maxSumWeights) {
            if (weight + marble <= 40000 && dp[weight] && dp[weight + marble]) {
                return@repeat print("Y ")
            }
        }
        print("N ")
    }
}

/*

*/