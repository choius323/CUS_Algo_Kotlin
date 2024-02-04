package _20230713

/*

15686번 - 치킨 배달
https://www.acmicpc.net/problem/15686
https://velog.io/@choius323/Kotlin-백준-15686번-치킨-배달

브루트 포스를 활용하는 문제이다.
우선 집과 가게의 좌표를 각각 저장한다. 그리고 각각의 집과 가게 사이의 거리를 2차원 배열에 저장한다.
그리고 DFS로 가게를 M개 고르는 완전 탐색을 한다.
고른 가게 만으로 나오는 도시의 치킨 거기를 미리 계산해둔 거리 배열로 구한다.

*/

import kotlin.math.abs

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val x: Int, val y: Int) //좌표를 저장할 클래스

    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    fun getDist(pos1: Pos, pos2: Pos) = abs(pos1.x - pos2.x) + abs(pos1.y - pos2.y) //두 지점의 거리 계산

    val home = ArrayList<Pos>() //집 배열
    val rest = ArrayList<Pos>() //가게 배열
    val n = i() //가로(세로) 길이
    val m = i() //남겨야 할 가게 수
    for (y in 1..n) for (x in 1..n) {
        when (i()) {
            1 -> home
            2 -> rest
            else -> continue
        }.add(Pos(x, y)) //1, 2일 때 각각 배열에 좌표 저장
    }

    val distList = Array(home.size) { it1 -> List(rest.size) { it2 -> getDist(home[it1], rest[it2]) } } //집과 가게의 거리 저장
    var ans = 9999 //도시의 치킨 거리 최소값
    val visited = BooleanArray(rest.size) //DFS를 탐색하며 방문했는지 확인

    fun dfs(count: Int, idx: Int) {
        if (count == m) { //가게 수 조건 충족
            var dist = 0 //지금 분기에서 도시의 치킨 거리
            distList.forEach { //it : 집의 모든 치킨 거리
                var min = 99 //이 집의 최소 치킨 거리
                for (i in it.indices)
                    if (visited[i] && min > it[i]) //i번째 가게를 없애지 않았고 가장 가까운 경우
                        min = it[i]
                dist += min
            }
            ans = minOf(ans, dist)
            return
        }
        for (i in idx until rest.size) {
            visited[i] = true
            dfs(count + 1, i + 1) //같은 인덱스를 탐색하지 않게 i+1을 전달
            visited[i] = false
        }
    }
    dfs(0, 0)
    print(ans)
}

fun main2() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Pos(val x: Int, val y: Int) {
        fun dist(o: Pos): Int = Math.abs(x - o.x) + Math.abs(y - o.y)
    }

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val homes = ArrayList<Pos>()
    val chickens = ArrayList<Pos>()
    for (y in 0 until n) for (x in 0 until n) {
        when (r()) {
            1 -> homes.add(Pos(x, y))
            2 -> chickens.add(Pos(x, y))
        }
    }
    var min = 9999
    fun dfs(index: Int, count: Int, dists: IntArray) {
        if (count == m) {
            min = minOf(min, dists.sum())
            return
        }
        for (i in index until chickens.size) {
            val nDists = IntArray(homes.size) { minOf(dists[it], homes[it].dist(chickens[i])) }
            dfs(i + 1, count + 1, nDists)
        }
    }
    dfs(0, 0, IntArray(homes.size) { 100 })
    print(min)
}

/*
3 1
1 0 0
0 2 0
0 0 2
*/