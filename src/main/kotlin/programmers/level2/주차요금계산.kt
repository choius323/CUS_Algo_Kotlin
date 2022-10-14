package programmers.level2

/*

주차 요금 계산
https://school.programmers.co.kr/learn/courses/30/lessons/92341

*/

class Programmers92341 {
    class Solution {
        fun solution(fees: IntArray, records: Array<String>): List<Int> {
            val parkList = Array<State?>(10000) { null }
            records.forEach {
                val (h, m, _num, inout) = it.split(":", " ")
                val num = _num.toInt()
                val now = h.toInt() * 60 + m.toInt()
                val prevState = parkList[num]
                if (prevState != null) {
                    if (inout == "IN") {
                        prevState.isIn = true
                    } else {
                        prevState.total += now - prevState.time
                        prevState.isIn = false
                    }
                    prevState.time = now
                } else {
                    parkList[num] = State(0, true, now)
                }
            }
            val lastTime = 23 * 60 + 59
            parkList.forEach { state ->
                if (state != null && state.isIn) {
                    state.total += lastTime - state.time
                    state.isIn = false
                }
            }
            return parkList.filterNotNull().map { calcFee(it.total, fees) }
        }

        fun calcFee(total: Int, fees: IntArray): Int {
            val overTime = if (total > fees[0]) total.toFloat() - fees[0] else 0f
            return fees[1] + kotlin.math.ceil(overTime / fees[2]).toInt() * fees[3]
        }

        data class State(var total: Int, var isIn: Boolean, var time: Int)
    }
}