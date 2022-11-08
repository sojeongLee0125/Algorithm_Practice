package week2;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298 {
    public static void main(String[] args) throws IOException {
        // Ai의 오큰수: 현재 Ai 보다 오른쪽에서 Ai 보다 크면서 가장 왼쪽에 있는 수
        // 스택에 값을 넣는다.
        // 다음으로 들어오는 값이 현재 최상단보다 크다면 해당 값을 오른수로 갖는다.
        //  해당 값은 pop하고 새로들어오는 값만 push
        // 다음으로 들어오는 값이 현재 최상단보다 작다면 해당 값은 push

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                answer[stack.peek()] = nums[i];
                stack.pop();
            }
            stack.push(i);
        }

        for (int i : answer) {
            bw.write(i+" ");
        }

        bw.flush();
        bw.close();
    }
}