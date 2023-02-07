package old.week1;

import java.util.Scanner;

public class BOJ_1159 {
    public static void main(String[] args) {
        // 성의 첫 글자가 같은 선수 5명을 선발. 5명보다 적다면, 경기를 기권.
        // 뽑을 수 있는 성의 첫 글자를 모두 구한다.
        // 성의 첫글자를 알파벳 배열에 저장
        // 선발할 수 없는 경우 "PREDAJA"

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cnt = new int[26];
        boolean sw = false;

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            cnt[str.charAt(0) - 'a']++;
        }

        String answer = "";
        for (int i = 0; i < 26; i++) {
            if (cnt[i] >= 5) {
                sw = true;
                answer += (char) (i + 97);
            }
        }

        if (sw) System.out.println(answer);
        else System.out.println("PREDAJA");
    }
}