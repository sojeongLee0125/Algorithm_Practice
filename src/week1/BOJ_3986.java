package week1;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_3986 {
    public static void main(String[] args) {
        // stack 에 넣어서 최종적으로 stack에 남은 요소가 있는지 체크하는 문제
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) {
                // 스택이 비어있지 않고, peek이 현재요소와 같으면 pop / 그 외에는 push
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) cnt++;
        }
        System.out.println(cnt);
    }
}
