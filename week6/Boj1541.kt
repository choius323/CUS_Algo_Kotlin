package week6

/*

1541번 - 잃어버린 괄호
https://www.acmicpc.net/problem/1541

-가 나오면 그 이후의 숫자는 모두 빼면 된다.

*/

fun main() = System.`in`.bufferedReader().run {
    val sb = StringBuilder()
    var isMinus = false
    var answer = 0
    while (ready()) {
        val c = read().toChar()
        if (c == '+' || c == '-') {
            val num = sb.toString().toInt()
            answer += if (isMinus) -num else num
            sb.clear()
            if (c == '-') isMinus = true
        } else {
            sb.append(c)
        }
    }
    val num = sb.toString().trim().toInt()
    answer += if (isMinus) -num else num

    println(answer)
}

/*

*/