package _20231119

/*

1339번 - 단어 수학
https://www.acmicpc.net/problem/1339
분류 : 그리디 알고리즘

*/

fun main() {
    val strings = Array(readln().toInt()) { readln() }
    val chars = strings.flatMap(String::toList).toSet()
    val charMap = mutableMapOf<Char, Int>()

    fun String.toNum(): Int = fold(0) { acc, char -> acc * 10 + charMap[char]!! }

    fun permutation(nextNum: Int): Int {
        if (nextNum == 9 - chars.size) {
            return strings.sumOf(String::toNum)
        }
        var max = 0
        for (char in chars) {
            if (char in charMap) continue
            charMap += char to nextNum
            max = maxOf(max, permutation(nextNum - 1))
            charMap -= char
        }
        return max
    }
    print(permutation(9))
}