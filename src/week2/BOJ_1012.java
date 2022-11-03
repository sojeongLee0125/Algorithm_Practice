package week2;

import java.util.Scanner;

public class BOJ_1012 {
    static int[][] map;
    static int N, M;

    public static void dfs(int y, int x) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (0 <= ny && ny < N && 0 <= nx && nx < M && map[ny][nx] == 1) {
                map[ny][nx] = 2;
                dfs(ny, nx);
            }
        }
    }

    public static void main(String[] args) {
        // 배추의 범위를 dfs로 구해서 cnt++
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            M = sc.nextInt();
            N = sc.nextInt();
            int cnt = 0;
            int K = sc.nextInt();
            map = new int[N][M];

            for (int j = 0; j < K; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1;
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[y][x] == 1) {
                        map[y][x] = 2;
                        dfs(y, x);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }
}
