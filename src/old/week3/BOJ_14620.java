package old.week3;

import java.util.Scanner;

public class BOJ_14620 {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] cost;
    static boolean[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void sol(int cnt, int cst) {
        if (cnt == 3) {
            min = Math.min(min, cst);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isOk(i, j)) {
                    sol(cnt + 1, cst + put(i, j));
                    remove(i, j);
                }
            }
        }
    }

    public static boolean isOk(int y, int x) {
        if (chk[y][x]) return false;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || chk[ny][nx]) return false;
        }
        return true;
    }

    private static int put(int i, int j) {
        int rs = 0;
        chk[i][j] = true;
        rs += cost[i][j];
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            chk[ny][nx] = true;
            rs += cost[ny][nx];
        }
        return rs;
    }

    private static void remove(int i, int j) {
        chk[i][j] = false;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            chk[ny][nx] = false;
        }
    }

    public static void main(String[] args) {
        // 화단에서 씨앗을 심으면 해당 지점 + 상하좌우 공간을 차지
        // 꽃잎이 겹치면 안되고, 꽃잎이 좌표 밖으로 나가면 안된다.
        // 최소비용 구하기

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 화단 한 변의 길이
        cost = new int[N][N];
        chk = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        sol(0, 0);
        System.out.println(min);
    }
}
