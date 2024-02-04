/*

17298번 - 오큰수
https://www.acmicpc.net/problem/17298
분류 : 



*/

package _20230615

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Element(val index: Int, val num: Int)

    val r = { nextToken();nval.toInt() }
    val nums = IntArray(r()) { r() }
    val stack = java.util.Stack<Element>()
    for ((i, num) in nums.withIndex()) {
        while (stack.isNotEmpty() && stack.last().num < num) {
            nums[stack.pop().index] = num
        }
        stack.add(Element(i, num))
    }
    stack.forEach { nums[it.index] = -1 }
    print(nums.joinToString(" "))
}

/*

*/