package _20240204

/*

1364번 - 울타리 치기
https://www.acmicpc.net/problem/1364
분류 : 수학

6n번째마다 울타리 범위의 증가량이 1씩 증가하는데, 6n+1번째에만 직전의 증가량이 적용되지 않는다.

https://velog.io/@jibmin/백준-1364-울타리-치기-코틀린

*/

fun main() {
    val n = readln().toLong()
    var sum = 0L
    var inc = 0L
    for (index in 6L..n) {
        sum += inc
        if (index % 6 == 0L) {
            inc++
            sum++
        } else if ((index - 1) % 6 == 0L) {
            sum--
        }
    }
    print(sum + n)
}

/*
1 1
2 2
3 3
4 4
5 5
6 7
7 8
8 10
9 12
10 14
11 16
12 19
13 21

*/