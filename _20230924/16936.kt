package _20230924

/*

16936번 - 나3곱2
https://www.acmicpc.net/problem/16936
분류 : 수학, 정렬, 해 구성하기



*/

fun main() {
    val n = readln().toInt()
    val nums = readln().split(" ").map(String::toLong).toLongArray()
    val answer = LongArray(n)
    var isFilled = false

    fun perm(seq: Int, num: Long) {
        if(seq == n) {
            isFilled = true
            return
        }
        for(i in 0 until n) {
            if(isFilled) return
            val next = nums[i]
            if(num == -1L || num * 2 == next || (num % 3 == 0L && num / 3 == next)) {
                answer[seq] = next
                perm(seq + 1, next)
            }
        }
    }

    perm(0, -1)
    print(answer.joinToString(" "))
}


/*

*/