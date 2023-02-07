package old.week4;

import java.util.Scanner;

public class BOJ_1987 {
    static int R, C, max = Integer.MIN_VALUE;
    static char[][] map;
    static boolean[] abc = new boolean[50];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

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

        dfs(0, 0, 1 << (int) map[0][0] - 'A', 1);
        System.out.println(max);
    }

    private static void dfs(int y, int x, int num, int cnt) {
        max = Math.max(max, cnt);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
            int next = (1 << (int) map[ny][nx] - 'A');
            if ((num & next) == 0) dfs(ny, nx, num | next, cnt + 1);
        }
    }
}
