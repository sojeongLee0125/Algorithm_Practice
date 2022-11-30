package week4;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_15926_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 문자열의 길이 1 ~ 20만
        String str = sc.next(); // 괄호로 구성된 길이 n의 문자열
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int rs = 0;
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == '(') stack.push(i);
            else {
                stack.pop();
                if (!stack.isEmpty()) {
                    rs = Math.max(rs, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        System.out.println(rs);
    }
}
