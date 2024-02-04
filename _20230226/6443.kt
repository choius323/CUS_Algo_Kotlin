/*

6443번 - 애너그램
https://www.acmicpc.net/problem/6443

사전순으로 출력해야 되기 때문에 작은 글자부터 DFS로 탐색하며 문자열을 만든다.
알파벳마다 나오는 횟수를 저장하고, 그 횟수를 기준으로 탐색하면 aa를 2번 출력하는 것과 같은 반복 출력을 방지할 수 있다.

*/

package _20230226

fun main() {
    val sb = StringBuilder()

    fun tracking(alphabet: IntArray, length: Int, str: String) {
        if (str.length == length) {
            sb.appendLine(str)
            return
        }
        for (i in alphabet.indices) {
            if (alphabet[i] > 0) {
                alphabet[i]--
                tracking(alphabet, length, str + (i + 'a'.code).toChar())
                alphabet[i]++
            }
        }
    }

    repeat(readln().toInt()) {
        val input = readln()
        val alphabet = IntArray('z' - 'a' + 1)
        input.forEach { alphabet[it - 'a']++ }
        tracking(alphabet, input.length, "")
    }
    print(sb)
}

/*

*/