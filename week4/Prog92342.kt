/*

양궁대회
https://school.programmers.co.kr/learn/courses/30/lessons/92342



*/

package week4

import java.util.*

private class Prog92342 {
    class Solution {
        private val stack = Stack<Boolean>()

        private var maxScore = 0

        private var numbersList = mutableListOf<IntArray>()

        fun solution(n: Int, info: IntArray): IntArray {
            dfs(n, info)
            return numbersList.maxByOrNull { it.reversed().joinToString("").toLong() } ?: intArrayOf(-1)
        }

        private fun dfs(n: Int, numbers: IntArray) {
            for (index in 0..1) {
                stack.push(index == 0)
                if (stack.size != numbers.size) {
                    dfs(n, numbers)
                } else {
                    addOnlyMax(n, numbers)
                }
                stack.pop()
            }
        }

        private fun getNumbers(n: Int, numbers: IntArray): IntArray {
            var mutableN = n
            val result = mutableListOf<Int>()

            for (i in numbers.indices) {
                if (stack[i] && mutableN > 0 && mutableN > numbers[i]) {
                    mutableN -= (numbers[i] + 1)
                    result.add(numbers[i] + 1)
                } else {
                    result.add(0)
                }
            }
            if (mutableN > 0) {
                result[result.lastIndex] += mutableN
            }
            return result.toIntArray()
        }

        private fun addOnlyMax(n: Int, info: IntArray) {
            val result = getNumbers(n, info)
            val scoreDifference = getScoreDifference(info, result)

            if (scoreDifference > 0) {
                if (scoreDifference > maxScore) {
                    numbersList = mutableListOf(result)
                    maxScore = scoreDifference
                } else if (scoreDifference == maxScore) {
                    numbersList.add(result)
                }
            }
        }

        private fun getScoreDifference(apeachInfo: IntArray, ryanInfo: IntArray): Int {
            var apeachScore = 0
            var ryanScore = 0

            for (i in apeachInfo.indices) {
                if (apeachInfo[i] > ryanInfo[i]) {
                    apeachScore += 10 - i
                } else if (ryanInfo[i] > 0) {
                    ryanScore += 10 - i
                }
            }
            return ryanScore - apeachScore
        }
    }

    fun solution(n: Int, str: String): IntArray = Solution().solution(n, stringToArray(str))

    private fun stringToArray(str: String): IntArray {
        return str.trim()
            .split(", ")
            .map { it.toInt() }
            .toIntArray()
    }
}

fun main() {
//    println(Prog92342().solution(5, "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0").contentToString())
    println(B().invoke(5))
}

abstract class A:(Int)->Int{

}

class B: A() {
    override fun invoke(p1: Int): Int {
        return p1+2
    }

}

/*

*/