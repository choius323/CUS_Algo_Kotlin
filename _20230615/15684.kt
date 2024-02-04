/*

15684번 - 사다리 조작
https://www.acmicpc.net/problem/15684
분류 : 



*/

package _20230615

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val h = r()
    val ladders = Array(h) { BooleanArray(n - 1) }
    repeat(m) {
        val a = r() - 1
        val b = r() - 1
        ladders[a][b] = true
    }
    fun startGame(): Boolean {
        for (start in 0 until n) {
            var pos = start
            for (depth in 0 until h) {
                if (pos < n - 1 && ladders[depth][pos]) {
                    pos++
                } else if (pos > 0 && ladders[depth][pos - 1]) {
                    pos--
                }
            }
            if (start != pos) return false
        }
        return true
    }

    var answer = 4
    fun dfs(addLadder: Int, startPos: Int, startDepth: Int) {
        if (startGame()) answer = minOf(answer, addLadder)
        //사다리를 4개 이상 놓지 않으므로 3일 떄는 항상 더 진행하지 않는다.
        if (answer == 0 || addLadder >= answer || addLadder == 3) return
        for (depth in startDepth until h) {
            for (pos in 0 until n - 1) {
                if (depth == startDepth && pos < startPos) continue
                if (ladders[depth][pos].not() && (pos == 0 || ladders[depth][pos - 1].not()) && (pos == n - 2 || ladders[depth][pos + 1].not())) {
                    ladders[depth][pos] = true
                    if (addLadder + 1 <= answer) dfs(addLadder + 1, pos + 1, depth)
                    ladders[depth][pos] = false
                }
            }
        }
    }
    dfs(0, 0, 0)
    print(if (answer > 3) -1 else answer)
}

/*
3 1 3
1 1

*/