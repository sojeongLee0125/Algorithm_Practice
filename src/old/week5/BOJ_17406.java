package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17406 {
    // 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값
    // 회전 연산은 세 정수 (r, c, s)로 이루어져 있고,
    // 가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 시계 방향으로 한 칸씩 돌린다
    // 배열 A와 사용 가능한 회전 연산이 주어졌을 때, 배열 A의 값의 최솟값

    static int N, M, K, min = Integer.MAX_VALUE;
    static int[][] A;
    static ArrayList<int[]> rotate = new ArrayList<>();
    static boolean[] rotateChk = new boolean[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 행 (3 ≤ N, M ≤ 50)
        M = Integer.parseInt(st.nextToken()); // 열 3 ≤ N, M ≤ 50
        A = new int[N + 1][M + 1];

        K = Integer.parseInt(st.nextToken()); // 회전연산수 1 ≤ K ≤ 6

        // 1 ≤ A[i][j] ≤ 100
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1 ≤ s / 1 ≤ r-s < r < r+s ≤ N / 1 ≤ c-s < c < c+s ≤ M
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotate.add(new int[]{r, c, s});
        }

        // 1. rotate 연산 중 하나를 선택한다.(dfs)
        go(0, A);

        System.out.println(min);

    }

    // 2. 왼쪽 윗 칸이 (r-s, c-s), 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 시계 방향으로 한 칸씩 돌린다
    // 3. rotate 연산을 모두 다 적용했을 때의 각 행의 합의 최소값을 갱신한다.
    private static void go(int cnt, int[][] map) {
        if (cnt == rotate.size()) {
            getMin(map);
            return;
        }
        for (int i = 0; i < rotate.size(); i++) {
            if (rotateChk[i]) continue;
            rotateChk[i] = true;
            int[][] after = rotateMap(map, rotate.get(i));
            go(cnt + 1, after);
            rotateChk[i] = false;
        }
    }

    private static int[][] rotateMap(int[][] map, int[] arr) {
        int[][] after = new int[N + 1][M + 1];
        int r = arr[0];
        int c = arr[1];
        int s = arr[2];

        for (int i = 1; i <= N; i++) {
            System.arraycopy(map[i], 0, after[i], 0, map[i].length);
        }

        int sy = r - s;
        int sx = c - s;

        int ey = r + s;
        int ex = c + s;

        int my = (sy + ey) / 2;
        int mx = (sx + ex) / 2;

        while (true) {
            int x = sx;
            int y = sy;

            // 최 상단 가로
            while (x < ex) {
                after[sy][x + 1] = map[sy][x];
                x++;
            }

            // 오른쪽 세로
            while (y < ey) {
                after[y + 1][x] = map[y][x];
                y++;
            }

            // 아래 가로
            while (x > sx) {
                after[ey][x - 1] = map[ey][x];
                x--;
            }

            // 왼쪽 세로
            while (y > sy) {
                after[y - 1][sx] = map[y][sx];
                y--;
            }

            sx++;
            sy++;
            ey--;
            ex--;

            if (sy >= my && sx >= mx) break;
        }

        return after;
    }

    private static void getMin(int[][] map) {
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += map[i][j];
            }
            min = Math.min(min, sum);
        }
    }


}
