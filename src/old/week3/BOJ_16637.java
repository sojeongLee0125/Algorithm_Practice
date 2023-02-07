package old.week3;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_16637 {
    static int N;
    static int max = Integer.MIN_VALUE;
    static ArrayList<Character> op = new ArrayList<>();
    static ArrayList<Integer> nums = new ArrayList<>();

    public static int calc(char o, int a, int b) {
        if (o == '+') return a + b;
        else if (o == '-') return a - b;
        else return a * b;
    }

    public static void recur(int cur, int sum) {
        if (cur == (nums.size() - 1)) {
            max = Math.max(max, sum);
            return;
        }

        recur(cur + 1, calc(op.get(cur), sum, nums.get(cur + 1)));

        if (cur + 2 <= (nums.size() - 1)) {
            int tmp = calc(op.get(cur + 1), nums.get(cur + 1), nums.get(cur + 2));
            recur(cur + 2, calc(op.get(cur), sum, tmp));
        }
    }

    public static void main(String[] args) {
        // (를 적절히 추가해서 얻을 수 있는 결과의 최대값
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        String str = sc.next();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) nums.add(str.charAt(i) - '0');
            else op.add(str.charAt(i));
        }

        recur(0, nums.get(0));
        System.out.println(max);
    }
}
