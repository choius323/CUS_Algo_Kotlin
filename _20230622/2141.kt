package _20230622

/*

2141번 - 우체국
https://www.acmicpc.net/problem/2141
분류 : 

우체국의 위치에 따른 거리의 합을 함수로 나타내면
f(x) = |x-1|+|x-5|*2+|x+3|*5
와 같은 형식이 된다.
이 함수의 뾰족점은 -3, 1, 5와 같은 우체국의 위치가 되고, 이 중에 합을 가장 작게 만드는 위치를 찾으면 된다.

인구수를 절반으로 나누는 위치가 정답이다 ----> ???

인구의 반을 계산할 때는 3/2=>1이 되면서 틀리게 되므로 1을 더해서 (3+1)/2=>2로 계산한다.


https://www.acmicpc.net/board/view/87103

*/

fun main() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    data class Town(val pos: Int, val count: Int)

    val r = { nextToken();nval.toInt() }

    val towns = Array(r()) { Town(r(), r()) }.sortedArrayWith(compareBy(Town::pos))
    val totalCount = towns.sumOf { it.count.toLong() }
    var sumCount = 0L
    for (i in towns.indices) {
        sumCount += towns[i].count
        if (sumCount >= (totalCount + 1) / 2) {
            return print(towns[i].pos)
        }
    }
}

/*
2
5 1
1 1

1


3
1 3
3 1
4 1

1


2
2 1
1 1

1



2
1 1
2 2

2

*/