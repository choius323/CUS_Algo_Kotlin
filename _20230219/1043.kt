/*

1043번 - 거짓말
https://www.acmicpc.net/problem/1043

분리 집합을 사용해서 풀었지만, n과 m이 작기 때문에 DFS로 풀어도 된다.
분리 집합은 교집합이 없는 집합들(분리된 집합)이다.
이 문제에선 진실을 들은 사람과 거짓을 들을 사람의 교집합이 있으면 안 되기 때문에 분리 집합을 사용할 수 있다.
find에서 새로운 루트를 set[num]에 넣어주지 않거나 union에서 깊이를 비교하지 않으면 시간 복잡도가 늘어난다.

*/

package _20230219




fun main2() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = i() // 인원 수
    val m = i() // 파티 수
    val set = IntArray(n + 1) { it }  // 진실을 아는 사람 수. Union Find, Disjoint Set, 분리집합.
    val deep = IntArray(n + 1) // 집합의 깊이

    fun find(num: Int): Int { // 루트 찾기
        if (set[num] == num)
            return num
        set[num] = find(set[num]) // 새로운 루트 저장
        return set[num]
    }

    fun union(a: Int, b: Int) { // 합집합 저장
        var aRoot = find(a)
        var bRoot = find(b)

        if (deep[aRoot] < deep[bRoot])
            aRoot = bRoot.also { bRoot = aRoot }

        set[bRoot] = aRoot

        if (deep[aRoot] == deep[bRoot]) deep[aRoot] += 1
    }

    var known = 0 // 진실을 아는 사람
    repeat(i()) {
        val num = i()
        if (known != 0)
            union(known, num)
        else
            known = num
    }

    var answer = 0
    val partyArr = Array(m) { IntArray(i()) { i() } }
    forLoop@ for (party in partyArr) {
        for (p in party) {
            if (set[p] != p) { // 자신이 루트가 아닌 경우 -> 다른 루트에 속해있는 경우. 자식 노드가 있는 경우는 안 나옴
                party.forEach { union(it, p) }
                continue@forLoop
            }
        }
        party.forEach {
            union(party[0], it)
        }
    }

    repeat(n) { find(it + 1) }
    forLoop@ for (party in partyArr) {
        if (party.isNotEmpty()) {
            for (p in party) {
                if (find(p) == find(known)) { // 진실을 아는 사람과 같은 집합
                    continue@forLoop
                }
            }
            answer += 1
        }
    }
    print(answer)
}

/*
5 3
1 3
3 1 2 4
3 1 4 5
1 3
*/