package old.week1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2309 {
    public static void main(String[] args) {
        // 아홉 난쟁이 의 합  - 100 : 거짓 난쟁이 2명의 합
        // 해당 합이 되는 2명을 고르는 조합

        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        for (int i = 0; i < 9; i++) {
            boolean sw = false;
            for (int j = 0; j < i; j++) {
                if (arr[i] + arr[j] == (sum - 100)) {
                    arr[i] = arr[j] = 100;
                    sw = true;
                    break;
                }
            }
            if (sw) break;
        }

        Arrays.sort(arr);

        for (int i = 0; i < 7; i++) {
            System.out.println(arr[i]);
        }
    }
}
