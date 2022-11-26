package week4;

import java.util.Scanner;

public class BOJ_1062 {
    // 학생들은 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
    // 모든 단어는 "anta"로 시작되고, "tica"로 끝난다.
    // K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값
    static int N, K, cnt = Integer.MIN_VALUE;
    static int[] word;
    public static int cal(int mask) {
        int cnt = 0;
        for (int w : word) {
            if ((w & mask) == w) cnt++;
        }
        return cnt;
    }

    public static int sol(int idx, int k, int mask) {
        if (k < 0) return 0;
        if (idx == 26) return cal(mask);

        // 현재 선택된 idx 알파벳을 배우는 경우의 수
        cnt = Math.max(cnt, sol(idx + 1, k - 1, mask | (1 << idx)));

        // 무조건 배워야 하는 경우에는 안배우는 경우의 수를 배제
        if (idx != 'a' - 'a' && idx != 'c' - 'a' && idx != 'i' - 'a' && idx != 'n' - 'a' && idx != 't' - 'a') {
            cnt = Math.max(cnt, sol(idx + 1, k, mask));
        }

        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 단어의 갯수
        K = sc.nextInt(); // 글자의 갯수
        word = new int[N];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (char c : str.toCharArray()) {
                word[i] |= (1 << c - 'a');
            }
        }

        System.out.println(sol(0, K, 0));
    }
}
