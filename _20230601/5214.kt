/*

5214번 - 환승
https://www.acmicpc.net/problem/5214
분류 : 



*/

package _20230601

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    if (n == 1) return print(1)
    val k = r()
    val m = r()
    //각 노드(역)에 연결된 하이퍼튜브의 번호를 저장
    val graph = Array(n + 1) { mutableListOf<Int>() }
    //N번 노드(역)에 연결된 하이퍼 튜브를 저장
    val goalTube = BooleanArray(m)
    val tubes = Array<IntArray>(m) {
        IntArray(k) { _ ->
            r().also { node ->
                graph[node] += it
                if (node == n) goalTube[it] = true
            }
        }
    }

    var count = 0
    //방문하는 튜브를 저장
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(m)
    graph[1].forEach { tubeIndex ->
        queue.add(tubeIndex)
        visited[tubeIndex] = true
    }
    while (queue.isNotEmpty()) {
        count++
        repeat(queue.size) {
            val now = queue.removeFirst()
            if (goalTube[now]) return print(count + 1)
            for (nextNode in tubes[now]) {
                for (nextTube in graph[nextNode]) {
                    if (visited[nextTube]) continue
                    visited[nextTube] = true
                    queue.add(nextTube)
                }
            }
        }
    }
    print(-1)
}

/*fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val k = r()
    val m = r()
    val graph = Array(n + 1) { mutableListOf<Int>() }
    val tubes = mutableListOf<IntArray>()
    repeat(m) { _ ->
        val tube = IntArray(k) { r() }
        val num = tubes.size
        tubes.add(tube)
        tube.forEach {
            graph[it] += num
        }
    }

    var count = 0
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(n + 1)
    queue.add(1)
    visited[1] = true
    while (queue.isNotEmpty()) {
        count++
        repeat(queue.size) {
            val now = queue.removeFirst()
            if (now == n) return print(count)
            for (tubeIndex in graph[now]) {
                for (next in tubes[tubeIndex]) {
                    if (visited[next]) continue
                    visited[next] = true
                    queue.add(next)
                }
            }
        }
    }
    print(-1)
}*/

/*

*/