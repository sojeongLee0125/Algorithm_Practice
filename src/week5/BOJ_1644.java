package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1644 {
    // 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 자연수
        boolean[] prime = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            if (prime[i]) continue;
            for (int j = i + i; j <= N; j = j + i) {
                prime[j] = true;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            if (!prime[i]) list.add(i);
        }

        int lt = 0;
        int rt = 0;
        int cnt = 0;
        int sum = 0;
        while (true) {
            if (sum >= N) {
                sum -= list.get(lt++);
            } else if (rt == list.size()) {
                break;
            } else if (sum < N) {
                sum += list.get(rt++);
            }
            if (sum == N) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
