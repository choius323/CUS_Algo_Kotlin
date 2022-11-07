package week6

/*

5557번 - 1학년
https://www.acmicpc.net/problem/5557

dp[i-1]의 각 숫자에 경우의 수를 저장하고, 그 숫자에 input[i]를 더하거나 뺀 결과를 dp[i]의 각 숫자에 더한다.

참고 링크 : https://lmcoa15.tistory.com/96

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val nums = List(n) { r() }
    val dp = List(n) { MutableList(21) { 0L } }
    dp[0][nums[0]]++

    for (i in 1..n - 2) {
        for (j in 0..20) {
            if (j + nums[i] <= 20) dp[i][j + nums[i]] += dp[i - 1][j]
            if (j - nums[i] >= 0) dp[i][j - nums[i]] += dp[i - 1][j]
        }
    }
    print(dp[n - 2][nums.last()])
}

/*

*/