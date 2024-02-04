package _20230706

/*

1148번 - 단어 만들기
https://www.acmicpc.net/problem/1148
분류 : 



*/

fun main() = System.`in`.bufferedReader().run {
    val dict = mutableListOf<ByteArray>()
    while (true) {
        val input = readLine()
        if (input[0] == '-') break
        val counts = ByteArray('z' - 'a' + 1)
        for (c in input) counts[c - 'A']++
        dict += counts
    }
    while (true) {
        val str = readLine()
        if (str[0] == '#') break
        val charCounts = IntArray('z' - 'a' + 1)
        val answer = IntArray('z' - 'a' + 1) { -1 }
        for (c in str) {
            charCounts[c - 'A']++
            answer[c - 'A'] = 0
        }
        out@ for (word in dict) {
            for (i in word.indices) {
                if (charCounts[i] < word[i]) {
                    continue@out
                }
            }
            for (i in word.indices) {
                if (word[i] > 0) answer[i]++
            }
        }
        val min = answer.filter { it >= 0 }.min()
        val max = answer.max()
        val minChars = StringBuilder()
        val maxChars = StringBuilder()
        for (i in answer.indices) {
            if (answer[i] == min) minChars.append('A' + i)
            if (answer[i] == max) maxChars.append('A' + i)
        }
        println("$minChars $min $maxChars $max")
    }
}

/*

*/