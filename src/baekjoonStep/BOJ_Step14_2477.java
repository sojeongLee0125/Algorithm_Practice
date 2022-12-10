package baekjoonStep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Step14_2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // idx out 에러 해결하고 커밋 및 블로깅하기
        int N = Integer.parseInt(br.readLine()); // 참외 갯수
        int[] arr = new int[6]; // 방향별 길이 입력 배열
        int maxW = 0, maxH = 0; // 가로 세로 길이 최대값
        int maxWIdx = 0, maxHIdx = 0; // 가로 세로 길이 최대값 인덱스

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()); // 방향
            arr[i] = Integer.parseInt(st.nextToken()); // 길이

            // 가장 긴 가로길이 세로길이 및 해당 위치 인덱스
            if (d == 1 || d == 2) { // w
                maxW = Math.max(maxW, arr[i]);
                if (maxW == arr[i]) maxWIdx = i;
            } else {
                maxH = Math.max(maxH, arr[i]);
                if (maxH == arr[i]) maxHIdx = i;
            }
        }

        int rtIdx = 0, ltIdx = 0, minW = 0, minH = 0;

        // 아랫변이 최대길이 일 경우
        if (maxWIdx == 5) {
            rtIdx = 0;
            ltIdx = 4;
        } else {
            // 윗변의 길이가 최대일 경우
            rtIdx = 2;
            ltIdx = 0;
        }

        // 빈 사각형의 세로길이
        minH = Math.abs(arr[rtIdx] - arr[ltIdx]);

        // 오른쪽 높이가 최대일 경우
        if (maxHIdx == 0) {
            rtIdx = 1;
            ltIdx = 5;
        } else {
            // 왼쪽 높이가 최대일 경우
            rtIdx = maxHIdx + 1;
            ltIdx = maxHIdx - 1;
        }

        // 빈 사각형의 가로길이
        minW = Math.abs(arr[rtIdx] - arr[ltIdx]);

        int size = (maxH * maxW) - (minH * minW);
        System.out.println(size * N);

    }
}
