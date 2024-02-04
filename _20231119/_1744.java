package _20231119;

/*

1744번 - 수 묶기
https://www.acmicpc.net/problem/1744
분류 : 그리디 알고리즘, 정렬, 많은 조건 분기


 */

import java.util.Scanner;

public class _1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int answer = 0;
        boolean[] used = new boolean[n];

        while (true) {
            Pair maxPair = null;
            for (int a = 0; a < n; a++) {
                if (used[a]) continue;
                for (int b = a + 1; b < n; b++) {
                    if (used[b]) continue;
                    Pair nowPair = new Pair(a, b, nums[a] + nums[b], nums[a] * nums[b]);
                    if (nowPair.sum < nowPair.multi && (maxPair == null || maxPair.multi < nowPair.multi)) {
                        maxPair = nowPair;
                    }
                }
            }
            if (maxPair != null) {
                answer += maxPair.multi;
                used[maxPair.idx1] = true;
                used[maxPair.idx2] = true;
            } else {
                for (int idx = 0; idx < n; idx++) {
                    if (!used[idx]) {
                        answer += nums[idx];
                    }
                }
                break;
            }
        }
        System.out.println(answer);
    }

    static class Pair {
        int idx1, idx2;
        int sum, multi;

        public Pair(int idx1, int idx2, int sum, int multi) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.sum = sum;
            this.multi = multi;
        }
    }
}