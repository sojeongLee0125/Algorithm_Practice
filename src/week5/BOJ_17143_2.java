package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_2 {
    static int R, C, M, rs;
    static Shark[] sharks;
    static int[][] map;
    static int[][] tmp;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;
        int dead;

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

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new Shark[M + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(--r, --c, s, --d, z);

            // 이동 후 좌표 : next = (cur + 이동수) % 2(전체 크기 - 1)
            if (shark.d <= 1) shark.s = s % (2 * (R - 1)); // 상하
            else shark.s = s % (2 * (C - 1)); // 좌우

            sharks[i] = shark;
            map[shark.r][shark.c] = i;
        }

        for (int t = 0; t < C; t++) {
            // 낚시
            for (int y = 0; y < R; y++) {
                if (map[y][t] != 0) {
                    rs += sharks[map[y][t]].z;
                    sharks[map[y][t]].dead = 1;
                    map[y][t] = 0;
                    break;
                }
            }

            // 상어 이동
            tmp = new int[R][C];
            for (int i = 1; i <= M; i++) {
                if (sharks[i].dead == 1) continue;

                int y = sharks[i].r;
                int x = sharks[i].c;
                int s = sharks[i].s;
                int d = sharks[i].d;

                int ny = 0;
                int nx = 0;

                while (true) {
                    ny = y + s * dy[d];
                    nx = x + s * dx[d];
                    if (ny < R && ny >= 0 && nx < C && nx >= 0) break;

                    if (d <= 1) {
                        // 0과 1일 경우 - 위 아래
                        if (ny < 0) {
                            s = s - y;
                            y = 0;
                        } else {
                            s = s - (R - 1 - y);
                            y = R - 1;
                        }
                    } else {
                        // 오른쪽 왼쪽
                        if (nx < 0) {
                            s = s - x;
                            x = 0;
                        } else {
                            s = s - (C - 1 - x);
                            x = C - 1;
                        }
                    }

                    // 1 <> 0 && 2 <> 3
                    d ^= 1;
                }

                if (tmp[ny][nx] != 0) {
                    if (sharks[tmp[ny][nx]].z < sharks[i].z) {
                        sharks[tmp[ny][nx]].dead = 1;
                        tmp[ny][nx] = i;
                    } else sharks[i].dead = 1;
                } else tmp[ny][nx] = i;

                sharks[i].r = ny;
                sharks[i].c = nx;
                sharks[i].d = d;
            }

            for (int i = 0; i < tmp.length; i++) {
                System.arraycopy(tmp[i], 0, map[i], 0, tmp[i].length);
            }
        }

        System.out.println(rs);
    }
}