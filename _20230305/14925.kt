/*

14925번 - 목장 건설하기
https://www.acmicpc.net/problem/14925



*/

package _20230305

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val m = r()
    val n = r()
    val land = Array(m) { IntArray(n) { if (r() == 0) 1 else 0 } }
    for (y in 1 until m) for (x in 1 until n) {
        if (land[y][x] > 0) {
            land[y][x] = minOf(land[y - 1][x - 1], land[y][x - 1], land[y - 1][x]) + 1
        }
    }
    print(land.maxOf { it.max() })
}

/*
5 3
1 0 0
0 0 0
2 0 1
1 0 0
0 0 0
*/