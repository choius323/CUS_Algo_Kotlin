/*

13458번 - 시험 감독
https://www.acmicpc.net/problem/13458
분류 : 수학, 사칙연산


*/

package _20230326

import kotlin.math.ceil

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val aArray = IntArray(n) { r() }
    val b = r()
    val c = r()
    var answer = n.toLong()
    for (a in aArray) {
        answer += ceil(maxOf(0, a - b).toDouble() / c).toLong()
    }
    print(answer)
}

/*
5
9 10 11 12 13
7 3
*/