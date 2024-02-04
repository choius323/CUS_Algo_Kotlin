package _20230629

/*

23740번 - 버스 노선 개편하기
https://www.acmicpc.net/problem/23740
분류 : 정렬, 스위핑



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Route(val start: Int, var end: Int, var cost: Int) {
        override fun toString() = "$start $end $cost"
    }

    val r = { nextToken();nval.toInt() }
    val n = r()
    val routes = Array(n) { Route(r(), r(), r()) }.sortedArrayWith(compareBy({ it.start }, { -it.end }))
    val newRoutes = mutableListOf<Route>()

    for (route in routes) {
        val last = newRoutes.lastOrNull()
        if (last == null || last.end < route.start) {
            newRoutes.add(route)
        } else {
            last.end = maxOf(last.end, route.end)
            last.cost = minOf(last.cost, route.cost)
        }
    }
    print("${newRoutes.size}\n${newRoutes.joinToString("\n")}")
}

/*

*/