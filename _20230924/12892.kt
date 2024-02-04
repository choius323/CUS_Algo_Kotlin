package _20230924

/*

12892번 - 생일 선물
https://www.acmicpc.net/problem/12892
분류 : 정렬, 두 포인터



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Gift(val p: Int, val v: Int)
    val r = {nextToken();nval.toInt()}
    val n = r()
    val d = r()
    val gifts = Array(n) { Gift(r(), r()) }.sortedArrayWith(compareBy(Gift::p))
    var left = 0
    var value = 0L
    var maxValue = 0L
    for(right in 0 until n) {
        while(left < right && gifts[right].p - gifts[left].p >= d) {
            value -= gifts[left].v
            left++
        }
        value += gifts[right].v
        maxValue = maxOf(maxValue, value)
    }
    print(maxValue)
}

/*

*/