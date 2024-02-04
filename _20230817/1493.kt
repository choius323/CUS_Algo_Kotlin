package _20230817

/*

1493번 - 박스 채우기
https://www.acmicpc.net/problem/1493
분류 : 수학, 그리디 알고리즘, 분할 정복

재귀 함수를 만들어 분할 정복을 하며 채워야 하는 상자의 개수를 구했다.
L, W, H 크기의 박스에 한 변의 길이가 a인 정육면체를 채우면 상자의 남은 영역은 3개로 나눌 수 있다.
(L-a, W, H), (a, W-a, H), (a, a, H-a)
새로 구한 3개의 영역에 대해 다시 분할 정복을 하며 나머지 상자의 개수를 구한다.

참고 링크:
https://yeeybook.tistory.com/95

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toInt() }
    val length = r()
    val width = r()
    val height = r()
    val countBoxes = IntArray(21)
    var canFill = true
    repeat(r()) { countBoxes[r()] = r() }
    var count = 0

    fun recur(l: Int, w: Int, h: Int) {
        if (canFill.not()) return
        else if (l == 0 || w == 0 || h == 0) return
//        val (l1, l2, l3) = intArrayOf(l, w, h).sorted()
        val minLen = minOf(l, w, h)
        for (i in 20 downTo 0) {
            if (countBoxes[i] == 0) continue
            if (minLen >= (1 shl i)) {
                countBoxes[i]--
                count++
                val sliceLen = 1 shl i
                recur(l - sliceLen, w, h)
                recur(sliceLen, w - sliceLen, h)
                recur(sliceLen, sliceLen, h - sliceLen)
                return
            }
        }
        canFill = false
    }

    recur(length, width, height)
    print(if (canFill) count else -1)
}

/*
100000 100000 100000
1
3 100000
*/