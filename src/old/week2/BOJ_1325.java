package old.week2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1325 {
    public static int N, M, max;
    public static ArrayList<Integer>[] tree;
    public static boolean[] chk;
    public static int[] dp;

    public static void dfs(int num) {
        chk[num] = true;
        for (int n : tree[num]) {
            if (!chk[n]) {
                dp[n]++;
                dfs(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // A B : A가 B를 신뢰한다. -> B를 해킹하면 A도 해킹할 수 있다.
        // 트리구조를 만들어서 자식의 수를 카운트 한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            tree[A].add(B);
        }

        // 각 노드별 자식 갯수 구하기
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            chk = new boolean[N + 1];
            dfs(i);
        }

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (dp[i] == max) bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }
}
