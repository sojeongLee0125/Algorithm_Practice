package old.week3;

import java.util.Scanner;

public class BOJ_1189 {
    static int R, C, K, rs;
    static char[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void dfs(int y, int x) {
        if (y == 0 && x == C - 1 && chk[y][x] == K) {
            rs++;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == 'T' || chk[ny][nx] != 0) continue;
            chk[ny][nx] = chk[y][x] + 1;
            dfs(ny, nx);
            chk[ny][nx] = 0;
        }
    }

    public static void main(String[] args) {
        // dfs로 경로를 진행하며 거리가 k이면서 도착하는 경우의 수를 카운트 한다.
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        K = sc.nextInt();

        map = new char[R][C];
        chk = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        map[R - 1][0] = 'a';
        chk[R - 1][0] = 1;

        dfs(R - 1, 0);
        System.out.println(rs);
    }
}
