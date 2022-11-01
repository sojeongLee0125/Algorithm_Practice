package week1;

import java.util.Scanner;

public class BOJ_2979 {
    public static void main(String[] args) {
        // 트럭 1대 : 1분에 A원
        // 2대 : B원
        // 3대 : C원
        // 시간의 배열을 만들어서 도착한 시간 ~ 떠난 시간 배열의 값을 현재 주차된 차의 대수로 만든다.

        Scanner sc = new Scanner(System.in);
        int[] cnt = new int[101];
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        for (int i = 0; i < 3; i++) {
            int st = sc.nextInt();
            int ed = sc.nextInt();
            for (int j = st; j < ed; j++) {
                cnt[j]++;
            }
        }

        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (cnt[i] == 1) sum += A;
            else if (cnt[i] == 2) sum += (B * 2);
            else if (cnt[i] == 3) sum += (C * 3);
        }

        System.out.println(sum);
    }
}
