/*

6137번 - 문자열 생성
https://www.acmicpc.net/problem/6137
분류 : 



*/

package _20230615

fun main() {
    val n = readln().toInt()
    val chars = CharArray(n) { readln()[0] }
    val tList = mutableListOf<Char>()
    var left = 0
    var right = n - 1
    while (tList.size < n) {
        when (chars[left] compareTo chars[right]) {
            -1 -> tList.add(chars[left++])
            1 -> tList.add(chars[right--])
            0 -> {
                var ll = left
                var rr = right
                while (ll < rr) {
                    when (chars[ll] compareTo chars[rr]) {
                        -1 -> {
                            tList.add(chars[left++])
                            break
                        }

                        1 -> {
                            tList.add(chars[right--])
                            break
                        }

                        0 -> {
                            ll++
                            rr--
                        }
                    }
                }
                if (ll >= rr) tList.add(chars[left++])
            }
        }
    }
    val sb = StringBuilder()
    for (i in tList.indices) {
        sb.append(tList[i])
        if (i != 0 && (i + 1) % 80 == 0) sb.appendLine()
    }
    print(sb)
}

/*
6
A
A
B
A
A
A

*/