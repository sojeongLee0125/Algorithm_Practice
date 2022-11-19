package week3;

import java.util.Scanner;

public class BOJ_15684 {
    static int N, M, H;
    static int min = Integer.MAX_VALUE;
    static boolean[][] visited;

    public static boolean isOk() {
        for (int i = 1; i <= N; i++) {
            int start = i;
            for (int j = 1; j <= H; j++) {
                if (visited[j][start]) start++;
                else if (visited[j][start - 1]) start--;
            }
            if (i != start) return false;
        }
        return true;
    }

    public static void dfs(int cur, int cnt) {
        if (cnt > 3 || cnt >= min) return;
        if (isOk()) {
            min = Math.min(min, cnt);
            return;
        }
        for (int i = cur; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j] || visited[i][j - 1] || visited[i][j + 1]) continue;
                visited[i][j] = true;
                dfs(i, cnt + 1);
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        // 가로선은 인접한 두 세로선을 연결
        // 두 가로선이 연속하거나 서로 접하면 안 된다.
        // 1번 세로선과 2번 세로선이 1번 위치에서 연결되면 2번 세로선과 3번 세로선은 1번 위치에서 연결될 수 없다.
        // 사다리에 가로선을 추가해서, 사다리 게임의 결과를 조작 => i번 세로선의 결과가 i번
        // 추가해야 하는 가로선 개수의 최솟값
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 세로선의 갯수
        M = sc.nextInt(); // 현재 있는 가로선의 갯수
        H = sc.nextInt(); // 세로선마다 가로선 놓을 수 있는 위치의 갯수
        visited = new boolean[50][50];

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            visited[a][b] = true;
        }

        dfs(1, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}
