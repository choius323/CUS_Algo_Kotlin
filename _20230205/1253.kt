package _20230205

/*

1253번 - 좋다
https://www.acmicpc.net/problem/1253



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val nums = IntArray(n) { r() }.apply { sort() }
    var answer = 0

    for (i in 0 until n) {
        var start = 0
        var end = n - 1
        while (start < end) {
            val sum = nums[start] + nums[end]
            if (sum == nums[i]) {
                when {
                    start == i -> start++
                    end == i -> end--
                    else -> {
                        answer++
                        break
                    }
                }
            } else if (sum < nums[i]) {
                start++
            } else {
                end--
            }
        }
    }
    print(answer)
}

/*

*/