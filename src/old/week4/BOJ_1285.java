package old.week4;

import java.util.Scanner;

public class BOJ_1285 {
    static int N, min = Integer.MAX_VALUE;
    static int[] row;

    public static void main(String[] args) {
        // 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여
        // 뒷면이 위를 향하는 동전 개수를 최소로 하기
        // 앞면이 H 뒷면이 T
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        row = new int[N];
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            int val = 1;
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                // 한 행의 값을 하나의 숫자로 저장한다.
                if (c == 'T') row[i] |= val;
                val *= 2;
            }
        }

        sol(0);
        System.out.println(min);
    }

    private static void sol(int cur) {
        // 행의 모든 경우가 정해진 경우
        if (cur == N) {
            int sum = 0;
            // 열의 내용 중 뒤집어야 하는 갯수 구하기
            for (int i = 1; i <= (1 << (N - 1)); i = i * 2) {
                int cnt = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & row[j]) != 0) cnt++;
                }
                sum += Math.min(cnt, N - cnt);
            }
            min = Math.min(sum, min);
            return;
        }

        // 뒤집어서 재귀함수 실시
        row[cur] = ~row[cur];
        sol(cur + 1);

        // 다시 뒤집어서 원래대로 했을 경우 재귀함수 실시
        row[cur] = ~row[cur];
        sol(cur + 1);
    }
}
