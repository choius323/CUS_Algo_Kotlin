package _20240128

/*

6137번 - 문자열 생성
https://www.acmicpc.net/problem/6137
분류 : 그리디 알고리즘, 문자열, 두 포인터

문자열 S의 처음과 마지막 중 빠른 문자를 T에 추가하고 S에서 제거한다.
만약 처음과 마지막이 같은 문자라면 안쪽으로 들어가며 빠른 문자가 더 먼저 나오는 쪽의 문자를 제거한다.
이 과정을 S가 없어질 때 까지 반복한다.

*/

fun main() = System.`in`.bufferedReader().run {
    val n = readLine().toInt()
    val chars = CharArray(n) { readLine()[0] }
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
        if ((i + 1) % 80 == 0) sb.appendLine()
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