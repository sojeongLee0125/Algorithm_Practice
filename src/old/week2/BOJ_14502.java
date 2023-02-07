package old.week2;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_14502 {
    public static int N, M;
    public static int[][] map, chk;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    public static void dfs(int y, int x) {
        chk[y][x] = 1;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                if (map[ny][nx] == 0 && chk[ny][nx] == 0) {
                    dfs(ny, nx);
                }
            }
        }
    }

    public static int solution() {
        chk = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    dfs(i, j);
                }
            }
        }

        int rs = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && chk[i][j] == 0) {
                    rs++;
                }
            }
        }

        return rs;
    }


    public static void main(String[] args) {
        // 1. 값이 0인 곳들을 저장한다.
        // 2. 해당 좌표들의 조합 3개를 구해서 벽을 세우고, 독가스가 퍼져나간 뒤 완전탐색으로 안전영역을 구한다.
        // 3. 최대값을 구한다.
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }

        // 3개의 조합을 구해서 벽을 세우고 독가스를 퍼지게 한다.
        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    map[list.get(i)[0]][list.get(i)[1]] = map[list.get(j)[0]][list.get(j)[1]] = map[list.get(k)[0]][list.get(k)[1]] = 1;
                    answer = Math.max(solution(), answer);
                    map[list.get(i)[0]][list.get(i)[1]] = map[list.get(j)[0]][list.get(j)[1]] = map[list.get(k)[0]][list.get(k)[1]] = 0;
                }
            }
        }
        System.out.println(answer);
    }
}
