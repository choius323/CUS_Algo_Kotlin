/*

17144번 - 미세먼지 안녕!
https://www.acmicpc.net/problem/17144
분류 : 



*/

package _20230402

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val i = { nextToken();nval.toInt() }
    val size = Pos(r = i(), c = i())
    val house = Array(size.r) { IntArray(size.c) }
    val t = i()
    val cleaner = Array(2) { Pos(-1, -1) }
    for (r in 0 until size.r) for (c in 0 until size.c) {
        val pos = Pos(c, r)
        val a = i()
        if (c == 0 && a == -1) {
            cleaner[if (cleaner.first().r == -1) 0 else 1] = pos
        }
        house[r][c] = a
    }
    repeat(t) {
        diffuse(house, size, cleaner)
        blow(house, size, cleaner)
    }
    print(house.sumOf { it.sum() } + 2)
}

private fun diffuse(house: Array<IntArray>, size: Pos, cleaner: Array<Pos>) {
    val dPos = arrayOf(Pos(-1, 0), Pos(1, 0), Pos(0, -1), Pos(0, 1))
    val tempHouse = Array(size.r) { IntArray(size.c) }
    for (r in 0 until size.r) for (c in 0 until size.c) {
        val pos = Pos(c, r)
        if (cleaner.contains(pos)) {
            tempHouse[r][c] = -1
            continue
        }
        val a = house[r][c]
        val moveA = a / 5
        var count = 0
        for (d in dPos) {
            val nPos = Pos(c + d.c, r + d.r)
            if (cleaner.contains(nPos).not() && nPos.r in 0 until size.r && nPos.c in 0 until size.c) {
                count++
                tempHouse[nPos.r][nPos.c] += moveA
            }
        }
        tempHouse[pos.r][pos.c] += a - moveA * count
    }
    tempHouse.copyInto(house)
}

private fun blow(house: Array<IntArray>, size: Pos, cleaner: Array<Pos>) {
    //공기청정기 위쪽 원
    for (r in cleaner[0].r - 2 downTo 0) house[r + 1][0] = house[r][0]
    for (c in 1 until size.c) house[0][c - 1] = house[0][c]
    for (r in 1..cleaner[0].r) house[r - 1][size.c - 1] = house[r][size.c - 1]
    for (c in size.c - 2 downTo 1) house[cleaner[0].r][c + 1] = house[cleaner[0].r][c]
    house[cleaner[0].r][cleaner[0].c + 1] = 0

    //공기청정기 아래쪽 원
    for (r in cleaner[1].r + 2 until size.r) house[r - 1][0] = house[r][0]
    for (c in 1 until size.c) house[size.r - 1][c - 1] = house[size.r - 1][c]
    for (r in size.r - 2 downTo cleaner[1].r) house[r + 1][size.c - 1] = house[r][size.c - 1]
    for (c in size.c - 2 downTo 1) house[cleaner[1].r][c + 1] = house[cleaner[1].r][c]
    house[cleaner[1].r][cleaner[1].c + 1] = 0
}

private data class Pos(val c: Int, val r: Int)

/*

*/