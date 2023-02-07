package old.week4;

import java.util.Scanner;

public class BOJ_2234 {
    static int R, C, cnt, wid, removeWid;
    static int[][] map;
    static int[][] chk;
    static int[] size = new int[2505];
    //                 서  북  동  남
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt(); // 열 정보
        R = sc.nextInt(); // 행 정보
        chk = new int[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 1. 방의 개수 2. 가장 넓은 방의 넚이 3. 하나의 벽을 제거하여 얻을 수 있는 가낭 넓은 방의 크기
        // dfs를 돌리면서 해당 칸의 2진수화 된 숫자 4자리를 비교하여, 서 북 동 남 중 갈 수 있으면 진행한다.
        // 해당 방의 갯수와 가장 넓은 방의 넓이를 먼저 구한다.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (chk[i][j] == 0) {
                    cnt++;
                    size[cnt] = dfs(i, j, cnt);
                    wid = Math.max(wid, size[cnt]);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i + 1 < R) {
                    int a = chk[i + 1][j];
                    int b = chk[i][j];
                    if (a != b) {
                        removeWid = Math.max(removeWid, size[a] + size[b]);
                    }
                }
                if (j + 1 < C) {
                    int a = chk[i][j + 1];
                    int b = chk[i][j];
                    if (a != b) {
                        removeWid = Math.max(removeWid, size[a] + size[b]);
                    }
                }
            }
        }

        System.out.println(cnt);
        System.out.println(wid);
        System.out.println(removeWid);
    }

    private static int dfs(int y, int x, int cnt) {
        chk[y][x] = cnt;
        int rs = 1;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C || chk[ny][nx] != 0) continue;
            if ((map[y][x] & (1 << k)) != 0) continue;
            rs += dfs(ny, nx, cnt);
        }
        return rs;
    }
}
