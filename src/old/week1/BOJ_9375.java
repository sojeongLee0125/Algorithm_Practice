package old.week1;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_9375 {
    public static void main(String[] args) {
        // 부분집합의 갯수 구하기
        // (n+1) * (m+1) - 1(공집합)

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int n = sc.nextInt();
            int answer = 1;
            for (int j = 0; j < n; j++) {
                String str1 = sc.next();
                String str2 = sc.next();
                map.put(str2, map.getOrDefault(str2, 0) + 1);
            }
            for (int num : map.values()) {
                answer *= (num + 1);
            }
            System.out.println(answer - 1);
        }
    }
}
