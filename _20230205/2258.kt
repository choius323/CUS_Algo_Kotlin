package _20230205

/*

2258번 - 정육점
https://www.acmicpc.net/problem/2258

가격을 기준으로 오름차순 정렬을 하고, 가격이 같으면 무게를 기준으로 내림차순 정렬한다.
같은 가격의 고기를 여러개 살 수 있다는 점을 주의해야 한다.

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Meat(val weight: Int, val price: Int)

    val r = { nextToken();nval.toInt() }
    val n = r()
    val m = r()
    val meats = Array(n) { Meat(r(), r()) }
    meats.sortWith(compareBy({ it.price }, { -it.weight }))

    var totalWeight = 0
    var totalPrice = 0
    var lastPrice = -1
    var answer: Int? = null

    for ((weight, price) in meats) {
        totalWeight += weight
        if (lastPrice != price) {
            lastPrice = price
            totalPrice = price
        } else {
            totalPrice += price
        }
        if (totalWeight >= m && (answer == null || totalPrice < answer)) {
            answer = totalPrice
        }
    }
    print(answer ?: -1)
}

/*

*/