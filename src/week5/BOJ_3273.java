package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273 {
    // n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열 (1 - 100만)
    // 자연수 x가 주어졌을 때, ai + aj = x 만족하는 (ai, aj)쌍의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 수열의 크기 (1 -10만)
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine()); // 1- 200만

        Arrays.sort(arr);
        int lt = 0;
        int rt = n - 1;
        int cnt = 0;

        while (lt < rt) {
            int sum = arr[lt] + arr[rt];
            if (sum == x) {
                cnt++;
                rt--;
            } else if (sum < x) lt++;
            else rt--;
        }

        System.out.println(cnt);
    }
}
