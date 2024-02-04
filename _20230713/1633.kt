package _20230713

/*

1633번 - 최고의 팀 만들기
https://www.acmicpc.net/problem/1633
분류 : 



*/

fun main() = System.`in`.bufferedReader().run {
    val dp = Array(16) { IntArray(16) }
    while (ready()) {
        val temp = Array(16) { IntArray(16) }
        val (white, black) = readLine().trim().split(" ").map(String::toInt)
        for (w in 15 downTo 0) for(b in 15 downTo 0) {
            temp[w][b] = maxOf(dp[w][b], if(w == 0) 0 else (dp[w - 1][b] + white), if(b == 0) 0 else (dp[w][b - 1] + black))
        }
        temp.copyInto(dp)
    }
    print(dp[15][15])
}

/*

*/