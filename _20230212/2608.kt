/*

2608번 - 로마 숫자
https://www.acmicpc.net/problem/2608



*/

package _20230212

fun main() {
    fun oneCharToNum(
        str: String, i: Int,
        one: Char, five: Char, ten: Char,
        unit: Int,
    ): Pair<Int, Int> {
        return when (str.getOrNull(i + 1)) {
            one -> {
                if (str.getOrNull(i + 2) == one) {
                    3 * unit to 3
                } else {
                    2 * unit to 2
                }
            }

            five -> 4 * unit to 2
            ten -> 9 * unit to 2
            else -> 1 * unit to 1
        }
    }

    fun strToNum(str: String): Int {
        var num = 0
        var i = 0
        while (i < str.length) {
            when (str[i]) {
                'I' -> oneCharToNum(str, i, 'I', 'V', 'X', 1)
                'X' -> oneCharToNum(str, i, 'X', 'L', 'C', 10)
                'C' -> oneCharToNum(str, i, 'C', 'D', 'M', 100)
                'M' -> oneCharToNum(str, i, 'M', '.', '.', 1000)
                'V' -> 5 to 1
                'L' -> 50 to 1
                'D' -> 500 to 1
                else -> throw Exception()
            }.let { (incNum, incIndex) ->
                num += incNum
                i += incIndex
            }
        }
        return num
    }

    fun oneNumToStr(inputNum: Int, one: Char, five: Char, ten: Char, unit: Int): String {
        val num = inputNum % (unit * 10)
        return when {
            num >= 9 * unit -> "$one$ten"
            num >= 5 * unit -> "$five${"$one".repeat((num - 5 * unit) / unit)}"
            num >= 4 * unit -> "$one$five"
            else -> "$one".repeat(num / unit)
        }
    }

    fun numToStr(num: Int): String {
        return oneNumToStr(num, 'M', '.', '.', 1000) +
                oneNumToStr(num, 'C', 'D', 'M', 100) +
                oneNumToStr(num, 'X', 'L', 'C', 10) +
                oneNumToStr(num, 'I', 'V', 'X', 1)
    }

    val num1 = strToNum(readln())
    val num2 = strToNum(readln())
    println(num1 + num2)
    print(numToStr(num1 + num2))
}

/*

*/