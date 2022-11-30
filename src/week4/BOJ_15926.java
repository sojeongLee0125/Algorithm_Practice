package week4;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_15926 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 문자열의 길이 1 ~ 20만
        String str = sc.next(); // 괄호로 구성된 길이 n의 문자열
        int[] dp = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                dp[i] = dp[stack.pop()] = 1;
            }
        }

        int max = 0;
        int cnt = 0;
        for (int i : dp) {
            if (i == 1) {
                cnt++;
                max = Math.max(cnt, max);
            } else cnt = 0;
        }

        System.out.println(max);
    }
}
