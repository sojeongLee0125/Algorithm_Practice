package old.week2;

import java.util.Scanner;

public class BOJ_1992 {
    static int N;
    static int[][] map;
    public static String quard(int y, int x, int n) {
        if (n == 1) return String.valueOf(map[y][x]);
        String s = String.valueOf(map[y][x]);
        String ret = "";
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (!s.equals(String.valueOf(map[i][j]))) {
                    ret += "(";
                    ret += quard(y, x, n / 2);
                    ret += quard(y, x + (n / 2), n / 2);
                    ret += quard(y + (n/2), x, n / 2);
                    ret += quard(y + (n/2), x + (n / 2), n / 2);
                    ret += ")";
                    return ret;
                }
            }
        }
        return String.valueOf(map[y][x]);
    }

    public static void main(String[] args) {
        // 1. N = 2 일때 (1111) 만든다 4개가 같으면 1로 통합한다.
        // 2. N = 4 일때 (1111)(1111)(0000)(0101) = 110(0101)
        // 3. N = 8 일때 110(0101) / 0010 / 1 / 0001
        // 재귀함수로 푼다.

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(quard(0, 0, N));
    }
}
