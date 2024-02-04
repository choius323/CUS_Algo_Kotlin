/*

17243번 - Almost-K Increasing Subsequence
https://www.acmicpc.net/problem/17243
분류 : 다이나믹 프로그래밍

dp[i][c]는 i번째 숫자부터 k가 c일 때 만들 수 있는 부분 수열 중 가장 긴 길이를 의미한다.

*/

package _20230521

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val k = r()
    val nums = IntArray(n) { r() }
    val dp = Array(n) { IntArray(k + 1) }
    fun recursive(count: Int, start: Int): Int {
        if (dp[start][count] != 0) return dp[start][count]
        var max = 1
        for (i in start + 1 until n) {
            if (nums[start] <= nums[i]) {
                max = maxOf(max, recursive(count, i) + 1)
            } else if (count > 0) {
                max = maxOf(max, recursive(count - 1, i) + 1)
            }
        }
        dp[start][count] = max
        return max
    }
    print(recursive(k, 0))
}

/*
5 0
1 2 3 5 4

4


5 1
1 2 3 5 4

5


5 1
1 3 2 4 3

4


5 4
5 4 3 2 1

5


1 0
1

1


5 0
1 3 2 1 3

3  (1 3 3)
*/