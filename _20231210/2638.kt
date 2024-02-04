package _20231210

/*

2638번 - 치즈
https://www.acmicpc.net/problem/2638
분류 : 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션, 깊이 우선 탐색

2:외부공기, 1:치즈, 0:치즈내부공기
우선 외부 공기를 DFS로 탐색하며 모두 체크한다.
그 후 치즈가 모두 사라질 때 까지 시간을 넘기며 공기와 닫은 치즈를 모두 외부 공기로 바꿔준다.
닫은 치즈는 외부 공기로 바로 바꾸지 않고 모든 영역을 탐색한 뒤 한 번에 바꿔야한다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val n = r()
    val m = r()
    val cheeseArray = Array(n) { IntArray(m) { r() } }
    var totalCheese = cheeseArray.sumOf { array -> array.count { it == 1 } }
    var time = 0

    fun checkOutside(x: Int, y: Int) {
        cheeseArray[y][x] = 2
        for (d in 0..3) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            if (nx in 0 until m && ny in 0 until n && cheeseArray[ny][nx] == 0) {
                checkOutside(nx, ny)
            }
        }
    }

    fun isMelt(x: Int, y: Int): Boolean {
        if (cheeseArray[y][x] != 1) return false
        var count = 0
        for (d in 0..3) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            if (nx in 0 until m && ny in 0 until n && cheeseArray[ny][nx] == 2) {
                count++
            }
        }
        return count >= 2
    }

    checkOutside(0, 0)

    while (totalCheese > 0) {
        time++
        val meltList = mutableListOf<Pair<Int, Int>>()
        for (y in 0 until n) for (x in 0 until m) {
            if (isMelt(x, y)) {
                totalCheese--
                meltList.add(x to y)
            }
        }
        meltList.forEach { (x, y) ->
            checkOutside(x, y)
        }
    }

    print(time)
}

/*
4 5
1 1 1 0 0
1 0 1 0 0
1 1 1 0 0
0 0 0 0 0


*/