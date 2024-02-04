/*

5052번 - 전화번호 목록
https://www.acmicpc.net/problem/5052
분류 : 



*/

package _20230326

/*fun main() = System.`in`.bufferedReader().run {
    repeat(readLine().toInt()) {
        val trie = TrieNode()
        for (numStr in Array<String>(readLine().toInt()) { readLine() }.sortedArrayDescending()) {
            if (trie.contains(numStr)) {
                println("NO")
                return@repeat
            }
            trie.add(numStr)
        }
        println("YES")
    }
}

private data class TrieNode(val children: MutableMap<Char, TrieNode> = mutableMapOf()) {
    fun add(word: String, index: Int = 0) {
        if (index == word.length) return
        val char = word[index]
        children.getOrPut(char) { TrieNode() }.add(word, index + 1)
    }

    fun contains(word: String, index: Int = 0): Boolean {
        if (index == word.length) return true
        val char = word[index]
        return children[char]?.contains(word, index + 1) ?: false
    }
}*/

fun main() = System.`in`.bufferedReader().run {
    repeat(readLine().toInt()) {
        val numbers = Array(readLine().toInt()) { readLine() }.sortedArray()
        for (i in 0..numbers.size - 2) {
            if (numbers[i].length <= numbers[i + 1].length && numbers[i + 1].startsWith(numbers[i])) {
                return@repeat println("NO")
            }
        }
        println("YES")
    }
}

/*

*/