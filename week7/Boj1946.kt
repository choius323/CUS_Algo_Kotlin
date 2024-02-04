/*

1946번 - 신입 사원
https://www.acmicpc.net/problem/1946



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    repeat(r()) {
        val n = r()
        val applyList = MutableList(n) { 0 } //index:서류, value:면접
        repeat(n) {
            applyList[r() - 1] = r() - 1
        }
        var count = 0
        var max = 99999999
        for (apply in applyList) {
            if (apply < max) {
                max = apply
                count++
            }
        }
        println(count)
    }
}

/*

*/