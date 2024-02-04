package _20240204

/*

12919번 - A와 B 2
https://www.acmicpc.net/problem/12919
분류 : 문자열, 브루트포스 알고리즘, 재귀

Bottom-Up으로 하면 경우의 수가 너무 많아져서 시간 초과가 발생한다.
Top-Down 방식으로 접근하며 불가능한 경우를 제거하며 진행하면 해결 가능하다.
처음에 Bottom-Up으로 진행하며 문자열 추가, 뒤집기에서 시간을 최대한 줄이기 위해 비트를 사용했다.

*/

fun main() {
    fun String.toBit(): Long = fold(0L) { acc, c ->
        (if (c == 'A') acc + 1 else acc) shl 1
    }.let { it shr 1 }

    fun Long.reversed(len: Int): Long {
        var origin = this
        var ret = 0L
        repeat(len) {
            ret = ret shl 1
            if (origin % 2 == 1L) {
                ret++
            }
            origin = origin shr 1
        }
        return ret
    }

    val start = readln()
    val startNum = start.toBit()
    val visited = mutableSetOf<Long>()
    val target = readln()
    var isFind = false

    fun recur(len: Int, num: Long) {
        if (isFind) return
        if (len + (num shl 6) in visited) return
        visited += len + (num shl 6)
        if (len == start.length) {
            if (num == startNum) {
                isFind = true
            }
            return
        }
        if (num % 2 == 1L) {
            recur(len - 1, num shr 1)
        }
        if (num.inv() and (1L shl (len - 1)) > 0) {
            recur(len - 1, num.reversed(len - 1))
        }
    }

    recur(target.length, target.toBit())
    print(if (isFind) 1 else 0)
}

/*
A

AA
AB -> -BA

AAA
AAB -> -BAA
-BAA   X
-BAB -> BAB

AAAA
AAAB -> -BAAA
-BAAA   X
-BAAB -> BAAB
BABA
BABB -> -BBAB
*/

/*
fun recur(len: Int, num: Long) {
    if (isFind) return
    if ((isForward && num in visited) || (isForward.not() && num in reVisited)) return
    if (len == target.length) {
        if ((isForward && num == targetNum) || (isForward.not() && num == reTargetNum)) {
            isFind = true
        }
        return
    }
    println(len)
    if (isForward) {
        visited += num
        recur(len + 1, (num shl 1) + 1, isForward)
        recur(len + 1, num shl 1, isForward.not())
    } else {
        reVisited += num
        recur(len + 1, (1 shl len) + num, isForward)
        recur(len + 1, num, isForward.not())
    }
}
*/