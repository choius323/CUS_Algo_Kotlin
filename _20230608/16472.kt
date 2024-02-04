/*

16472번 - 고냥이
https://www.acmicpc.net/problem/16472
분류 : 



*/

package _20230608

fun main() {
    val n = readln().toInt()
    val str = readln()
    var left = 0
    var answer = 0
    val wordsCount = IntArray('z' - 'a' + 1)
    var countKind = 0
    for (right in str.indices) {
        val index = str[right] - 'a'
        if (++wordsCount[index] == 1) {
            countKind++
        }
        while (countKind > n) {
            val leftIndex = str[left] - 'a'
            left++
            wordsCount[leftIndex]--
            if (wordsCount[leftIndex] == 0) {
                countKind--
            }
        }
        answer = maxOf(answer, right - left + 1)
    }
    print(answer)
}

/*

*/