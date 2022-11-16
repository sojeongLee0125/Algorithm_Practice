package week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851 {
    static int N, K;
    static int[] cnt = new int[200005];
    static int[] chk = new int[200005];
    static final int MAX = 200001;

    public static void bfs(int cur) {
        Queue<Integer> q = new LinkedList<>();
        chk[cur] = 1;
        cnt[cur] = 1;
        q.offer(cur);
        while (!q.isEmpty()) {
            int c = q.poll();
            int[] arr = {c + 1, c - 1, c * 2};
            for (int n : arr) {
                if (0 <= n && n < MAX) {
                    if (chk[n] == 0) {
                        q.offer(n);
                        chk[n] = chk[c] + 1;
                        cnt[n] += cnt[c];
                    } else if (chk[n] == chk[c] + 1) {
                        cnt[n] += cnt[c];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 수빈이는 현재 점 N, 동생은 점 K
        // 걷는다면 1초 후 -1 혹은 +1
        // 순간이동은 2*X
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else {
            bfs(N);
            System.out.println(chk[K] - 1);
            System.out.println(cnt[K]);
        }
    }
}
