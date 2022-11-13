package week3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_4179 {
    static int R, C, sy, sx, rs;
    static int[][] map;
    static int[][] fireChk;
    static int[][] jinsuChk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new int[R][C];
        fireChk = new int[R][C];
        jinsuChk = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireChk[i], Integer.MAX_VALUE);
        }
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    jinsuChk[i][j] = 1;
                    sy = i;
                    sx = j;
                } else if (map[i][j] == 'F') {
                    fireChk[i][j] = 1;
                    q.offer(new int[]{i, j});
                }
            }
        }

        // 불이 번지는 과정 계산
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (0 <= ny && 0 <= nx && ny < R && nx < C) {
                    if (fireChk[ny][nx] != Integer.MAX_VALUE || map[ny][nx] == '#') continue;
                    fireChk[ny][nx] = fireChk[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{ny, nx});
                }
            }
        }

        // 진수의 이동 체크
        q.offer(new int[]{sy, sx});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            if (cy == R - 1 || cx == C - 1 || cy == 0 || cx == 0) {
                rs = jinsuChk[cy][cx];
                break;
            }
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (0 <= ny && 0 <= nx && ny < R && nx < C) {
                    if (jinsuChk[ny][nx] != 0 || map[ny][nx] == '#') continue;
                    if (fireChk[ny][nx] <= jinsuChk[cy][cx] + 1) continue;
                    jinsuChk[ny][nx] = jinsuChk[cy][cx] + 1;
                    q.offer(new int[]{ny, nx});
                }
            }
        }

        if (rs != 0) {
            System.out.println(rs);
        } else System.out.println("IMPOSSIBLE");

    }
}
