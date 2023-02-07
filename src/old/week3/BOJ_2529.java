package old.week3;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2529 {
    static char[] op;
    static boolean[] chk = new boolean[10];
    static ArrayList<Long> arr = new ArrayList<>();
    static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        op = new char[k];

        for (int i = 0; i < k; i++) {
            op[i] = sc.next().charAt(0);
        }

        // dfs 완전 탐색으로 최대값 최소값 구해보기
        dfs(0, "");
        arr.sort(Long::compareTo);
        String max = String.valueOf(arr.get(arr.size() - 1));
        String min = String.valueOf(arr.get(0));

        System.out.println(format(max));
        System.out.println(format(min));
    }

    private static String format(String str) {
        while (str.length() < (k + 1)) {
            str = "0" + str;
        }
        return str;
    }

    public static boolean isOk(int a, int b, char opr) {
        if (a < b && opr == '<') return true;
        if (a > b && opr == '>') return true;
        return false;
    }

    private static void dfs(int cnt, String result) {
        if (cnt == k + 1) {
            arr.add(Long.valueOf(result));
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (chk[i]) continue;
            if (cnt == 0 || isOk(result.charAt(cnt - 1) - '0', i, op[cnt - 1])) {
                chk[i] = true;
                result += i;
                dfs(cnt + 1, result);
                chk[i] = false;
                result = result.substring(0, result.length() - 1);
            }
        }
    }

}
