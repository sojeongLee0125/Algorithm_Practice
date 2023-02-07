package old.week1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1940 {
    public static void main(String[] args) {
        // N개의 재료들의 조합으로 M이 만들어지면 cnt++

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int cnt = 0;

        if (M <= 200000) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] + arr[j] == M) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
