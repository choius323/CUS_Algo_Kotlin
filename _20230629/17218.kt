package _20230629

/*

17218번 - 비밀번호 만들기
https://www.acmicpc.net/problem/17218
분류 : 



*/

fun main() {
    val str1 = " " + readln()
    val str2 = " " + readln()
    val dp = Array(str2.length) { IntArray(str1.length) }
    for (i2 in 1..str2.lastIndex) for (i1 in 1..str1.lastIndex) {
        dp[i2][i1] = maxOf(dp[i2 - 1][i1], dp[i2][i1 - 1], dp[i2 - 1][i1 - 1] + if (str1[i1] == str2[i2]) 1 else 0)
    }
    var answer = ""
    var i1 = str1.lastIndex
    var i2 = str2.lastIndex
    while (answer.length < dp.last().last()) {
        when {
            str1[i1] == str2[i2] -> {
                answer += str1[i1]
                i1--
                i2--
            }

            dp[i2 - 1][i1] > dp[i2][i1 - 1] -> i2--
            else -> i1--
        }
    }
    print(answer.reversed())
}

fun main2() {
    val strSet = mutableSetOf<String>()
    fun permutation(str: String, startIndex: Int, newStr: String, lambda: (String) -> Unit) {
        for (index in startIndex..str.lastIndex) {
            val s = newStr + str[index]
            lambda(s)
            permutation(str, index + 1, s, lambda)
        }
    }
    permutation(readln(), 0, "") {
        strSet.add(it)
    }
    var answer = ""
    permutation(readln(), 0, "") {
        if (it in strSet && answer.length < it.length) {
            answer = it
        }
    }
    print(answer)
}

/*
AUTABBEHNSA
BCUAMEFKAJNA

UAENA
*/