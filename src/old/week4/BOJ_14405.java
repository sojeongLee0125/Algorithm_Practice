package old.week4;

import java.util.Scanner;

public class BOJ_14405 {
    public static void main(String[] args) {
        // 피카츄는 "pi", "ka", "chu"를 발음, 이 세 음절을 합친 단어만 발음
        // 문자열 S가 주어졌을 때, 피카츄가 발음할 수 있는 문자열인지 아닌지 구하는 프로그램
        Scanner sc = new Scanner(System.in);
        String S = sc.next(); // 알파벳 소문자로 이루어진 문자열이며, 길이는 5000을 넘지 않는다.
        boolean flag = true;

        for (int i = 0; i < S.length(); i++) {
            if (i < S.length() - 1 && (S.substring(i, i + 2).equals("pi") || S.substring(i, i + 2).equals("ka"))) {
                i++;
            } else if (i < S.length() - 2 && (S.substring(i, i + 3).equals("chu"))) {
                i += 2;
            } else {
                flag = false;
            }
        }
        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }
}
