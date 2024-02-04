/*

2631번 - 줄세우기
https://www.acmicpc.net/problem/2631



*/

package _20230305

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Child(val num: Int, val max: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val children = mutableListOf<Child>()
    repeat(n) {
        val num = r()
        var max = 1
        for (child in children) {
            if (child.num < num && child.max >= max) {
                max = child.max + 1
            }
        }
        children.add(Child(num, max))
    }
    print(children.minOf { n - it.max })
}

/*

*/