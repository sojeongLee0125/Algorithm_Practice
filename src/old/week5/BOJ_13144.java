package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13144 {
    // 1. 길이가 N인 수열
    // 2. 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 수열의 길이 N (1 - 10만)
        int[] arr = new int[N + 1]; // 1 이상 100,000 이하 정수 배열
        int[] chk = new int[100005];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 사용
        long cnt = 0;
        int rt = 0;
        for (int lt = 1; lt <= N; lt++) {
            // 중복 전까지 rt 전진
            while (rt + 1 <= N && chk[arr[rt + 1]] == 0) {
                chk[arr[++rt]] = 1;
            }

            // 정답 카운팅
            cnt += (rt - lt + 1);

            // lt 체크 해제
            chk[arr[lt]] = 0;
        }

        System.out.println(cnt);
    }
}
