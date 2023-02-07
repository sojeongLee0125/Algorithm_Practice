package old.week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2589 {
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[][] chk;
    static char[][] map;

    public static void bfs(int y, int x) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if (0 <= ny && 0 <= nx && ny < N && nx < M) {
                    if (map[ny][nx] == 'L' && chk[ny][nx] == 0) {
                        chk[ny][nx] = chk[cur[0]][cur[1]] + 1;
                        q.offer(new int[]{ny, nx});
                        max = Math.max(max, chk[ny][nx]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 이동은 상하좌우 육지로만 가능하다.
        // 한 칸을 이동하는데 1시간이 걸린다.
        // 보물은 서로간에 최단거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 묻혀있다.
        // 두 곳 사이를 최단거리로 이동하는 시간을 구한다.
        // 육지를 발견하면 bfs를 돌린다.
        // 해당 bfs 내에서 거리의 최대값을 구하고 갱신한다.

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // bfs 실시
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    chk = new int[N][M];
                    chk[i][j] = 1;
                    bfs(i, j);
                }
            }
        }

        System.out.println(max - 1);
    }
}
