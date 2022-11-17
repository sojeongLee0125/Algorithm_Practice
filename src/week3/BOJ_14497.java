package week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14497 {
    static int N, M, sy, sx, ty, tx, cnt;
    static char[][] map;
    static boolean[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new char[N][M];
        chk = new boolean[N][M];

        sy = sc.nextInt() - 1;
        sx = sc.nextInt() - 1;

        ty = sc.nextInt() - 1;
        tx = sc.nextInt() - 1;

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sy, sx});
        chk[sy][sx] = true;
        while (!chk[ty][tx]) {
            cnt++;
            Queue<int[]> tmp = new LinkedList<>();
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cy = cur[0];
                int cx = cur[1];
                for (int k = 0; k < 4; k++) {
                    int ny = cy + dy[k];
                    int nx = cx + dx[k];
                    if (0 > ny || 0 > nx || ny >= N || nx >= M || chk[ny][nx]) continue;
                    chk[ny][nx] = true;
                    if (map[ny][nx] != '0') {
                        map[ny][nx] = '0';
                        tmp.offer(new int[]{ny, nx});
                    } else q.offer(new int[]{ny, nx});
                }
            }
            q = tmp;
        }
        System.out.println(cnt);
    }
}
