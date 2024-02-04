package _20230713

/*

16987번 - 계란으로 계란치기
https://www.acmicpc.net/problem/16987
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Egg(var shield: Int, val weight: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val eggs = Array(n) { Egg(r(), r()) }
    var max = 0
    fun dfs(index: Int, count: Int) {
        if (index == n) {
            max = maxOf(max, count)
            return
        }
        if (eggs[index].shield <= 0) return dfs(index + 1, count)
        var isHit = false
        for (i in 0 until n) {
            if (i == index || eggs[i].shield <= 0) continue
            isHit = true
            eggs[index].shield -= eggs[i].weight
            eggs[i].shield -= eggs[index].weight
            val nCount = count +
                    (if (eggs[index].shield <= 0) 1 else 0) +
                    (if (eggs[i].shield <= 0) 1 else 0)
            dfs(index + 1, nCount)
            eggs[index].shield += eggs[i].weight
            eggs[i].shield += eggs[index].weight
        }
        if (isHit.not()) dfs(index + 1, count)
    }
    dfs(0, 0)
    print(max)
}

/*

*/