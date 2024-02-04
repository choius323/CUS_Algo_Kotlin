package _20230817

/*

1038번 - 감소하는 수
https://www.acmicpc.net/problem/1038
분류 : 브루트포스 알고리즘, 백트래킹

감소하는 수의 최대 값은 9876543210이다.
각 자리 수에 들어가는 최대 값을 저장할 배열과, 현재 값을 저장하는 배열을 만든다.
재귀 함수로 감소하는 수를 찾으며 가장 큰 수부터 answers에 저장한다.
값을 찾으려는 인덱스 앞의 수가 모두 0이면 9, 아니면 직전 인덱스의 -1 값을 시작으로 저장하고 최대값까지 반복문을 수행한다.
또, 마지막 인덱스를 제외하면 해당 인덱스의 값이 0일 때 더 작은 감소하는 수를 얻을 수 있다.

*/

fun main() {
    val max = (9L downTo 0L).toList()
    val answers = ArrayList<Long>()
    val ints = LongArray(10)
    fun recur(idx: Int) {
        if (idx == 10) {
            answers.add(ints.fold(0L) { acc, i -> acc * 10 + i })
            return
        }
        val startNum = if (ints.take(idx).all { it == 0L }) {
            9L
        } else {
            if (ints[idx - 1] <= max[idx]) return
            ints[idx - 1] - 1
        }
        for (num in startNum downTo max[idx]) {
            ints[idx] = num
            recur(idx + 1)
        }
        if (idx != 9) {
            ints[idx] = 0
            recur(idx + 1)
        }
    }

    recur(0)
    val n = readln().toInt()
    println(if(n < answers.size) answers[answers.lastIndex - n] else -1)
}

/*

*/