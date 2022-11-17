package week3;

import java.io.IOException;
import java.util.*;

public class BOJ_3197 {
    static int R, C, cnt, swanY, swanX;
    static char[][] map;
    static boolean[][] chk;
    static boolean[][] swan_chk;

    static Queue<int[]> swan = new LinkedList<>();
    static Queue<int[]> swan_tmp = new LinkedList<>();
    static Queue<int[]> water = new LinkedList<>();
    static Queue<int[]> water_tmp = new LinkedList<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        chk = new boolean[R][C];
        swan_chk = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'L') {
                    swanY = i;
                    swanX = j;
                }
                if (map[i][j] == '.' || map[i][j] == 'L') {
                    chk[i][j] = true;
                    water.offer(new int[]{i, j});
                }
            }
        }

        swan.offer(new int[]{swanY, swanX});
        swan_chk[swanY][swanX] = true;

        while (true) {
            if (isMove()) break;
            melt();
            water = water_tmp;
            swan = swan_tmp;
            water_tmp = new LinkedList<>();
            swan_tmp = new LinkedList<>();
            cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isMove() {
        while (!swan.isEmpty()) {
            int[] cur = swan.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || swan_chk[ny][nx]) continue;
                swan_chk[ny][nx] = true;
                if (map[ny][nx] == '.') {
                    swan.offer(new int[]{ny, nx});
                } else if (map[ny][nx] == 'X') {
                    swan_tmp.offer(new int[]{ny, nx});
                } else if (map[ny][nx] == 'L') {
                    return true;
                }
            }
        }
        return false;
    }

    private static void melt() {
        while (!water.isEmpty()) {
            int[] cur = water.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || chk[ny][nx]) continue;
                if (map[ny][nx] == 'X') {
                    chk[ny][nx] = true;
                    water_tmp.offer(new int[]{ny, nx});
                    map[ny][nx] = '.';
                }
            }
        }
    }
}
