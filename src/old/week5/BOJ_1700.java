package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1700 {
    // 사용하고 있는 전기용품의 사용순서가 주어진다.
    // 이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 갯수 (1 - 100)
        int K = Integer.parseInt(st.nextToken()); // 전기용품 총 사용횟수 (1 - 100)

        int[] arr = new int[105]; // 전기용품 사용 순서 저장
        int[] chk = new int[105]; // 사용여부 체크 배열

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // K 이하의 자연수
        }

        ArrayList<Integer> array = new ArrayList<>(); // 콘센트에 꽂혀있는 전기용품 저장
        int cnt = 0;

        for (int i = 0; i < K; i++) {
            if (chk[arr[i]] == 0) {
                // 현재 콘센트가 꽉 차 있을 경우
                if (array.size() == N) {
                    int last_idx = 0;
                    int last_num = 0;
                    // 현재 꽂혀있는 전기용품 순서대로 조회
                    for (int on : array) {
                        int cur_idx = Integer.MAX_VALUE;
                        for (int j = i + 1; j < K; j++) {
                            if (on == arr[j]) {
                                // 현재 꽂혀있는 전기용품의 제일 처음 등장하는 다음 순서
                                cur_idx = j;
                                break;
                            }
                        }
                        if (last_idx < cur_idx) {
                            last_idx = cur_idx;
                            last_num = on;
                        }
                    }
                    // 가장 마지막에 참조되는 전기용품 제거
                    chk[last_num] = 0;
                    cnt++;
                    array.remove(array.indexOf(last_num));
                }
                // 현재 콘센트에 꽂을 수 있는 경우
                array.add(arr[i]);
                chk[arr[i]] = 1;
            }
        }
        System.out.println(cnt);
    }
}
