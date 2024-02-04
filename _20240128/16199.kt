package _20240128

/*

16199번 - 나이 계산하기
https://www.acmicpc.net/problem/16199
분류 : 수학, 구현, 사칙연산

만 나이는 이미 생일이 지나면 연 나이와 같고, 지나지 않았다면 1을 빼야 한다.

*/

fun main() {
    fun readInts() = readln().split(" ").map { it.toInt() }
    val birth = readInts()
    val now = readInts()
    val yearAge = now[0] - birth[0]

    println(yearAge - if (now[1] > birth[1] || (now[1] == birth[1] && now[2] >= birth[2])) 0 else 1)

    println(yearAge + 1)

    println(yearAge)
}

/*

*/