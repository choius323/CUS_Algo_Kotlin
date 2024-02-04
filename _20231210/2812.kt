package _20231210

/*

2812번 - 크게 만들기
https://www.acmicpc.net/problem/2812
분류 : 자료 구조, 그리디 알고리즘, 스택

앞에 있는 수가 더 클 수록 큰 수가 된다.

*/

fun main() {
    val (n, k) = readln().split(" ").map(String::toInt)
    var count = 0
    val stack = ArrayDeque<Char>()
    for (c in readln()) {
        while (count < k && stack.isNotEmpty() && stack.last() < c) {
            stack.removeLast()
            count++
        }
        stack += c
    }
    repeat(k - count) {
        stack.removeLast()
    }
    print(stack.joinToString(""))
}

/*

*/