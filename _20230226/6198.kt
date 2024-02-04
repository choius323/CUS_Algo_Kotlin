/*

6198번 - 옥상 정원 꾸미기
https://www.acmicpc.net/problem/6198

한 건물은 자신 이상 높이의 건물이 나오기 전까지, 모든 건물을 모두 볼 수 있다.
따라서 스택에 건물의 높이를 저장하며 같거나 높은 건물이 나오기 전 까지 스택에서 제거하지 않는다.
이렇게 되면 스택은 내림차순으로 저장하게 되고, 건물을 입력받았을 때 스택에서 제거되지 않은 건물의 수가 현재 건물을 볼 수 있는 수이다.

*/

package _20230226

fun main() = System.`in`.bufferedReader().run {
    val stack = ArrayDeque<Int>()
    var answer = 0L

    repeat(readLine().toInt()) {
        val h = readLine().toInt()
        while (stack.isNotEmpty() && stack.last() <= h) {
            stack.removeLast()
        }
        answer += stack.size
        stack.add(h)
    }
    print(answer)
}

/*

*/