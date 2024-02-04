package _20230706

/*

12869번 - 뮤탈리스크
https://www.acmicpc.net/problem/12869
분류 : 



*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    operator fun Array<Array<IntArray>>.get(array: IntArray) = this[array[0]][array[1]][array[2]]
    operator fun Array<Array<IntArray>>.set(array: IntArray, value: Int) {
        this[array[0]][array[1]][array[2]] = value
    }

    val r = { nextToken();nval.toInt() }
    val permutations = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(0, 2, 1), intArrayOf(1, 0, 2),
        intArrayOf(1, 2, 0), intArrayOf(2, 0, 1), intArrayOf(2, 1, 0)
    )
    val dp = Array(61) { Array(61) { IntArray(61) } }

    fun dfs(hpArray: IntArray): Int {
        if (hpArray.all { it <= 0 }) {
            dp[hpArray] = 0
            return 0
        }
        var count = 99
        out@ for (perm in permutations) {
            val copied = hpArray.copyOf()
            for (i in 0..2) {
                if (i == 0 && copied[perm[i]] <= 0) continue@out
                copied[perm[i]] = maxOf(0, copied[perm[i]] - if (i == 0) 9 else if (i == 1) 3 else 1)
            }

            count = minOf(
                count, if (dp[hpArray] != 0) {
                    dp[hpArray]
                } else {
                    val newCount = dfs(copied) + 1
                    dp[copied] = newCount
                    newCount
                }
            )
        }
        return count
    }

    val hpArray = IntArray(3)
    repeat(r()) { hpArray[it] = r() }
    print(dfs(hpArray))
}

/*

*/