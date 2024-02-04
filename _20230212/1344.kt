/*

1344번 - 축구
https://www.acmicpc.net/problem/1344

dp를 사용하면 시간 내에 해결이 가능하다.
dp 배열을 모두 채우는데 (간격 수)*(A팀 최대 골)*(B팀 최대 골)*(각 간격에서 생기는 경우의 수) = 18^3 * 4 이다.
3차원 배열을 사용해서 간격, a팀 골, b팀 골을 각 인덱스로 사용했고, 각 경우의 수에서 다음 경우의 수에 갈 때 확률을 다음 배열에 더해줬다.

dfs로 모든 경우를 매번 계산하면 시간 초과가 발생한다.

*/

package _20230212

fun main() {
    val (ap, bp) = List(2) { readln().toDouble() / 100 }
    val primes = BooleanArray(19)
    intArrayOf(2, 3, 5, 7, 11, 13, 17).forEach { primes[it] = true }
    val dp = Array(19) { Array(19) { DoubleArray(19) } }
    dp[0][0][0] = 1.0

    for (case in 0..17) for (a in 0..17) for (b in 0..17) {
        dp[case + 1][a][b] += dp[case][a][b] * (1 - ap) * (1 - bp)
        dp[case + 1][a + 1][b] += dp[case][a][b] * ap * (1 - bp)
        dp[case + 1][a][b + 1] += dp[case][a][b] * (1 - ap) * bp
        dp[case + 1][a + 1][b + 1] += dp[case][a][b] * ap * bp
    }

    var answer = .0
    for (a in 0..18) for (b in 0..18) {
        if (primes[a] || primes[b]) answer += dp[18][a][b]
    }
    println(answer)
}

/*

//    fun game(count: Int, goalA: Int, goalB: Int, percent: Double) {
//        if (count == 18) {
//            if (primes[goalA] || primes[goalB]) {
//                answer += percent
//            }
//            return
//        }
//        game(count + 1, goalA, goalB, percent * (1 - ap) * (1 - bp))
//        game(count + 1, goalA + 1, goalB, percent * ap * (1 - bp))
//        game(count + 1, goalA, goalB + 1, percent * (1 - ap) * bp)
//        game(count + 1, goalA + 1, goalB + 1, percent * ap * bp)
//    }
//    game(0, 0, 0, 1.0)

/*

*/