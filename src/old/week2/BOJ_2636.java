package old.week2;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2636 {
    static int N, M;
    static int[][] map, chk;
    static ArrayList<int[]> list;

    public static void dfs(int y, int x) {
        if (map[y][x] == 1) {
            list.add(new int[]{y, x});
        } else {
            int[] dy = {-1, 0, 1, 0};
            int[] dx = {0, 1, 0, -1};
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (0 <= ny && ny < N && 0 <= nx && nx < M && chk[ny][nx] == 0) {
                    chk[ny][nx] = 1;
                    dfs(ny, nx);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1. 치즈 값들을 입력받는다.
        // 2. 1시간이 지나면 가장자리의 치즈들이 다 녹는다.
        //    dfs를 걸고 1을 만나면 치즈위치를 저장하고 리턴한다.
        // 3. dfs 다 돌고 치즈 위치들을 0으로 만들어 준다.

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int time = 0;
        int cnt = 0;
        while (true) {
            chk = new int[N][M];
            list = new ArrayList<>();

            //dfs 돌려서 지워야할 지즈 구하기
            dfs(0, 0);

            // 지워야할 치즈 지우기
            cnt = 0;
            for (int[] arr : list) {
                map[arr[0]][arr[1]] = 0;
                cnt++;
            }
            time++;

            // 치즈가 하나도 안남아 있으면 break
            boolean sw = true;
            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        sw = false;
                        break outer;
                    }
                }
            }

            if (sw) break;
        }

        System.out.println(time);
        System.out.println(cnt);
    }

}
