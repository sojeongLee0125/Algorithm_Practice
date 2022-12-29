package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143 {
    // 크기가 R×C인 격자판 / 칸에는 상어가 최대 한 마리 들어있을 수 있다. 상어는 크기와 속도를 가지고 있다.
    // 처음에 1열 한 칸 왼쪽 / 낚시왕 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.

    // 1초동안
    // 1. 낚시왕이 오른쪽으로 한 칸 이동
    // 2. 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
    // 3. 상어가 이동한다.
    // 상어는 주어진 속도로 이동, 속도의 단위는 칸/초 / 격자판의 경계를 넘는 경우에는 방향을 반대로
    // 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.

    static int R, C, M;
    static Shark[][] map;
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1};

    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        // 상어의 정보는 다섯 정수 r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000)
        //  (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다.
        //  d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, s, d, z);
            map[r][c] = shark;
        }

        // 낚시왕이 잡은 상어 크기의 합을 출력한다.
        // 이동 수 만큼 dfs
        go(1, 0, map);
    }

    private static void go(int time, int sum, Shark[][] map) {
        if (time == C + 1) {
            System.out.println(sum);
        } else {
            // 1. 낚시왕이 상어를 잡는다.
            int score = fish(time, map);
            // 2. 상어들이 이동한 후의 맵을 구한다.
            // 3. 두 마리 이상 있는 상어는 잡아먹는다.
            Shark[][] tmp = move(map);
            go(time + 1, sum + score, tmp);
        }
    }

    private static int fish(int time, Shark[][] map) {
        int tmp = 0;
        for (int i = 1; i <= R; i++) {
            if (map[i][time] != null) {
                tmp += map[i][time].z;
                map[i][time] = null;
                break;
            }
        }
        return tmp;
    }

    private static Shark[][] move(Shark[][] map) {
        Shark[][] tmpMap = new Shark[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] != null) {
                    change(map[i][j], tmpMap);
                }
            }
        }

        return tmpMap;
    }

    private static void change(Shark shark, Shark[][] tmpMap) {
        int ny = shark.r + (dy[shark.d] * shark.s);
        int nx = shark.c + (dx[shark.d] * shark.s);

        boolean udFlag = true;
        while (ny <= 0 || ny > R) {
            if (shark.d == 2) udFlag = false;

            if (udFlag) {
                // 위로 이동하다가 넘치는 경우
                udFlag = !udFlag;
                ny = 1 + (1 - ny);
            } else {
                // 아래로 이동하다가 넘치는 경우
                udFlag = !udFlag;
                ny = R - (ny - R);
            }

            if (udFlag) shark.d = 1;
            else shark.d = 2;
        }

        // 양옆으로 이동하다가 넘치는 경우
        boolean rlFlag = true;
        while (nx <= 0 || nx > C) {
            if (shark.d == 4) rlFlag = false;

            if (rlFlag) {
                // 오른쪽 이동하다가 넘치는 경우
                rlFlag = !rlFlag;
                nx = C - (nx - C);
            } else {
                // 왼쪽로 이동하다가 넘치는 경우
                rlFlag = !rlFlag;
                nx = 1 + (1 - nx);
            }
            if (rlFlag) shark.d = 3;
            else shark.d = 4;
        }

        shark.r = ny;
        shark.c = nx;

        if (tmpMap[ny][nx] != null) {
            Shark origin = tmpMap[ny][nx];
            if (origin.z < shark.z) {
                tmpMap[ny][nx] = shark;
            }
        } else {
            tmpMap[ny][nx] = shark;
        }
    }
}
