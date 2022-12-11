package baekjoonStep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Step14_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            System.out.println(solution(x1, y1, r1, x2, y2, r2));
        }
    }

    private static int solution(int x1, int y1, int r1, int x2, int y2, int r2) {

        int dis_pow = (int) ((Math.pow(x2 - x1, 2)) + (Math.pow(y2 - y1, 2))); // 중점 간 거리 제곱

        // 1. 중점도 같고 반지름도 같은 경우 - 두 원이 일치
        if (x1 == x2 && y1 == y2 && r1 == r2) {
            return -1;
        }

        // 2. 두 원 반지름의 합보다 중점 간 거리가 더 먼 경우
        else if (dis_pow > Math.pow(r1 + r2, 2)) {
            return 0;
        }

        // 3. 원이 원을 포함하지만 내접하지 않는 경우 겹치지 않음
        else if (dis_pow < Math.pow(r1 - r2, 2)) {
            return 0;
        }

        // 4. 원이 원을 포함하면서 내접하는 경우 1개가 겹친다.
        else if (dis_pow == Math.pow(r1 - r2, 2)) {
            return 1;
        }

        // 5.외접할 경우 1개가 겹친다.
        else if (dis_pow == Math.pow(r1 + r2, 2)) {
            return 1;
        }

        // 그 외의 경우 2개가 겹친다.
        else return 2;
    }

}
