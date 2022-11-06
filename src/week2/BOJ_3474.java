package week2;

import java.util.Scanner;

public class BOJ_3474 {
    public static void main(String[] args) {
        // 테스트케이스 : n을 입력받아서 n!의 0의 갯수
        // 1. 2의 배수나 5의 배수가 등장하면 해당 수에서 2의 갯수 와 5의 갯수를 카운트 한다.
        // 2. 2의 배수와 5의 배수 중 작은 수가 10의 갯수 = > 5의 배수가 무조건 작다
        // 3. 25! 안에서 5의 갯수 : 5로 나눈 몫 + 25로 나눈 몫
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (0 < T--) {
            int N = sc.nextInt();
            int cnt = 0;
            for (int i = 5; i <= N; i *= 5) {
                cnt += N / i;
            }
            System.out.println(cnt);
        }
    }
}
