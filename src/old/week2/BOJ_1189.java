package old.week2;

import java.util.Scanner;

public class BOJ_1189 {
    public static int R, C, K;
    public static char[][] map;
    public static int[][] chk;

    public static int dfs(int y, int x) {
        int rs = 0;
        if (y == 0 && x == (C - 1)) {
            // 도착점에 도착해서 경우의 수를 만족하는 경우
            if (chk[y][x] == K) return 1;
            else return 0;
        } else {
            //dfs 실시
            int[] dy = {-1, 0, 1, 0};
            int[] dx = {0, 1, 0, -1};
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (0 <= ny && ny < R && 0 <= nx && nx < C && map[ny][nx] != 'T' && chk[ny][nx] == 0) {
                    chk[ny][nx] = chk[y][x] + 1;
                    rs += dfs(ny, nx);
                    // 경우의 수를 하나 계산했으면 체크를 해제
                    chk[ny][nx] = 0;
                }
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        // 집까지 도착하는 경우의 수를 구하기
        // 단 T 가 있는 곳은 지나지 못하고, 집까지의 거리가 K인 경우의 수
        // dfs로 구하면서 방문했던 곳은 체크를 해제하는 것이 중요
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt(); // 세로
        C = sc.nextInt(); // 가로
        K = sc.nextInt(); // 거리
        map = new char[R][C];
        chk = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 시작점 거리 1로 초기화
        chk[R - 1][0] = 1;

        System.out.println(dfs(R - 1, 0));
    }
}
