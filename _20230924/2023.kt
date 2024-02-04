package _20230924

/*

2023번 - 신기한 소수
https://www.acmicpc.net/problem/2023
분류 : 수학, 정수론, 백트래킹, 소수 판정



*/

fun main() {
    val n = readln().toInt()
    val sb = StringBuilder()

    fun isPrime(num: Int): Boolean {
        for (i in 2..kotlin.math.sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }

    fun recur(str: String) {
        if (n == str.length) {
            if (isPrime(str.toInt())) {
                sb.appendLine(str)
            }
            return
        }
        for (c in 1..9 step 2) {
            val next = str + c
            if (isPrime(next.toInt())) {
                recur(str + c)
            }
        }
    }

    listOf(2, 3, 5, 7).forEach {
        recur("$it")
    }

    print(sb)
}

/*

*/