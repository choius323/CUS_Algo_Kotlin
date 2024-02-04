package _20230629

/*

9935번 - 폭발 문자열
https://www.acmicpc.net/problem/9935
분류 : 



*/

fun main() {
    val str = readln()
    val bomb = readln().reversed()
    val stack = ArrayDeque<Char>()
    fun isBomb(): Boolean {
        if (bomb.length > stack.size) return false
        for (i in bomb.indices) {
            if (stack[stack.lastIndex - i] != bomb[i]) return false
        }
        return true
    }
    for (c in str) {
        stack.add(c)
        for (i in bomb.indices) {
            while (isBomb()) {
                repeat(bomb.length) { stack.removeLast() }
            }
        }
    }
    print(stack.joinToString("").ifEmpty { "FRULA" })
}

fun main3() {
    val sb = StringBuilder()
    val str = readln()
    val bomb = readln()
    out@ for (c in str) {
        sb.append(c)
        for (i in bomb.indices) {
            if (sb.getOrNull(sb.lastIndex - i) != bomb[bomb.lastIndex - i]) {
                continue@out
            }
        }
        sb.delete(sb.length - bomb.length, sb.length)
    }
    print(sb.ifEmpty { "FRULA" })
}

//메모리 초과 발생
//문자열이 바뀌지 않을때까지 폭발 문자열을 제거
//fun main() {
//    val start = readln()
//    var str = start
//    val bomb = readln()
//    val sb = StringBuilder()
//    var isChanged = false
//    var isFirst = true
//    while (isFirst || isChanged) {
//        isChanged = false
//        isFirst = false
//        sb.clear()
//        var i = 0
//        while (i < str.length) {
//            if (str[i] != bomb.first()) {
//                sb.append(str[i])
//                i++
//            } else {
//                var j = 0
//                while (bomb.getOrElse(j) { ' ' } == str.getOrNull(i + j)) {
//                    j++
//                }
//                if (j != bomb.length) {
//                    for (addIdx in i until i + j) {
//                        sb.append(str[addIdx])
//                    }
//                } else {
//                    isChanged = true
//                }
//                i += j
//            }
//        }
//        str = sb.toString()
//        if (str.isEmpty()) return print("FRULA")
//    }
//    print(str)
//}

/*

*/