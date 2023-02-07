package old.week2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2178 {
    static int[][] map, chk;
    static int N, M;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static class Point {
        int y;
        int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void bfs(int y, int x) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = p.y + dy[k];
                int nx = p.x + dx[k];
                if (0 <= ny && ny < N && 0 <= nx && nx < M && chk[ny][nx] == 0 && map[ny][nx] == 1) {
                    chk[ny][nx] = chk[p.y][p.x] + 1;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    public static void main(String[] args) {
        // bfs를 돌면서 거리값 배열 구하기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        chk = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && chk[i][j] == 0) {
                    chk[i][j] = 1;
                    bfs(i, j);
                }
            }
        }

        System.out.println(chk[N-1][M-1]);
    }
}