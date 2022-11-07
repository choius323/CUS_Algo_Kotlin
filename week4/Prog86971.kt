/*

전력망을 둘로 나누기
https://school.programmers.co.kr/learn/courses/30/lessons/86971

 */

package week4

private class Prog86971 {
    class Solution {
        fun solution(n: Int, inputWires: Array<IntArray>): Int {
            val wires = List(n) { mutableListOf<Int>() }
            inputWires.forEach { wire ->
                wires[wire[0] - 1].add(wire[1] - 1)
                wires[wire[1] - 1].add(wire[0] - 1)
            }

            var answer = n

            fun dfs(i: Int, visited: BooleanArray): Int {
                visited[i] = true
                var count = 1
                for (next in wires[i]) {
                    if (visited[next].not()) {
                        count += dfs(next, visited)
                    }
                }
                return count
            }

            for (i in 0 until n) {
                for (j in wires[i].indices) {
                    val temp = wires[i][j]
                    wires[i][j] = i
                    val count = dfs(i, BooleanArray(n))
                    answer = minOf(answer, kotlin.math.abs(n - count * 2))
                    println("$answer $i $j $wires")
                    wires[i][j] = temp
                }
            }
            return answer
        }
    }

    fun solution(n: Int, str: String): Int = Solution().solution(n, stringToArray(str))

    private fun stringToArray(str: String): Array<IntArray> {
        return str.trim()
            .drop(2).dropLast(2)
            .split("], [")
            .filter { it.isNotBlank() }
            .map { it.split(", ").map { it2 -> it2.toInt() }.toIntArray() }
            .toTypedArray()
    }
}

fun main() {
//    println(Programmers86971().solution(9, "[[1, 3], [2, 3], [3, 4], [4, 5], [4, 6], [4, 7], [7, 8], [7, 9]]"))
    println(Prog86971().solution(2, "[[1, 2]]"))
}

