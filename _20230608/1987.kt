/*

1987번 - 알파벳
https://www.acmicpc.net/problem/1987
분류 : 



*/

package _20230608

fun main() = System.`in`.bufferedReader().run {
    fun Char.toNum() = this - 'A'
    val (r, c) = readLine().split(" ").map(String::toInt)
    val board = Array(r) { readLine() }
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var answer = 0
    fun dfs(x: Int, y: Int, visited: Int, count: Int) {
        answer = maxOf(answer, count)
        for (d in 0..3) {
            val nx = x + dx[d]
            val ny = y + dy[d]
            if (nx in 0 until c && ny in 0 until r) {
                val nextNum = 1 shl board[ny][nx].toNum()
                if (visited and nextNum == 0) {
                    dfs(nx, ny, visited or nextNum, count + 1)
                }
            }
        }
    }
    dfs(0, 0, 1 shl board[0][0].toNum(), 1)
    print(answer)
}

/*
20 20
ABCDEFGHIJKLMNOPQRST
BCDEFGHIJKLMNOPQRSTU
CDEFGHIJKLMNOPQRSTUV
DEFGHIJKLMNOPQRSTUVW
EFGHIJKLMNOPQRSTUVWX
FGHIJKLMNOPQRSTUVWXY
GHIJKLMNOPQRSTUVWXYZ
HIJKLMNOPQRSTUVWXYZA
IJKLMNOPQRSTUVWXYZAB
JKLMNOPQRSTUVWXYZABC
KLMNOPQRSTUVWXYZABCD
LMNOPQRSTUVWXYZABCDE
MNOPQRSTUVWXYZABCDEF
NOPQRSTUVWXYZABCDEFG
OPQRSTUVWXYZABCDEFGH
PQRSTUVWXYZABCDEFGHI
QRSTUVWXYZABCDEFGHIJ
RSTUVWXYZABCDEFGHIJK
STUVWXYZABCDEFGHIJKL
TUVWXYZABCDEFGHIJKLM

26
*/