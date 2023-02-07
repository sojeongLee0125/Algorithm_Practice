package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17406_2 {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] A;
    static int[][] B;
    static ArrayList<int[]> ops = new ArrayList<>();
    static boolean[] rotateChk;
    static int[] rotateOrder;
    static int N, M, K, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1];
        B = new int[N + 1][M + 1];
        rotateChk = new boolean[K];
        rotateOrder = new int[K];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; ++j) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            ops.add(new int[]{r, c, s});
        }

        dfs(0);
        System.out.println(min);
    }

    private static void dfs(int cnt) {
        if (cnt == K) {
            calc();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!rotateChk[i]) {
                rotateOrder[cnt] = i;
                rotateChk[i] = true;
                dfs(cnt + 1);
                rotateChk[i] = false;
            }
        }
    }

    private static void calc() {
        for (int i = 1; i <= N; i++) {
            System.arraycopy(A[i], 0, B[i], 0, A[i].length);
        }
        for (int i = 0; i < K; i++) {
            go(ops.get(rotateOrder[i]));
        }
        getMin();
    }

    private static void go(int[] op) {
        int r = op[0];
        int c = op[1];
        int s = op[2];

        for (int i = 0; i < s; i++) {
            int cy = r - s + i;
            int cx = c - s + i;
            int d = 0;
            int before = B[cy][cx];
            int tmp = 0;

            while (d < 4) {
                cy += dy[d];
                cx += dx[d];

                // 값 교환
                tmp = B[cy][cx];
                B[cy][cx] = before;
                before = tmp;

                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < r - s + i || ny > r + s - i || nx < c - s + i || nx > c + s - i) {
                    d++;
                }
            }
        }
    }

    private static void getMin() {
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += B[i][j];
            }
            min = Math.min(min, sum);
        }
    }

}
