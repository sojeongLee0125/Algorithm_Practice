package week2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_2870 {
    static String result;
    static ArrayList<String> list = new ArrayList<>();

    public static void solution() {
        while (true) {
            if (!result.equals("") && result.charAt(0) == '0') {
                result = result.substring(1);
            } else break;
        }
        if (result.equals("")) {
            result = "0";
        }
        list.add(result);
        result = "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            result = "";
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) < 65) {
                    // 숫자일 경우
                    result += str.charAt(j);
                } else if (!result.equals("")) {
                    // 문자를 만나서 앞의 숫자들을 정리해야 할 경우
                    solution();
                }
            }
            // 반복문 종료 후 result 값 처리
            if (!result.equals("")) solution();
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length();
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }
}