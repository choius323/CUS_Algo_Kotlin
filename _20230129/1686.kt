package _20230129/*

1686번 - 복날
https://www.acmicpc.net/problem/1686



*/

fun main() = System.`in`.bufferedReader().run {
    data class Pos(val x: Double, val y: Double) {
        constructor(list: List<Double>) : this(list[0], list[1])
    }

    val (v, m) = readLine().split(" ").map { it.toInt() }
    val start = Pos(readLine().split(" ").map { it.toDouble() })
    val goal = Pos(readLine().split(" ").map { it.toDouble() })
    var answer = 0

    val bunkers = ArrayList<Pos>()
    while (ready()) {
        bunkers.add(Pos(readLine().split(" ").map { it.toDouble() }))
    }


    print(if (answer == 0) "No." else "Yes, visiting $answer other holes.")
}

/*

*/