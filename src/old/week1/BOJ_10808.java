package old.week1;

import java.util.Scanner;

public class BOJ_10808 {
    public static void main(String[] args) {
        // 알파벳을 입력받아서 해당 알파벳  - 'a' 인덱스 값을 증가시킨다.
        // 0 에서 26까지 인덱스를 출력한다.
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] arr = new int[26];

        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }

    }
}
