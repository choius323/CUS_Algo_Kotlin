package _20230115/*

3020번 - 개똥벌레
https://www.acmicpc.net/problem/3020



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }

    val n = r()
    val h = r()
    var minBreakCount = n
    var sectionCount = 0
    val upList = IntArray(h + 1)
    val downList = IntArray(h + 1)

    repeat(n) { i ->
        (if (i % 2 == 0) upList else downList)[r()]++
    }
    for (i in h - 2 downTo 0) {
        upList[i] += upList[i + 1]
        downList[i] += downList[i + 1]
    }

    for (i in 1..h) {
        val breakCount = upList[i] + downList[h - i + 1]
        if (breakCount < minBreakCount) {
            minBreakCount = breakCount
            sectionCount = 1
        } else if (minBreakCount == breakCount) {
            sectionCount++
        }
    }
    print("$minBreakCount $sectionCount")
}

/*

*/