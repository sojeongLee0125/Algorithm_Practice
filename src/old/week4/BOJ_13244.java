package old.week4;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_13244 {
    static int T, N, M;
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] chk;

    public static void main(String[] args) {
        // 해당 그래프가 트리인지 파악하는 문제
        // 1. 에지를 따라 모든 노드에 도달할 수 있어야 한다.
        // 2. 에지가 제거되면 그래프는 연결되지 않는다.
        // 3. 존재하는 두 노드에 에지가 추가되면 사이클이 생성된다.
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt(); // 테스트 케이스 갯수

        while (T-- > 0) {
            N = sc.nextInt(); // 노드 수 (1 - 1000)
            M = sc.nextInt(); // 에지 수 (1 - 10^6)
            chk = new boolean[N + 1];

            map = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                map.get(a).add(b);
                map.get(b).add(a);
            }

            // dfs를 돌렸을 때 1번만 도는지 체크
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (!chk[i]) {
                    dfs(i);
                    cnt++;
                }
            }
            if (cnt > 1) {
                System.out.println("graph");
                continue;
            }
            if (N - 1 == M) {
                System.out.println("tree");
            } else {
                System.out.println("graph");
            }
        }
    }
    private static void dfs(int node) {
        chk[node] = true;
        for (int nx : map.get(node)) {
            if (!chk[nx]) {
                dfs(nx);
            }
        }
    }
}
