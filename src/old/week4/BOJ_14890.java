package old.week4;

import java.util.Scanner;

public class BOJ_14890 {
    static int N, L, rs;
    static int[][] map;
    static int[][] reverse;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 맵 너비
        L = sc.nextInt(); // 경사로 길이
        map = new int[N][N];
        reverse = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                reverse[j][i] = map[i][j];
            }
        }

        check(map);
        check(reverse);
        System.out.println(rs);
    }

    private static void check(int[][] map) {
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            boolean sw = true;
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) cnt++;
                else if (map[i][j] + 1 == map[i][j + 1] && cnt >= L) cnt = 1;
                else if (map[i][j] - 1 == map[i][j + 1] && cnt >= 0) cnt = -L + 1;
                else {
                    sw = false;
                    break;
                }
            }
            if (sw && cnt >= 0) rs++;
        }
    }

}
