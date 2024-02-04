/*

23326번 - 홍익 투어리스트
https://www.acmicpc.net/problem/23326
분류 : 



*/

package _20230608

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val q = r()
    val place = java.util.TreeSet<Int>()
    repeat(n) {
        if (r() == 1) place.add(it + 1)
    }
    val sb = StringBuilder()
    var now = 1
    repeat(q) { _ ->
        when (r()) {
            1 -> {
                val i = r()
                if (place.remove(i).not()) {
                    place += i
                }
            }

            2 -> now = (now + r() - 1) % n + 1
            3 -> sb.appendLine(
                if (place.isEmpty()) {
                    -1
                } else {
                    val next = place.higher(now)
                    if (now in place) {
                        0
                    } else if (next == null) {
                        place.first() - (now - n)
                    } else {
                        next - now
                    }
                }
            )
        }
    }
    print(sb)
}

/*
5 8
0 1 0 0 0
2 2
3
1 5
3
1 4
3
2 20
3

4
2
1
1
*/