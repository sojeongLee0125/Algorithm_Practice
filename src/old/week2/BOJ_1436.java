package old.week2;

import java.util.Scanner;

public class BOJ_1436 {
    public static void main(String[] args) {
        // N번째로 작은 666 출력
        // N <= 10000
        // 666 부터 무한 증가하면서 666을 포함하면 N을 감소
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int i = 666;
        for (; ; i++) {
            if (String.valueOf(i).contains("666")) {
                N--;
            }
            if (N == 0) break;
        }
        System.out.println(i);
    }
}
