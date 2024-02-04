/*

2179번 - 비슷한 단어
https://www.acmicpc.net/problem/2179

단어를 기준으로 입력된 단어들을 정렬하면, 접두사가 같은 단어가 모두 붙어있게 된다.
단, 출력은 입력받은 순서로 해야되므로 입력받을 때 순서를 미리 저장하고, 그 순서에 맞춰서 출력을 해야한다.

*/

package _20230219

fun main() {
    // 접두사 저장(접두사, 입력 순서1, 입력 순서2)
    data class Prefix(val str: String, val s: Int, val t: Int)

    // 입력 단어(문자열, 입력 순서)
    data class Word(val str: String, val index: Int)

    // 공통 접두사 구하기
    fun commonPrefix(str1: String, str2: String): String {
        if (str1 == str2) return ""

        val length = minOf(str1.length, str2.length)
        val sb = StringBuilder()
        for (i in 0 until length) {
            if (str1[i] == str2[i]) sb.append(str1[i])
            else break
        }
        return sb.toString()
    }

    val n = readln().toInt()
    val originalWords = Array(n) { Word(readln(), it) }
    val words = originalWords.sortedArrayWith(compareBy { it.str }) // 입력된 단어들을 문자열 기준으로 정렬
    val prefixes = mutableListOf<Prefix>() // 최대 길이의 공통 접두사를 가진 단어 2개를 저장 
    var maxLength = 0 // 공통 접두사의 최대 길이
    var sIndex = n // 단어 S의 입력 순서

    for (i in 0 until n - 1) {
        val prefix = commonPrefix(words[i].str, words[i + 1].str) // i, i+1번째 단어의 공통 접두사
        if (maxLength <= prefix.length) { // 기존 공통 접두사의 길이보다 크거나 같을 때
            if (maxLength < prefix.length) { // 더 크면 초기화
                prefixes.clear()
                maxLength = prefix.length
                sIndex = n
            }
            sIndex = minOf(sIndex, words[i].index, words[i + 1].index) // 가장 먼저 입력되는 단어의 인덱스 저장
            prefixes.add(Prefix(prefix, words[i].index, words[i + 1].index))
        }
    }

    var tIndex = n // 단어 T의 입력 순서
    val prefix = originalWords[sIndex].str.substring(0 until maxLength) // 찾아야 하는 단어의 접두사

    for (p in prefixes) {
        if (p.str == prefix) {
            tIndex = when (sIndex) {
                p.s -> minOf(tIndex, p.t)
                p.t -> minOf(tIndex, p.s)
                else -> minOf(tIndex, p.s, p.t) // 접두사는 같지만 직접 비교하지 않아서 떨어져 있을 수 있음
            }
        }
    }

    println(originalWords[sIndex].str)
    print(originalWords[tIndex].str)
}

/*

*/