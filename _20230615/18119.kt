/*

18119번 - 단어 암기
https://www.acmicpc.net/problem/18119
분류 : 



*/

package _20230615

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val i = { nextToken();nval.toInt() }
    val s = { nextToken();sval }
    val n = i()
    val m = i()
    val charIndex = Array('z' - 'a' + 1) { mutableSetOf<Int>() }
    val words = IntArray(n) { index ->
        s().toSet().onEach { charIndex[it - 'a'].add(index) }.size
    }
    val wordsCount = words.copyOf()
    val vowel = "aeiou".map { it - 'a' }
    val sb = StringBuilder()
    var count = n
    for (f in 1..m) {
        val isForgot = i() == 1
        val xIndex = s()[0] - 'a'
        if (isForgot && xIndex in vowel) continue
        for (index in charIndex[xIndex]) {
            if (isForgot && wordsCount[index]-- == words[index]) {
                count--
            } else if (isForgot.not() && ++wordsCount[index] == words[index]) {
                count++
            }
        }
        sb.appendLine(count)
    }
    print(sb)
}

/*

*/