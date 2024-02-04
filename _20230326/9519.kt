/*

9519번 - 졸려
https://www.acmicpc.net/problem/9519
분류 : 



*/

package _20230326

fun main() {
    val x = readln().toInt()
    val word = readln()
    val map = mutableMapOf<String, Int>()
    var str = word
    var count = 0
    while (map.contains(word).not()) {
        var left = 0
        var right = str.lastIndex
        val sb = StringBuilder()
        while (left <= right) {
            sb.append(str[left])
            if (left != right) sb.append(str[right])
            left++
            right--
        }
        count++
        str = sb.toString()
        map += str to count
    }
    print(map.toList()[count - x % count - 1].first)
}

private fun test() {
    for (i in 2..3000) {
        test(10000, (0..i).map { 'a' + it }.joinToString(""))
    }
}

private fun test(x: Int, start: String) {
    var str = start
    var count = 1

    while ((count != 0 && str != start) || count < x) {
        var left = 0
        var right = str.lastIndex
        val sb = StringBuilder()
        while (left <= right) {
            sb.append(str[left])
            if (left != right) sb.append(str[right])
            left++
            right--
        }
        str = sb.toString()
//        println("$count $sb ${if (sb.contentEquals(start)) "!!!" else ""}")
        if (str == start) {
            println("len: ${start.length}  count: $count")
            return
        }
        count++
    }
}/*

*/