package _20230706

/*

19538번 - 루머
https://www.acmicpc.net/problem/19538
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val n = r()
    val people = Array(n + 1) { mutableListOf<Int>() }
    for (i in 1..n) {
        val person = people[i]
        while (true) {
            val num = r()
            if (num == 0) break
            person += num
        }
    }
    val visited = BooleanArray(n + 1)
    val answer = IntArray(n + 1) { -1 }
    val queue = ArrayDeque<Int>()
    repeat(r()) {
        val num = r()
        visited[num] = true
        answer[num] = 0
        queue.add(num)
    }
    while (queue.isNotEmpty()) {
        val now = queue.removeFirst()
        for (next in people[now]) {
            if (visited[next]) continue
            var count = 0
            for (i in people[next]) {
                if (visited[i] && answer[i] != answer[now] + 1) count++
            }
            if ((people[next].size + 1) / 2 > count) continue
            answer[next] = answer[now] + 1
            visited[next] = true
            queue.add(next)
        }
    }

    val sb = StringBuilder()
    for (i in 1..n) {
        sb.append(answer[i]).append(' ')
    }
    print(sb)
}

/*

*/