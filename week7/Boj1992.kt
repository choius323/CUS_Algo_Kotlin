/*

1992번 - 쿼드트리
https://www.acmicpc.net/problem/1992



*/

fun main() = System.`in`.bufferedReader().run {
    val sb = StringBuilder()
    val picture = List(readLine().toInt()) { readLine().toList() }

    fun isSame(l: Int, t: Int, len: Int): Boolean {
        for (y in t until t + len) {
            for (x in l until l + len) {
                if (picture[t][l] != picture[y][x])
                    return false
            }
        }
        return true
    }

    fun dfs(l: Int, t: Int, len: Int) {
        if (isSame(l, t, len)) {
            sb.append(picture[t][l])
            return
        }
        sb.append("(")
        dfs(l, t, len / 2)
        dfs(l + len / 2, t, len / 2)
        dfs(l, t + len / 2, len / 2)
        dfs(l + len / 2, t + len / 2, len / 2)
        sb.append(")")
    }

    dfs(0, 0, picture.size)
    print(sb)
}

/*
8
11110000
11110000
00011100
00011100
11110000
11110000
11110011
11110011
*/