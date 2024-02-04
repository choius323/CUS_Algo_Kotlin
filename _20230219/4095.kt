/*

4095번 - 최대 정사각형
https://www.acmicpc.net/problem/4095

DP를 사용해서 배열의 모든 값을 확인하며 자신의 왼쪽, 왼쪽위, 위에 있는 값 중 최솟값+1을 저장하는 방식으로 했다.

01
11
에서 정사각형의 최대 길이는 1이지만

11
11
이 있는 배열에서 마지막 값은 minOf(1, 1, 1)+1이 되며 변의 길이가 2인 정사각형을 저장하게 된다.

1~n, 1~m을 확인하게 되면 0번째 열, 0번째 행에만 1이 있을 때 정사각형을 찾을 수 없으므로 따로 확인해야 한다.

*/

package _20230219

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val sb = StringBuilder()
    while (true) {
        val n = r()
        val m = r()
        if (n == 0) return print(sb)
        var answer = 0
        val board = Array(n) { IntArray(m) { r().also { num -> if (num == 1) answer = 1 } } }
        for (y in 1 until n) for (x in 1 until m) {
            if (board[y][x] > 0) {
                board[y][x] = minOf(board[y - 1][x - 1], board[y][x - 1], board[y - 1][x]) + 1
                answer = maxOf(answer, board[y][x])
            }
        }
        sb.appendLine(answer)
    }
}

/*

*/