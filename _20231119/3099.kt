package _20231119

/*

3099번 - 도트 매트릭스 프린터
https://www.acmicpc.net/problem/3099
분류 : 다이나믹 프로그래밍

*/

fun main() {
    val str = readln()
    val dp = IntArray(str.length) // dp 인덱스 = 문자열 인덱스
    dp[0] = 1
    for (strIdx in 1 until str.length) {
        var minCount = Int.MAX_VALUE
        var countDiffChar = 0
        for (dpIdx in strIdx - 1 downTo 0) {
            if (str[strIdx] == str[dpIdx]) { //같은 글자
                minCount = minOf(minCount, dp[dpIdx] + countDiffChar)
                break
            } else {
                countDiffChar++
                minCount = minOf(minCount, dp[dpIdx] + countDiffChar)
            }
        }
        if (minCount == Int.MAX_VALUE) {
            minCount = dp[strIdx - 1]
        }
        dp[strIdx] = minCount
    }
    print(dp.last() + str.length)
}