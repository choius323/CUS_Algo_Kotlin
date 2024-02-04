/*

17140번 - 이차원 배열과 연산
https://www.acmicpc.net/problem/17140



*/

package _20230319

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val i = { nextToken();nval.toInt() }
    val r = i() - 1
    val c = i() - 1
    val k = i()
    val table = Array(100) { IntArray(100) }
    for (y in 0..2) for (x in 0..2) table[y][x] = i()
    var maxColumn = 2
    var maxRow = 2

    for (time in 0..99) {
        if (table[r][c] == k) return print(time)
        if (maxRow >= maxColumn) {
            for (y in 0..maxRow) {
                val map = mutableMapOf<Int, Int>()
                for (x in 0..maxColumn) {
                    map += table[y][x] to map.getOrDefault(table[y][x], 0) + 1
                }
                map -= 0
                table[y].fill(0, 0, maxColumn + 1)
                val sortedList = map.toList().sortedWith(compareBy({ it.second }, { it.first }))
                for ((index, pair) in sortedList.withIndex()) {
                    val x = 2 * index
                    if (x > 99) break
                    table[y][x] = pair.first
                    table[y][x + 1] = pair.second
                    maxColumn = maxOf(maxColumn, x + 1)
                }
            }
        } else {
            for (x in 0..maxColumn) {
                val map = mutableMapOf<Int, Int>()
                for (y in 0..maxRow) {
                    map += table[y][x] to map.getOrDefault(table[y][x], 0) + 1
                }
                map -= 0
                for (y in 0..maxRow) table[y][x] = 0
                val sortedList = map.toList().sortedWith(compareBy({ it.second }, { it.first }))
                for ((index, pair) in sortedList.withIndex()) {
                    val y = 2 * index
                    if (y > 99) break
                    table[y][x] = pair.first
                    table[y + 1][x] = pair.second
                    maxRow = maxOf(maxRow, y + 1)
                }
            }
        }
    }
    print(if (table[r][c] == k) 100 else -1)
}

/*

*/