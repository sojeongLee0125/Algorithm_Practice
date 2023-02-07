package old.week1;

import java.util.Scanner;

public class BOJ_1629 {
    public static long recur(int a, int b, int c) {
        if (b == 1) return a % c;
        long result = recur(a, b / 2, c);
        result = (result * result) % c;
        if (b % 2 == 1) result = (result * a) % c;
        return result;
    }

    public static void main(String[] args) {
        // 재귀함수로 푼다.
        // 2^8 = 2^4 * 2^4 = 2^2 * 2^2 = 2^1 * 2^1
        // 2^7 = 2 * 2^6 = 2^3 * 2^3
        // 탈출 조건 곱하는 수가 1 이면 A 리턴한다.
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        long answer = recur(A, B, C);
        System.out.println(answer);
    }
}
