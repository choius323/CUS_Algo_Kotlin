/*

1025번 - 제곱수 찾기
https://www.acmicpc.net/problem/1025



*/

fun main() = System.`in`.bufferedReader().run {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val table = List(n) { readLine().toList() }
    println(table)
}

/*

*/