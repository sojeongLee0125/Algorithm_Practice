package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
    // N개의 수로 이루어진 수열 A1, A2, ..., AN, 주어진 수의 순서를 바꾸면 안 된다.
    // 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷) - 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자
    // 연산자 우선 순위를 무시하고 앞에서부터 진행, 나눗셈은 정수 나눗셈으로 몫
    // 음수를 양수로 나눌 때  양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것
    // N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것

    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static int[] nums;
    static int[] ops = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수의 개수 N(2 ≤ N ≤ 11)
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken()); //  A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken()); //  덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
        }

        dfs(0, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int cnt, int sum) {
        if (cnt == N - 1) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        } else {
            for (int i = 0; i < 4; i++) {
                if (ops[i] == 0) continue;
                ops[i]--;
                dfs(cnt + 1, calc(i, cnt, sum));
                ops[i]++;
            }
        }
    }

    private static int calc(int idx, int cnt, int sum) {
        if (idx == 0) {
            sum += nums[cnt + 1];
        } else if (idx == 1) {
            sum -= nums[cnt + 1];
        } else if (idx == 2) {
            sum *= nums[cnt + 1];
        } else {
            sum /= nums[cnt + 1];
        }
        return sum;
    }
}
