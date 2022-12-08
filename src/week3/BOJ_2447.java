package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2447 {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        makeStar(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (char[] chars : arr) {
            for (char c : chars) {
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void makeStar(int y, int x, int S, boolean chk) {
        if (chk) {
            for (int i = y; i < y + S; i++) {
                for (int j = x; j < x + S; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if (S == 1) {
            arr[y][x] = '*';
            return;
        }

        int size = S / 3;
        int idx = 0;
        for (int i = y; i < y + S; i += size) {
            for (int j = x; j < x + S; j += size) {
                idx++;
                if (idx == 5) {
                    makeStar(i, j, size, true);
                } else {
                    makeStar(i, j, size, false);
                }
            }
        }
    }
}