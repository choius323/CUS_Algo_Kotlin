package _20240204

/*

1309번 - 동물원
https://www.acmicpc.net/problem/1309
분류 : 다이나믹 프로그래밍

i번째에 사자를 넣은 칸의 수를 L[i], 넣지 않은 칸의 수를 E[i]라고 하면
L[i+1] = L[i] + E[i] + E[i]
E[i+1] = E[i] + L[i]
가 된다.

*/

fun main() {
    val div = 9901
    val n = readln().toInt()
    var input = 2
    var empty = 1
    repeat(n - 1) {
        val nextEmpty = input + empty
        input = (input + empty * 2) % div
        empty = nextEmpty % div
    }
    print((input + empty) % div)
}

/*

*/