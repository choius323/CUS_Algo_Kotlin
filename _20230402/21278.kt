/*

21278번 - 호석이 두 마리 치킨
https://www.acmicpc.net/problem/21278
분류 : 



*/

package _20230402

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val range = 0 until n

    val roads = Array(n) { IntArray(n) { 999 } }
    repeat(n) { roads[it][it] = 0 }
    repeat(r()) {
        val a = r() - 1
        val b = r() - 1
        roads[a][b] = 1
        roads[b][a] = 1
    }
    for (j in range) for (i in range) for (k in range) {
        roads[i][k] = minOf(roads[i][k], roads[i][j] + roads[j][k])
    }

    var minSumDist = 9999
    var minA = 0
    var minB = 0
    for (a in range) for (b in a + 1 until n) {
        val sumDist = roads[a].zip(roads[b]).sumOf { minOf(it.first, it.second) }
        if (sumDist < minSumDist) {
            minA = a
            minB = b
            minSumDist = sumDist
        }
    }

    print("${minA + 1} ${minB + 1} ${minSumDist * 2}")
}

/*

*/