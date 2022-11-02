package week1;

import java.util.Scanner;

public class BOJ_9996 {
    public static void main(String[] args) {
        // * 인덱스를 찾아서 * 앞을 head * 뒤를 tail 만든다.
        // 입력 문자열의 head 길이 = head && (전체 - tail)길이부터 끝까지 = tail

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String pattern = sc.next();
        int idx = pattern.indexOf("*");

        String head = pattern.substring(0, idx);
        String tail = pattern.substring(idx + 1);

        for (int i = 0; i < n; i++) {
            String str = sc.next();

            if (head.length() + tail.length() > str.length()) {
                System.out.println("NE");
                continue;
            }

            String strHead = str.substring(0, head.length());
            String strTail = str.substring(str.length() - tail.length());

            if (strHead.equals(head) && strTail.equals(tail)) {
                System.out.println("DA");
            } else {
                System.out.println("NE");
            }
        }
    }
}