package week3;

import java.util.Scanner;

public class BOJ_1987 {
    static int R, C, cnt;
    static char[][] map;
    static boolean[] visited = new boolean[100];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void dfs(int y, int x, int count) {
        cnt = Math.max(cnt, count);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 > ny || 0 > nx || ny >= R || nx >= C) continue;
            if (!visited[map[ny][nx] - 'A']) {
                visited[map[ny][nx] - 'A'] = true;
                dfs(ny, nx, count + 1);
                visited[map[ny][nx] - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;
        // dfs로 탐색하고 돌아오면서 체크를 푼다.
        dfs(0, 0, 1);
        System.out.println(cnt);
    }
}
