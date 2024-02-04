/*

1967번 - 트리의 지름
https://www.acmicpc.net/problem/1967

BFS를 활용한 탐색을 2번 사용해서 풀었다.
처음엔 다익스트라를 활용해서 마지막 노드마다 거리를 구했지만 메모리 초과가 발생했고 결국 답을 찾아봤다.
다익스트라는 그래프에서 가중치가 있거나 중간에 갱신해야 되는 경우에 사용해야 한다.
단 트리는 사이클이 없기 때문에 BFS나 DFS를 사용해도 된다.

*/

package _20230212

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Node(val v: Int, val d: Int)

    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i()
    val tree = Array(n + 1) { HashMap<Int, Int>() }
    repeat(n - 1) {
        val u = i()
        val v = i()
        val d = i()
        tree[u] += v to d
        tree[v] += u to d
    }

    fun bfs(start: Int): Node {
        var max = Node(start, 0)
        val queue = ArrayDeque<Node>()
        queue.add(Node(start, 0))
        val visited = BooleanArray(n + 1)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            tree[node.v].forEach { (nNode, nDist) ->
                if (!visited[nNode]) {
                    val dist = nDist + node.d
                    queue.add(Node(nNode, dist))
                    visited[nNode] = true
                    if (max.d < dist) max = Node(nNode, dist)
                }
            }
        }
        return max
    }
    print(bfs(bfs(1).v).d)
}

//fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
//    fun i(): Int {
//        nextToken()
//        return nval.toInt()
//    }
//
//    val n = i()
//    val last = HashSet<Int>()
//    val tree = Array(n + 1) { HashMap<Int, Int>() }
//    repeat(n - 1) {
//        val u = i()
//        val v = i()
//        val d = i()
//        tree[u] += v to d
//        tree[v] += u to d
//        last.remove(u); last.add(v)
//    }
//
//    var answer = 0
//    val queue = java.util.PriorityQueue<Int>()
//    for (target in last) {
//        val dist = IntArray(n + 1) { 201 }
//        dist[target] = 0
//        queue.clear()
//        queue.add(target)
//        while (queue.isNotEmpty()) {
//            repeat(queue.size) {
//                val q = queue.remove()
//                for (v in 1..n) {
//                    val nDist = dist[q] + tree[q].getOrDefault(v, 201)
//                    if (dist[v] > nDist) {
//                        dist[v] = nDist
//                        queue.add(v)
//                    }
//                }
//            }
//        }
//        answer = dist.maxOf { if (it != 201) it else 0 }
//    }
//    print(answer)
//}

/*
12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10

45



5
1 2 3
1 3 4
1 4 5
1 5 6

*/