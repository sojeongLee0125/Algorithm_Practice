package week2;

import java.util.Scanner;

public class BOJ_4659 {
    public static void main(String[] args) {
        // 문자열 규칙
        // 1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
        // 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
        // 3. 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.

        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.next()).equals("end")) {
            boolean sw1 = false;
            boolean sw2 = true;
            boolean sw3 = true;

            char[] arr = str.toCharArray();
            int cnt1 = 0;
            int cnt2 = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                    sw1 = true;
                    cnt1++;
                    cnt2 = 0;
                } else {
                    cnt1 = 0;
                    cnt2++;
                }

                if (i != str.length() - 1) {
                    if (arr[i] == arr[i + 1] && arr[i] != 'e' && arr[i] != 'o') {
                        sw2 = false;
                        break;
                    }
                }

                if (cnt1 >= 3 || cnt2 >= 3) {
                    sw3 = false;
                    break;
                }
            }

            if (sw1 && sw2 && sw3) {
                System.out.printf("<%s> is acceptable.", str);
            } else {
                System.out.printf("<%s> is not acceptable.", str);
            }

            System.out.println();
        }
    }
}
