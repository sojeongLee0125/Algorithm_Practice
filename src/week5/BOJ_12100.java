package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100 {

    // 1. 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것
    // 2. 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다.
    // 3. 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
    // 4. 보드의 크기가 N×N. 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
    static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기 N (1 ≤ N ≤ 20)
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j], max);
            }
        }

        move(0, map);
        System.out.println(max);
    }

    private static void move(int cnt, int[][] map) {
        if (cnt == 5) {
            getMax(map);
            return;
        }
        for (int i = 0; i < 4; i++) {
            move(cnt + 1, game(map));
            // 90도 회전으로 4번 - 상 하 좌 우
            rotate(map);
        }
    }

    private static void getMax(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    private static int[][] game(int[][] map) {
        int[][] nxt = new int[N][N];

        for (int i = 0; i < N; i++) {
            int idx = -1; // 현재까지 합쳐진 곳의 인덱스
            boolean sw = false;  // 합쳐질 수 있는지 여부

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                if (sw && map[i][j] == nxt[i][idx]) {
                    nxt[i][idx] *= 2;
                    sw = false;
                } else {
                    nxt[i][++idx] = map[i][j];
                    sw = true;
                }
            }
            for (idx++; idx < N; idx++) {
                nxt[i][idx] = 0;
            }
        }
        return nxt;
    }

    private static void rotate(int[][] map) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[N - j - 1][i];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            System.arraycopy(tmp[i], 0, map[i], 0, tmp[i].length);
        }
    }
}
