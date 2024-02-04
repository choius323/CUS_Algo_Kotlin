/*

15319번 - 동혁이의 생일선물
https://www.acmicpc.net/problem/15319
분류 : 

집합의 합을 오름차순 하면 x^0, x^1, x^0+x^1, x^2, x^0+x^2, x^1+x^2, x^0+x^1+x^2, x^3 순으로 이어진다.
8번째 -> x^3
1000

*/

package _20230608

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val remNum = 1_000_000_007
    val r = { nextToken();nval.toInt() }
    val powArray = mutableListOf<Long>()
    fun recursion(k: Int): Long {
        val highBit = k.takeHighestOneBit()
        val diffK = k - highBit
        return (powArray[highBit.countTrailingZeroBits()] + if (diffK <= 0) 0L else recursion(diffK)) % remNum
    }

    var answer = 0L
    repeat(r()) {
        val x = r()
        val k = r()
        var pow = 1L
        var indexPow = 1

        powArray.clear()
        while (indexPow in 1..k) {
            powArray.add(pow)
            indexPow = indexPow shl 1
            pow = (pow * x) % remNum
        }
        answer = (answer + recursion(k)) % remNum
    }
    print(answer)
}

/*

*/