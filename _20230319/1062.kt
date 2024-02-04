/*

1062번 - 가르침
https://www.acmicpc.net/problem/1062

단어는 항상 anti로 시작하고 tica로 끝나기 때문에 a,n,t,i,c는 항상 포함된다.
따라서 그 사이에 있는 글자 중 5개에 포함되지 않는 글자들이 얼마나 겹치는지 세는 것이 문제의 해답이다.

dfs를 사용해 모든 알파벳의 k-5개를 고르는 조합(순서X, 중복X)을 찾으며 그 조합에서 몇개의 단어를 배울 수 있는지 확인한다.
그 조합들 중 가장 많은 단어를 배운 개수를 출력한다.

입력받은 글자만으로 조합을 만드는 것도 해봤지만 입력받은 글자만 추출하는 과정이 오래 걸렸는지 시간 초과가 발생했다.

참고 링크
https://moonsbeen.tistory.com/188

*/

package _20230319

/*private inline val Char.idx get() = this - 'a' //a~z의 Char을 0~26으로 매핑
fun main2() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r() //단어 개수
    val k = r() //배울 수 있는 문자의 수
    val words = List(n) { r();sval.drop(4).dropLast(4) } //맨 앞 "anti", 맨 뒤 "tica"를 제외한 단어 목록
    val visited = BooleanArray('z'.idx + 1)
    for (c in listOf('a', 'n', 't', 'i', 'c'))
        visited[c.idx] = true //항상 배워야 하는 글자 수

    fun countWords(): Int {
        var count = 0
        words.forEach { w ->
            for (c in w)
                if (!visited[c.idx])
                    return@forEach
            count++
        }
        return count
    }

    fun dfs(pick: Int, idx: Int): Int {
        var count = 0
        if (pick == k - 5) return countWords()
        for (i in idx..visited.lastIndex) {
            if (!visited[i]) {
                visited[i] = true
                count = maxOf(dfs(pick + 1, i + 1), count)
                visited[i] = false
            }
        }
        return count
    }

    print(dfs(0, 0))
}*/

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val words = Array(n) { readln() }
    val visited = BooleanArray('z' - 'a' + 1)

    fun Char.idx() = this - 'a'

    fun countWords(): Int {
        return words.count { word ->
            word.all { char -> visited[char.idx()] }
        }
    }

    fun dfs(count: Int, start: Int): Int {
        if (count == k) {
            return countWords()
        } else if (count > k) {
            return 0
        }
        var max = 0
        for (idx in start..visited.lastIndex) {
            if (visited[idx].not()) {
                visited[idx] = true
                max = maxOf(dfs(count + 1, idx + 1), max)
                visited[idx] = false
            }
        }
        return max
    }

    val default = "anta" + "tica"
    (default).forEach { visited[it.idx()] = true }
    print(dfs(default.toSet().size, 0))
}

/*

*/