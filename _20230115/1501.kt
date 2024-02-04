package _20230115

/*

1501번 - 영어 읽기
https://www.acmicpc.net/problem/1501

사전은 A~Z+a~z를 각 인덱스로 갖는 2차원 배열로 만들고 dict[시작 글자][마지막 글자]로 지정한다.
마지막 배열에는 각 사전의 단어를 Word 클래스로 저장하며, 각 알파벳이 몇번 나왔는지 저장하는 배열과 문자열의 길이를 원소로 갖는다.

문장을 입력받으면 각 단어별로 사전에 알맞은 단어의 개수를 찾고, 그 개수를 모두 곱해서 출력한다.
사전의 단어와 비교할 때는 우선 단어의 길이를 비교하고, 같으면 각 글자가 나온 횟수를 저장한 배열의 내용을 비교한다.

주어지는 사전의 단어나 문장의 개수는 0이 될 수 있고, 단어의 길이는 1글자가 될 수 있다.
입력 단어나 문장의 앞뒤로 공백이 있는 것 같다. 입력 문자열에 trim으로 공백을 지워주니 NoSuchElementException이 해결됐다.

*/

fun main() = System.`in`.bufferedReader().run {
    val dict = Array(CHAR_SIZE) { Array(CHAR_SIZE) { mutableListOf<Word>() } }
    repeat(readLine().toInt()) { _ ->
        val string = readLine().trim()
        dict[string.first().index][string.last().index].add(getNewWord(string))
    }
    repeat(readLine().toInt()) { _ ->
        println(readLine().trim().split(" ").fold(1) { answer, str ->
            val strCountArray = getCountArray(str)
            val count = dict[str.first().index][str.last().index].filter { word ->
                word.length == str.length && word.countArray.contentEquals(strCountArray)
            }.size
            answer * count
        })
    }
}

private class Word(
    val countArray: IntArray = IntArray(CHAR_SIZE),
    val length: Int,
)

private const val CHAR_SIZE = 52
private val Char.index
    get() = when (this) {
        in 'A'..'Z' -> code - 65
        in 'a'..'z' -> code - (65 + 6)
        else -> throw Exception()
    }

private fun getNewWord(str: String) = Word(
    countArray = getCountArray(str),
    length = str.length,
)

private fun getCountArray(str: String): IntArray = IntArray(CHAR_SIZE).also { countArray ->
    for (i in 1..str.length - 2) {
        countArray[str[i].index]++
    }
}

/*

*/