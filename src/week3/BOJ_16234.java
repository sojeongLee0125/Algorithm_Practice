package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16234 {
    static int N, L, R, sum;
    static int[][] map;
    static boolean[][] chk;
    static ArrayList<int[]> list;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (0 <= ny && 0 <= nx && ny < N && nx < N) {
                    if (!chk[ny][nx] && L <= Math.abs(map[cur[0]][cur[1]] - map[ny][nx])
                            && Math.abs(map[cur[0]][cur[1]] - map[ny][nx]) <= R) {
                        chk[ny][nx] = true;
                        list.add(new int[]{ny, nx});
                        sum += map[ny][nx];
                        q.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 입력값 N, L, R
        // N개의 줄에 L 이상 R 이하의 차이일 경우 국경선이 열린다.
        // ArrayList<ArrayList> 넣어서 인구수 차이가 이상이면 연결 리스트에 추가한다.
        // true / false 로 현재 연결된 위치를 표시한다.
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // bfs 탐색으로 열린 국경선을 추가한다.
        int cnt = 0;
        while (true) {
            boolean sw = true;
            chk = new boolean[N][N];
            // BFS 로 인구수를 조절할 나라들을 구한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!chk[i][j]) {
                        list = new ArrayList<>();
                        chk[i][j] = true;
                        list.add(new int[]{i, j});
                        sum = map[i][j];
                        bfs(i, j);
                        if (list.size() == 1) continue;
                        // 국경선이 열렸다면
                        for (int[] arr : list) {
                            map[arr[0]][arr[1]] = sum / list.size();
                            sw = false;
                        }
                    }
                }
            }

            // 인구이동이 없었다면 멈춘다.
            if (sw) break;
            cnt++;
        }

        System.out.println(cnt);
    }
}
