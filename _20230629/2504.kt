package _20230629

/*

2504번 - 괄호의 값
https://www.acmicpc.net/problem/2504
분류 :

*/

fun main() {
    val stack = ArrayDeque<Any>()
    for (c in readln()) {
        if (c == '(' || c == '[') stack.add(c)
        else if (c == ')' || c == ']') {
            var num = if (c == ')') 2 else 3
            while (stack.lastOrNull() is Int) {
                num *= stack.removeLast() as Int
            }
            if ((stack.isEmpty())
                || ((c == ')' && stack.last() == '(') || (c == ']' && stack.last() == '[')).not()
            ) return print(0)
            stack.removeLast()
            while (stack.lastOrNull() is Int) {
                num += stack.removeLast() as Int
            }
            stack.add(num)
        }
    }
    print(if (stack.size == 1 && stack.first() is Int) stack.first() else 0)
}

/*

*/