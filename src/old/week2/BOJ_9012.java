package old.week2;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_9012 {
    public static void main(String[] args) {
        // 입력값으로 () 문자열이 주어진다.
        // 올바른 () 문자열 일 경우 "YES" 아니면 "NO" 출력
        // 스택을 활용한다.
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            Stack<Character> stack = new Stack<>();
            String str = sc.next();
            for (char c : str.toCharArray()) {
                if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
