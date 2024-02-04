/*

17143번 - 낚시왕
https://www.acmicpc.net/problem/17143
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val i = { nextToken();nval.toInt() }
    val dx = intArrayOf(0, 0, 0, 1, -1)
    val dy = intArrayOf(0, -1, 1, 0, 0)
    val r = i()
    val c = i()
    val map = Array(r) { Array<Shark?>(c) { null } } //각 위치에 상어의 정보. 없으면 null
    repeat(i()) {
        map[i() - 1][i() - 1] = Shark(i(), i(), i())
    }
    var answer = 0
    fun fishing(fisherIndex: Int) {
        for (y in 0 until r) {
            val shark = map[y][fisherIndex]
            if (shark != null) {
                answer += shark.z
                map[y][fisherIndex] = null
                return
            }
        }
    }

    fun moveSharks() {
        val nextMap = Array(r) { Array(c) { mutableListOf<Shark>() } }
        for (y in 0 until r) for (x in 0 until c) {
            val shark = map[y][x] ?: continue
            var nx = x
            var ny = y
            for (move in 1..shark.s) {
                nx += dx[shark.d]
                ny += dy[shark.d]
                if (nx !in 0 until c || ny !in 0 until r) {
                    nx -= dx[shark.d] * 2
                    ny -= dy[shark.d] * 2
                    shark.d += if (shark.d % 2 == 1) 1 else -1
                }
            }
            nextMap[ny][nx] += shark
        }
        for (y in 0 until r) for (x in 0 until c) {
            if (nextMap[y][x].isEmpty()) {
                map[y][x] = null
            } else {
                var maxIndex = 0
                var maxSize = 0
                for (index in nextMap[y][x].indices) {
                    if (maxSize < nextMap[y][x][index].z) {
                        maxSize = nextMap[y][x][index].z
                        maxIndex = index
                    }
                }
                map[y][x] = nextMap[y][x][maxIndex]
            }
        }
    }

    for (fisherIndex in 0 until c) {
        fishing(fisherIndex)
        moveSharks()
    }
    print(answer)
}

data class Shark(
    val s: Int,
    var d: Int,
    var z: Int,
)

/*

*/