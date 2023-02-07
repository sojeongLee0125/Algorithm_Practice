package old.week4;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_3015 {
    public static void main(String[] args) {
        // N명이 한 줄, 서로 볼 수 있는 쌍의 수
        // 두 사람 사이에 A 또는 B보다 키가 큰 사람이 없어야 한다.
        // 1. 오름차순(n-1) : 자기 옆만 쌍
        // 2. 내림차순(n-1) : 자기 옆만 쌍
        // 3. 높은 사람 등장 시 이후는 리셋하고 새로 시작
        // 4. 모든 사람의 키가 같은 경우 (50만 x 50만의 경우의 수 고려하기)

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 기다리는 사람의 수 (1 ~ 50만)
        long rs = 0; // 쌍의 수 : 최대 50만 * 50만
        Stack<long[]> stack = new Stack<>(); // {키, 카운트}

        for (int i = 0; i < N; i++) {
            long tmp = sc.nextLong(); // 현재 키
            long cnt = 1; // 기본값 1

            // 현재 peek 보다 크거나 같은 경우 계산을 시작.
            while (!stack.isEmpty() && stack.peek()[0] <= tmp) {
                rs += stack.peek()[1]; // 앞의 값들이 가진 cnt 더한다.(쌍을 짓는다.)

                // 높이가 같을 경우 cnt 증가(같은 높이는 뒤의 값 cnt 증가하고 앞의 같은 높이는 pop)
                // 그 외에는 cnt 1.
                if (stack.peek()[0] == tmp) {
                    cnt = stack.peek()[1] + 1;
                } else {
                    cnt = 1;
                }

                stack.pop();
            }

            // 내림차순일 경우 카운트 되지 않으므로 추가한다.
            if (!stack.isEmpty()) rs++;

            // 입력된 키 값과 cnt 값을 같이 담아 놓는다.
            stack.push(new long[]{tmp, cnt});
        }

        System.out.println(rs);
    }
}