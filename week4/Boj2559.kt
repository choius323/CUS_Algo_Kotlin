/*

수열
https://www.acmicpc.net/problem/2559

 */

package week4

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken(); nval.toInt() }
    val n = r()
    val k = r()
    val nums = List(n) { r() }
    var sum = nums.slice(0 until k).sum()
    var answer = sum
    for (i in k until n) {
        sum += -nums[i - k] + nums[i]
        answer = maxOf(sum, answer)
    }
    print(answer)
}