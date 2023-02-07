package old.week2;

import java.util.Scanner;

public class BOJ_2828 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int J = sc.nextInt();

        // 시작위치 : 1
        // 왼쪽 끝 오른쪽 끝 위치 구해서 사과 위치값과의 차이값이 이동거리
        int start = 1;
        int ret = 0;
        for (int i = 0; i < J; i++) {
            int apple = sc.nextInt();
            int right = start + M - 1;

            if (apple >= start && apple <= right) continue;
            else {
                if (apple < start) {
                    ret += (start - apple);
                    start = apple;
                } else {
                    ret += (apple - right);
                    start += (apple - right);
                }
            }
        }
        System.out.println(ret);
    }
}