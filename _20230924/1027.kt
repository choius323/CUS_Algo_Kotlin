package _20230924

/*

1027번 - 고층 건물
https://www.acmicpc.net/problem/1027
분류 : 수학, 브루트포스 알고리즘, 기하학



*/

fun main() {
    val n = readln().toInt()
    val heights = readln().split(" ").map(String::toInt)

    fun countTower(startIdx: Int, dir: Int): Int {
        var i = startIdx + dir
        var count = 0
        var minGradient = Double.MAX_VALUE
        while(i in 0 until n) {
            val x = Math.abs(startIdx - i)
            val y = heights[startIdx] - heights[i]
            val gradient = y.toDouble() / x
            if(minGradient > gradient) {
                minGradient = gradient
                count++
            }
            i += dir
        }
        return count
    }

    var answer = 0
    for(i in 0 until n) {
        answer = maxOf(answer, countTower(i, -1) + countTower(i, 1))
    }
    print(answer)
}

/*

*/