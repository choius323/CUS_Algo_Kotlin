/*

2096번 - 내려가기
https://www.acmicpc.net/problem/2096

메모이제이션 기법을 사용하는 DP문제이다.
dp의 i번째 값은 dp의 i-1번째 값과 i번째 점수에 의해 결정된다.
최대 점수와 최소 점수는 각각 계산을 해야 한다.

*/

package _20230601

/*fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i() //줄의 수
    val score = Array(3) { i() } //현재 계산할 줄의 점수
    var maxDp = score.toIntArray() //최대 점수 저장
    var minDp = score.toIntArray() //최소 점수 저장
    repeat(n - 1) { //첫번째 칸은 미리 계산했기 때문에 n-1번
        repeat(3) { score[it] = i() }
        maxDp = intArrayOf(
            maxOf(maxDp[0], maxDp[1]) + score[0], //직전 칸의 0,1번째 점수 중 최대 점수 + 현재 칸의 0번째 점수
            maxDp.maxOf { it } + score[1], //직전 칸의 최대 점수 + 현재 칸의 1번째 점수
            maxOf(maxDp[1], maxDp[2]) + score[2], //직전 칸의 1,2번째 점수 중 최대 점수 + 현재 칸의 2번째 점수
        )
        minDp = intArrayOf(
            minOf(minDp[0], minDp[1]) + score[0],
            minDp.minOf { it } + score[1],
            minOf(minDp[1], minDp[2]) + score[2],
        )
    }
    print("${maxDp.maxOf { it }} ${minDp.minOf { it }}")
}*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val dp = Array(3) { IntArray(2) }
    repeat(r()) {
        val newDp = Array(3) { r();IntArray(2) { nval.toInt() } }
        newDp[0][0] += maxOf(dp[0][0], dp[1][0])
        newDp[0][1] += minOf(dp[0][1], dp[1][1])
        newDp[1][0] += maxOf(dp[0][0], dp[1][0], dp[2][0])
        newDp[1][1] += minOf(dp[0][1], dp[1][1], dp[2][1])
        newDp[2][0] += maxOf(dp[1][0], dp[2][0])
        newDp[2][1] += minOf(dp[1][1], dp[2][1])
        newDp.copyInto(dp)
    }
    print("${dp.maxOf { it[0] }} ${dp.minOf { it[1] }}")
}

/*
5
1 2 3
4 5 6
4 9 0
3 4 5
8 6 1

30 11
*/