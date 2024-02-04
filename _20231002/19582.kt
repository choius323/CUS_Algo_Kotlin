package _20231002

/*

19582번 - 200년간 폐관수련했더니 PS 최강자가 된 건에 대하여
https://www.acmicpc.net/problem/19582
분류 : 다이나믹 프로그래밍, 그리디 알고리즘

그리디하게 대회에 참여하며 참가하지 못하는 대회가 나올 때 대회 1개를 제외했다.

1. 상금 상한에 걸리지 않을 경우 대회에 참여하고 상금을 수상한다.
2. 상금 상한에 걸리고 이미 참여하지 않은 대회가 있는 경우 `Zzz`를 출력한다.
3. 참여하지 않아도 된다면, 대회 1개를 제외시킨다.
3.1 현재 참가하려는 대회의 상금이 이전 대회보다 작고 이전 대회 중 1개를 제외했을 때 현재 대회에 참가할 수 있다면, 이전 대회 1개를 제외한다.
3.2 그렇지 않다면 현재 대회에 참가하지 않는다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    val r = { nextToken();nval.toLong() }
    var isAllTake = true // 확인한 대회를 모두 참가했는지 저장
    var maxPrize = 0L // 참여한 대회의 최대 상금
    var sumPrize = 0L // 획득한 총 상금
    repeat(r().toInt()) { _ ->
        val x = r()
        val p = r()
        if (sumPrize <= x) {
            maxPrize = maxOf(p, maxPrize)
            sumPrize += p
        } else if (isAllTake.not()) {
            return@run print("Zzz")
        } else {
            if (maxPrize >= p && sumPrize - maxPrize <= x) {
                sumPrize += p - maxPrize
            }
            isAllTake = false
        }
    }
    print("Kkeo-eok")
}

/*

*/