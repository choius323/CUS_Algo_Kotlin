package _20230706

/*

8980번 - 택배
https://www.acmicpc.net/problem/8980
분류 : 그리디, 정렬

택배의 도착 지점을 기준으로 오른차순 정렬하면 된다. --> ????

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Box(val s: Int, val e: Int, val w: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val c = r()
    val boxes = Array(r()) { Box(r(), r(), r()) }.sortedArrayWith(compareBy(Box::e))
    val towns = IntArray(n + 1)
    var answer = 0
    for (box in boxes) {
        var min = box.w
        for (i in box.s until box.e) {
            min = minOf(min, c - towns[i])
        }
        for (i in box.s until box.e) {
            towns[i] += min
        }
        answer += min
    }
    print(answer)
}

/*
6 60
5
1 2 30
2 5 70
5 6 60
3 4 40
1 6 40

150


2000 10000
3
1 2000 100
30 100 1000
1000 1001 10
*/