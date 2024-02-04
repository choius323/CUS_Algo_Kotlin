package _20240128

/*

17485번 - 진우의 달 여행 (Large)
https://www.acmicpc.net/problem/17485
분류 : 다이나믹 프로그래밍

2차원 배열을 만들어서 각 위치에 도달할 때 사용하게 되는 가장 적은 연료를 각 방향마다 저장한다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val max = 1_000_000
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val dp = Array(m) { IntArray(3) }

    fun move(x: Int, dir: Int): Int = // dir: 이동 방향. 왼쪽부터 -1, 0, 1 순서.
        if ((x == 0 && dir == -1) || (x == m - 1 && dir == 1)) {
            max
        } else {
            when (dir) {
                -1 -> minOf(dp[x - 1][1], dp[x - 1][2])
                0 -> minOf(dp[x][0], dp[x][2])
                1 -> minOf(dp[x + 1][0], dp[x + 1][1])
                else -> throw Exception("?")
            }
        }

    repeat(n) { _ ->
        Array(m) { x ->
            val need = r()
            IntArray(3) { need + move(x, it - 1) }
        }.copyInto(dp)
    }
    print(dp.minOf { it.min() })
}

/*

*/