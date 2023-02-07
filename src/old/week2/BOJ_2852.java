package old.week2;

import java.util.Scanner;

public class BOJ_2852 {
    public static int cal(String[] time, String[] pre, int tsum) {
        int sum = (Integer.parseInt(time[0]) * 60) + (Integer.parseInt(time[1]));
        int prev = (Integer.parseInt(pre[0]) * 60) + (Integer.parseInt(pre[1]));
        tsum += (sum - prev);
        return tsum;
    }

    public static void main(String[] args) {
        // 입력값 : 팀 번호 + 득점 시간
        // 출력값 : 1번 팀이 이기고 있던 시간 + 2번 팀이 이기고 있던 시간
        // 1. 팀의 스코어를 기록한다.
        // 2. 현재 이기고 있는 팀의 시간을 계산해서 기록한다.
        // 3. 새로 기록된 팀의 스코어를 올린다.

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A = 0;
        int B = 0;
        int asum = 0;
        int bsum = 0;
        String preT = "00:00";

        for (int i = 0; i < N; i++) {
            int T = sc.nextInt();
            String score = sc.next();

            String[] time = score.split(":");
            String[] pre = preT.split(":");

            if (A > B) {
                asum = cal(time, pre, asum);
            } else if (B > A) {
                bsum = cal(time, pre, bsum);
            }

            if (T == 1) A++;
            else B++;

            preT = score;
        }

        // 마지막 처리
        String[] time = {"48", "00"};
        String[] pre = preT.split(":");
        if (A > B) {
            asum = cal(time, pre, asum);
        } else if (B > A) {
            bsum = cal(time, pre, bsum);
        }

        printTime(asum);
        System.out.println();
        printTime(bsum);
    }

    private static void printTime(int sum) {
        String m1 = sum / 60 >= 10 ? String.valueOf(sum / 60) : "0" + sum / 60;
        String s1 = sum % 60 >= 10 ? String.valueOf(sum % 60) : "0" + sum % 60;
        System.out.printf("%s:%s", m1, s1);
    }


}
