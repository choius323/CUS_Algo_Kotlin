/*

1238번 - 파티
https://www.acmicpc.net/problem/1238

다익스트라를 사용해서 왕복 거리를 구하는 문제이다.
2가지 방법으로 풀어봤는데 처음은 모든 정점에서 다익스트라를 사용하는 것이다.
시간 복잡도는 O(n*mlogm)이고 메모리도 모든 다익스트라에서 나온 거리를 저장해야 하기 때문에 n*n배열이 필요하다.

두 번째는 다익스트라를 2번 사용하는 것이다.
구해야 하는 거리는 x에서 출발한 거리와 x까지 가는데 걸리는 거리이다.
x에서 출발한 것은 평범한 다익스트라이고 x까지 걸리는 것은 모든 간선을 거꾸로 한 뒤에 x에서 다익스트라를 하면 된다.
2->1, 3->1인 간선이 있을 때 그 간선을 뒤집으면 1->2, 1->3이 된다.
이 때 1에서 다익스트라를 사용하면 2와 3에서 출발했을 때 1까지의 거리가 나온다.

*/

package _20230319

// 다익스트라 2번 사용
fun main3() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Road(val v: Int, val d: Int)

    val i = { nextToken();nval.toInt() }
    val n = i()
    val m = i()
    val x = i()

    fun dijkstra(road: Array<out List<Road>>): IntArray {
        val dist = IntArray(n + 1) { 99999 }
        dist[0] = 0
        val pq = ArrayDeque<Int>()
        pq.add(x)
        dist[x] = 0
        while (pq.isNotEmpty()) {
            val q = pq.removeFirst()
            for ((v, d) in road[q]) {
                if (dist[v] > dist[q] + d) {
                    dist[v] = dist[q] + d
                    pq.add(v)
                }
            }
        }
        return dist
    }

    val road = Array(n + 1) { ArrayList<Road>() }
    val roadRe = Array(n + 1) { ArrayList<Road>() }
    repeat(m) {
        val u = i()
        val v = i()
        val d = i()
        road[u].add(Road(v, d))
        roadRe[v].add(Road(u, d))
    }

    val dist = dijkstra(road)
    val distRe = dijkstra(roadRe)
    print(dist.zip(distRe).maxOf { it.first + it.second })
}

// 모든 정점에 대한 다익스트라 실행. O(n*mlogm)
/*
fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Road(val v: Int, val d: Int)

    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i()
    val m = i()
    val x = i()
    val road = Array(n + 1) { ArrayList<Road>() }
    repeat(m) { road[i()].add(Road(i(), i())) }
    val pq = java.util.PriorityQueue<Int>()
    val dist = Array(n + 1) { IntArray(n + 1) { 99999 } }
    for (u in 1..n) {
        pq.add(u)
        dist[u][u] = 0
        while (pq.isNotEmpty()) {
            val q = pq.poll()
            for ((v, d) in road[q]) {
                if (dist[u][v] > dist[u][q] + d) {
                    dist[u][v] = dist[u][q] + d
                    pq.add(v)
                }
            }
        }
    }
    var answer = 0
    for (i in 1..n) {
        if (i != x)
            answer = maxOf(dist[i][x] + dist[x][i], answer)
    }
    print(answer)
}
*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun calcDist(roads: Array<IntArray>, start: Int): IntArray {
        val dists = IntArray(roads.size) { 100 * roads.size + 1 }
        val queue = ArrayDeque<Int>()
        queue.add(start)
        dists[start] = 0
        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            for (i in roads.indices) {
                if (dists[now] + roads[now][i] < dists[i]) {
                    dists[i] = dists[now] + roads[now][i]
                    queue.add(i)
                }
            }
        }
        return dists
    }

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val x = r() - 1
    val roads = Array(n) { IntArray(n) { 100 * n + 1 } }
    val roadsReverse = Array(n) { IntArray(n) { 100 * n + 1 } }
    repeat(n) {
        roads[it][it] = 0
        roadsReverse[it][it] = 0
    }
    repeat(m) {
        val u = r() - 1
        val v = r() - 1
        val t = r()
        roads[u][v] = t
        roadsReverse[v][u] = t
    }

    val dists = calcDist(roads, x)
    val distsReverse = calcDist(roadsReverse, x)
    print(dists.zip(distsReverse).maxOf { it.first + it.second })
}

/*
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

10




6 20 3
3 2 45
6 1 66
6 2 31
2 4 94
5 3 46
5 2 79
3 1 64
4 3 74
3 5 59
1 6 93
3 6 45
6 4 40
3 4 67
1 3 61
1 2 42
4 2 50
4 1 55
2 6 93
5 4 95
1 4 54

213
*/