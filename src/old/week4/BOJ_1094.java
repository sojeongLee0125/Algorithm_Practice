package old.week4;

import java.util.Scanner;

public class BOJ_1094 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int cnt = 1;
        while (X != 1) {
            if ((X & 1) != 0) cnt++;
            X /= 2;
        }
        System.out.println(cnt);

    }
}
