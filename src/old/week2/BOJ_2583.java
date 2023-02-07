package old.week2;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2583 {
    static int M, N, K;
    static int[][] map;
    static boolean[][] chk;
    static ArrayList<Integer> list = new ArrayList<>();

    public static int dfs(int y, int x) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int rs = 1;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (0 <= ny && ny < M && 0 <= nx && nx < N && map[ny][nx] == 0 && !chk[ny][nx]) {
                chk[ny][nx] = true;
                rs += dfs(ny, nx);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // 입력받은 직사각형 범위는 전부 1로 처리하기
        // 0이면서 방문하지 않은 지점의 넒이 최댓값을 dfs로 구한다.
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt(); // y좌표
        N = sc.nextInt(); // x좌표
        K = sc.nextInt();
        map = new int[M][N];
        chk = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            // 왼쪽 아래 꼭지점
            int lx = sc.nextInt();
            int ly = sc.nextInt();
            // 오른쪽 위 꼭지점
            int rx = sc.nextInt();
            int ry = sc.nextInt();
            for (int x = lx; x < rx; x++) {
                for (int y = ly; y < ry; y++) {
                    map[y][x] = 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !chk[i][j]) {
                    chk[i][j] = true;
                    list.add(dfs(i, j));
                }
            }
        }

        list.sort(Integer::compareTo);
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
