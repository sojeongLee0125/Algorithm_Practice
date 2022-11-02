package week1;

import java.util.Scanner;

public class BOJ_2559 {
    public static void main(String[] args) {
        // 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 계산하는 프로그램을 작성
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N + 1];
        int[] sumArr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
            sumArr[i] = arr[i] + sumArr[i - 1];
        }

        int max = Integer.MIN_VALUE;
        for (int i = K; i <= N; i++) {
            max = Math.max(sumArr[i] - sumArr[i - K], max);
        }

        System.out.println(max);
    }

}
