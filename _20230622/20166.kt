package _20230622

/*

20166번 - 문자열 지옥에 빠진 호석
https://www.acmicpc.net/problem/20166
분류 : 



*/

fun main2() = System.`in`.bufferedReader().run {
    val dx = intArrayOf(-1, 1, 0, 0, 1, 1, -1, -1)
    val dy = intArrayOf(0, 0, -1, 1, 1, -1, 1, -1)
    val (n, m, k) = readLine().split(" ").map(String::toInt)
    val ground = Array(n) { readLine().trim() }
    val wordMap = mutableMapOf<String, MutableList<Int>>()
    val counts = IntArray(k)
    repeat(k) {
        val word = readLine()
        val list = wordMap.getOrElse(word) { mutableListOf() }
        list.add(it)
        wordMap += word to list
    }

    fun posToRange(pos: Int, max: Int) = if (pos < 0) max - 1 else if (pos == max) 0 else pos

    fun dfs(x: Int, y: Int, str: String) {
        if (str.length > 5) return
        if (str in wordMap) {
            for (index in wordMap[str]!!) {
                counts[index]++
            }
        }
        for (d in 0..7) {
            val nx = posToRange(x + dx[d], m)
            val ny = posToRange(y + dy[d], n)
            dfs(nx, ny, str + ground[ny][nx])
        }
    }

    for (y in 0 until n) for (x in 0 until m) {
        dfs(x, y, "${ground[y][x]}")
    }
    print(counts.joinToString("\n"))
}

fun main() = System.`in`.bufferedReader().run {
    data class Node(
        val value: Char?,
        val children: MutableList<Node> = mutableListOf(),
        val wordIndices: MutableList<Int> = mutableListOf(),
    ) {
        fun find(char: Char): Node? {
            return children.firstOrNull { it.value == char }
        }

        fun add(word: String, index: Int, wordIndex: Int) {
            if (index == word.length) {
                wordIndices.add(wordIndex)
            } else {
                val nextNode = children.firstOrNull { it.value == word[index] }
                    ?: Node(word[index]).also { children.add(it) }
                nextNode.add(word, index + 1, wordIndex)
            }
        }
    }

    val dx = intArrayOf(-1, 1, 0, 0, 1, 1, -1, -1)
    val dy = intArrayOf(0, 0, -1, 1, 1, -1, 1, -1)
    val (n, m, k) = readLine().split(" ").map(String::toInt)
    val ground = Array(n) { readLine() }
    val trie = Node(null)
    val counts = IntArray(k)

    repeat(k) { wordIndex ->
        val word = readLine()
        trie.add(word, 0, wordIndex)
    }

    fun posToRange(pos: Int, max: Int) = if (pos < 0) max - 1 else if (pos == max) 0 else pos

    fun dfs(x: Int, y: Int, node: Node?, strLength: Int) {
        if (node == null) return
        for (index in node.wordIndices) {
            counts[index]++
        }
        if (strLength > 5) return
        for (d in 0..7) {
            val nx = posToRange(x + dx[d], m)
            val ny = posToRange(y + dy[d], n)
            dfs(nx, ny, node.find(ground[ny][nx]), strLength + 1)
        }
    }

    for (y in 0 until n) for (x in 0 until m) {
        dfs(x, y, trie.find(ground[y][x]), 1)
    }
    print(counts.joinToString("\n"))
}

/*
3 3 5
aaa
aba
aaa
aaa
aa
aba
a
az

3 3 4
aaa
aba
aaa
aaa
aaa
aa
bb

7*7*7

00
10
20
01
21
02
12
22

*/