package week4;

import java.util.Scanner;

public class BOJ_14391 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 세로 크기
        M = sc.nextInt(); // 가로 크기

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 종이를 적절히 잘라서 조각의 합을 최대
        // 0은 가로 1은 세로로 정의한다.
        int result = 0;
        // 모든 경우의 수 나누기
        for (int s = 0; s < (1 << (N * M)); s++) {
            // 현재 경우의 수의 합
            int sum = 0;
            // 가로(0) 먼저 계산
            for (int i = 0; i < N; i++) {
                int cur = 0;
                for (int j = 0; j < M; j++) {
                    int idx = (i * M) + j;
                    if ((s & (1 << idx)) == 0) {
                        cur = cur * 10 + map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                // 마지막 결과 합산
                sum += cur;
            }
            // 세로(1) 계산
            for (int j = 0; j < M; j++) {
                int cur = 0;
                for (int i = 0; i < N; i++) {
                    int idx = (i * M) + j;
                    if ((s & (1 << idx)) != 0) {
                        cur = cur * 10 + map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            // 가로 세로 총 합 갱신
            result = Math.max(result, sum);
        }
        System.out.println(result);


    }
}
