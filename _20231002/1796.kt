package _20231002

/*

1796번 - 신기한 키보드
https://www.acmicpc.net/problem/1796
분류 : 다이나믹 프로그래밍

각 알파벳을 출력할 때 커서를 한쪽 방향으로 이동하며 모두 출력하고, 반대 방향으로 이동하며 모두 출력하면 최소 입력이 된다.
어떤 방향이 최소 입력인지 알 수 없으므로 각 방향을 모두 확인해서 최솟값을 찾는다.
문자열이 50이하이므로 오래 걸리지 않았다.

*/

import kotlin.math.abs

fun main() {
    val indices = Array('z' - 'a' + 1) { mutableListOf<Int>() } // 알파벳 마다 문자열의 인덱스 목록
    readln().forEachIndexed { index, c ->
        indices[c - 'a'].add(index)
    }

    var answer = Int.MAX_VALUE
    fun recur(cursor: Int, index: Int, count: Int) {
        if (index == indices.size) {
            answer = minOf(answer, count)
            return
        }
        val list = indices[index]
        if (list.isEmpty()) {
            recur(cursor, index + 1, count)
        } else {
            val left = list.first()
            val right = list.last()
            // (이전 count) + (왼쪽부터 오른쪽까지 이동하는데 누른 횟수) + (해당 알파벳 모두 출력)
            val nCount = count + (right - left) + list.size
            recur(left, index + 1, nCount + abs(right - cursor)) // 가장 왼쪽 알파벳을 마지막에 출력
            recur(right, index + 1, nCount + abs(cursor - left)) // 가장 오른쪽 알파벳을 마지막에 출력
        }
    }
    recur(0, 0, 0)
    print(answer)
}

/*

1 3 5 7 9
0 1 2 3 4
*/