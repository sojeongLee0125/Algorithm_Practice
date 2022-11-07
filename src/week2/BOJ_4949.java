package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4949 {
    public static void main(String[] args) throws IOException {
        // 괄호가 짝을 이루는 지 검사하는 프로그램
        // () 짝과 []짝
        // ()일 경우와 [] 일 경우 stack 에 넣어서 검사한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equals(".")) break;
            Stack<Character> stack = new Stack<>();
            for (char c : input.toCharArray()) {
                if (c == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else stack.push(c);
                } else if (c == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else stack.push(c);
                } else if (c == '(' || c == '[') {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) System.out.println("yes");
            else System.out.println("no");
        }
    }
}
