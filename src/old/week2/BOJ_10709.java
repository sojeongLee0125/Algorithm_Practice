package old.week2;

import java.util.Scanner;

public class BOJ_10709 {
    public static int[][] map;
    public static int H, W;

    public static void main(String[] args) {
        // 가로행 세로열을 입력받는다.
        // 구름의 위치를 입력받는다.
        // 구름의 현재 위치는 0, 구름의 현재 위치에서 동쪽으로 한 칸씩 갈때마다 +1, 그 외는 -1
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            String str = sc.next();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) == 'c') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0) {
                    int cnt = 1;
                    while (j + 1 < W && map[i][j + 1] == -1) {
                        map[i][j + 1] = cnt++;
                        j++;
                    }
                }
            }
        }

        for (int[] rows : map) {
            for (int i : rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
