package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static int R, C, T;
    static int[][] room, spr;

    // 반시계방향
    static int[] dy1 = {0, -1, 0, 1};
    static int[] dx1 = {1, 0, -1, 0};

    // 시계방향
    static int[] dy2 = {0, 1, 0, -1};
    static int[] dx2 = {1, 0, -1, 0};

    static ArrayList<int[]> list1; // 윗쪽 청소 리스트
    static ArrayList<int[]> list2; // 아랫쪽 청소 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken()); // 행 (6-50)
        C = Integer.parseInt(st.nextToken()); // 열 (6-50)
        T = Integer.parseInt(st.nextToken()); // 시간 (1-1000)

        room = new int[R][C];
        boolean sw = true;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (sw) {
                        list1 = getClearList(i, j, dy1, dx1);
                        sw = false;
                    } else {
                        list2 = getClearList(i, j, dy2, dx2);
                    }
                }
            }
        }

        // 시간
        while (T-- > 0) {
            // 1. 모든 칸에서 동시에 미세먼지가 확산
            //  - 인접한 네 방향으로 확산
            //  - 인접한 방향에 공기청정기 or 칸이 없으면 확산x
            //  - 확산되는 양은 Ar,c/5이고 소수점은 버린다.
            //  - 남은 미세먼지의 양 Ar,c - (Ar,c/5)×(확산된 방향의 개수)
            spread();

            // 2. 공기청정기가 작동
            //  - 위쪽 공기청정기의 바람은 반시계방향으로 순환, 아래쪽 공기청정기의 바람은 시계방향으로 순환
            //  - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
            //  - 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화
            // 방의 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양
            cleaning(list1);
            cleaning(list2);
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] != -1) answer += room[i][j];
            }
        }

        System.out.println(answer);
    }

    private static ArrayList<int[]> getClearList(int sy, int sx, int[] dy, int[] dx) {
        ArrayList<int[]> result = new ArrayList<>();
        int idx = 0;
        int y = sy;
        int x = sx;

        while (true) {
            int ny = y + dy[idx];
            int nx = x + dx[idx];
            if (ny == sy && nx == sx) break;

            // 진행방향이 바뀌는 경우
            if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                idx++;
                ny = y + dy[idx];
                nx = x + dx[idx];
                if (ny == sy && nx == sx) break;
            }

            y = ny;
            x = nx;
            result.add(new int[]{ny, nx});
        }

        return result;
    }

    private static void spread() {
        spr = new int[R][C];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int subs = room[y][x] / 5;
            for (int k = 0; k < 4; k++) {
                int ny = y + dy1[k];
                int nx = x + dx1[k];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C || room[ny][nx] == -1) continue;
                spr[ny][nx] += subs;
                room[y][x] -= subs;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] += spr[i][j];
            }
        }
    }

    private static void cleaning(ArrayList<int[]> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            room[list.get(i)[0]][list.get(i)[1]] = room[list.get(i - 1)[0]][list.get(i - 1)[1]];
        }
        room[list.get(0)[0]][list.get(0)[1]] = 0;
    }


}
