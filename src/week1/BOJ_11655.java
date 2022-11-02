package week1;

import java.util.Scanner;

public class BOJ_11655 {

    public static void main(String[] args) {
        // ROT13은 알파벳을 13글자씩 밀어서 만든다.
        // ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다.
        // 입력받은 문자열을 charArray 한글자씩 받아서 처리한다.

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String answer = "";

        for (char c : str.toCharArray()) {
            if (c >= 65 && c <= 90) {
                // 대문자
                if (c + 13 <= 90) answer += (char) (c + 13);
                else answer += (char) (c - 13);
            } else if (c >= 97 && c <= 122) {
                // 소문자
                if (c + 13 <= 122) answer += (char) (c + 13);
                else answer += (char) (c - 13);
            } else {
                answer += c;
            }
        }

        System.out.println(answer);
    }
}
