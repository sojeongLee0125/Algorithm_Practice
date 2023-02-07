package old.week2;

import java.util.Scanner;

public class BOJ_2468 {
    static int N;
    static int[][] map;
    static boolean[][] chk;

    public static void dfs(int y, int x, int depth) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (0 <= ny && ny < N && 0 <= nx && nx < N && map[ny][nx] > depth && chk[ny][nx] == false) {
                chk[ny][nx] = true;
                dfs(ny, nx, depth);
            }
        }
    }

    public static void main(String[] args) {
        // 일정한 높이 이하의 모든지점은 물에 잠긴다
        // 안전한 영역 : 물에 잠기지 않는 지점들이 위 아래 오 왼으로 인접
        // 비의 양 : 1 ~ 최대 높이
        // 안전한 영역의 최대갯수 구하기 - dfs

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int answer = 0;
        for (int i = 0; i < 101; i++) {
            int cnt = 0;
            chk = new boolean[N][N];
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] > i && !chk[y][x]) {
                        chk[y][x] = true;
                        dfs(y, x, i);
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}