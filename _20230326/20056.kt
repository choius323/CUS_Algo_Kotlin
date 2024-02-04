/*

20056번 - 마법사 상어와 파이어볼
https://www.acmicpc.net/problem/20056
분류 : 



*/

package _20230326

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val k = r()
    val simulation = Simulation(n, k, Array(m) { Magic(r() - 1, r() - 1, r(), r(), r()) })
    simulation.start()
    print(simulation.getAllMass())
}

private class Simulation(val n: Int, val k: Int, magics: Array<Magic>) {
    private var table: Array<Array<MutableList<Magic>>> = Array(n) { Array(n) { mutableListOf() } }
    private val range = 0 until n
    private val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    private val dy = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)

    private val oddDir = intArrayOf(1, 3, 5, 7)
    private val evenDir = intArrayOf(0, 2, 4, 6)

    init {
        magics.forEach {
            table[it.r][it.c].add(it)
        }
    }

    fun start() = repeat(k) { moveAll() }

    private fun moveAll() {
        val tempTable: Array<Array<MutableList<Magic>>> = Array(n) { Array(n) { mutableListOf() } }
        for (r in range) for (c in range) {
            for (magic in table[r][c]) {
                move(magic).let {
                    tempTable[it.r][it.c].add(it)
                }
            }
        }
        divideMagic(tempTable)
    }

    private fun divideMagic(tempTable: Array<Array<MutableList<Magic>>>) {
        table.forEach { it.forEach { magics -> magics.clear() } }
        for (r in range) for (c in range) {
            val magics = tempTable[r][c]
            val m = magics.sumOf { it.m } / 5
            if (magics.size == 1) {
                table[r][c].add(magics.first())
            } else if (magics.size == 0 || m == 0) {
                continue
            } else {
                val s = magics.sumOf { it.s } / magics.size
                val dirArray = if (magics.all { it.d % 2 == 1 } || magics.all { it.d % 2 == 0 }) evenDir else oddDir
                for (d in dirArray) {
                    table[r][c].add(Magic(r, c, m, s, d))
                }
            }
        }
    }

    private fun move(magic: Magic): Magic = magic.run {
        val nc = posIntoRange(c + dx[d] * s)
        val nr = posIntoRange(r + dy[d] * s)
        Magic(nr, nc, m, s, d)
    }

    private fun posIntoRange(p: Int) = (p % n).let { if (it < 0) it + n else it }

    fun getAllMass() = table.sumOf { it.sumOf { it.sumOf { magic -> magic.m } } }
}

private data class Magic(val r: Int, val c: Int, val m: Int, val s: Int, val d: Int)

/*

*/