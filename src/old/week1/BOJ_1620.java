package old.week1;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_1620 {
    public static void main(String[] args) {
        // 해당 문자를 순서대로 순번 저장 - hashMap<String, String>

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        HashMap<String, String> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String str = sc.next();
            map.put(str, String.valueOf(i));
            map.put(String.valueOf(i), str);
        }

        for (int i = 1; i <= M; i++) {
            String key = sc.next();
            System.out.println(map.get(key));
        }

    }
}
