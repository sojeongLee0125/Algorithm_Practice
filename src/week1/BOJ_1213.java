package week1;

import java.util.Scanner;

public class BOJ_1213 {
    public static void main(String[] args) {
        // int 배열에 알파벳 갯수를 입력받는다.
        // 오로지 홀수개가 1개여야 가능하다. 홀수개가 2개이상이면 실패
        // 홀수개 알파벳을 가운데에 넣는다.
        // 가운데부터 양옆에 한개씩 붙여나간다.(이때 역순으로 붙인다.)
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String answer = "";
        int[] arr = new int[26];

        for (char c : str.toCharArray()) {
            arr[c - 'A']++;
        }

        int cnt = 0;
        char mid = '0';
        for (int i = 25; i >= 0; i--) {
            if (arr[i] == 0) continue;
            if (cnt > 1) break;
            if (arr[i] % 2 == 1) {
                cnt++;
                mid = (char) (i + 'A');
                arr[i]--;
            }
            if (arr[i] % 2 == 0) {
                for (int j = 0; j < arr[i]; j += 2) {
                    answer = answer + (char) (i + 'A');
                    answer = (char) (i + 'A') + answer;
                }
            }
        }

        StringBuilder sb = new StringBuilder(answer);
        if (mid != '0') sb.insert(answer.length() / 2, mid);
        if (cnt > 1) System.out.println("I'm Sorry Hansoo");
        else System.out.println(sb);
    }
}
