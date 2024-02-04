package _20240204

/*

17471번 - 게리맨더링
https://www.acmicpc.net/problem/17471
분류 : 수학, 그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색, 조합론, 깊이 우선 탐색

재귀 함수를 사용한 부분 집합으로 선거구 A로 지정할 구역을 지정하고 나머지 선택되지 않은 구역을 선거구 B로 지정한다.
각각 A와 B에 최소 1개 이상의 구역이 있는지 확인한다.
이후 각각 선거구를 BFS로 탐색하며 모두 연결되어 있는지 확인하며 선거구의 인원을 계산한다.
계산한 인원으로 각 선거구의 차이를 구한다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val populations = IntArray(n) { r() }
    val graph = Array(n) { IntArray(r()) { r() - 1 } }
    var answer = Int.MAX_VALUE
    val selected = BooleanArray(n)

    // 선거구의 한 지점에 연결되어 있는 모든 구역을 방문하며 인구 수 합산.
    fun bfs(startIdx: Int, visited: BooleanArray): Int {
        val deque = ArrayDeque<Int>()
        deque.add(startIdx)
        visited[startIdx] = true
        var count = populations[startIdx]
        while (deque.isNotEmpty()) {
            val idx = deque.removeFirst()
            for (nextIdx in graph[idx]) {
                if (visited[nextIdx] || selected[nextIdx] != selected[startIdx]) continue
                deque += nextIdx
                visited[nextIdx] = true
                count += populations[nextIdx]
            }
        }
        return count
    }

    // 나눠진 선거구가 제대로 이루이져 있는지 확인. 맞다면 인원 차이 계산
    fun checkSplit() {
        val visited = BooleanArray(n)
        val otherSection = selected.indexOfFirst { it != selected[0] }
        if (otherSection == -1) return
        val diff = kotlin.math.abs(bfs(0, visited) - bfs(otherSection, visited))
        if (visited.all { it }.not()) return
        answer = minOf(answer, diff)
    }

    // 재귀 함수로 각 선거구를 부분 집합으로 나눔
    fun subset(idx: Int) {
        if (idx == n) {
            checkSplit()
            return
        }
        selected[idx] = true
        subset(idx + 1)
        selected[idx] = false
        subset(idx + 1)
    }

    subset(0)
    print(if (answer == Int.MAX_VALUE) -1 else answer)
}

/*

*/