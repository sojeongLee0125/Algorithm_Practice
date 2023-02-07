package old.week3;

import java.util.*;

public class BOJ_13913 {
    static int N, K, rs;
    static int[] chk = new int[200005];
    static int[] parent = new int[200005];
    static final int MAX = 200005;

    public static void bfs(int cur) {
        Queue<Integer> q = new LinkedList<>();
        chk[cur] = 1;
        q.offer(cur);
        while (!q.isEmpty()) {
            int c = q.poll();
            if (c == K) {
                rs = chk[c];
                break;
            }
            int[] arr = {c + 1, c - 1, c * 2};
            for (int n : arr) {
                if (0 <= n && n < MAX && chk[n] == 0) {
                    chk[n] = chk[c] + 1;
                    parent[n] = c;
                    q.offer(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bfs(N);

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = K; i != N; i = parent[i]) {
            answer.add(i);
        }
        answer.add(N);

        System.out.println(rs - 1);
        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
