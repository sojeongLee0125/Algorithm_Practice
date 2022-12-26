package week5;

// 8개의 톱니 톱니바퀴 T개 일렬(왼쪽부터 1번...T번), 톱니는 N극 또는 S극 중 하나
// 총 K번 회전 /  회전은 한 칸을 기준 / 회전은 시계 방향과 반시계 방향
// 옆에 있는 톱니바퀴와 맞닿은 톱니의 극이 다르다면, 옆은 회전한 방향과 반대방향으로 회전하게 된다.
// 맞닿은 부분이 서로 같으면, 회전하지 않는다.
// 톱니바퀴 T개의 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하는 프로그램

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_15662 {
    static int T, K;
    static ArrayList<Integer>[] tops;
    static ArrayList<int[]> rolls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine()); // 톱니바퀴의 개수 T (1 ≤ T ≤ 1,000)
        tops = new ArrayList[T];

        // 톱니바퀴의 상태가 가장 왼쪽 톱니바퀴부터 순서대로 주어진다.
        // 상태는 8개의 정수로 이루어져 있고,
        // 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.

        for (int i = 0; i < T; i++) {
            tops[i] = new ArrayList<>();
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                tops[i].add(str.charAt(j) - '0');
            }
        }

        // 회전 횟수 K(1 ≤ K ≤ 1,000)가 주어진다.
        K = Integer.parseInt(br.readLine());
        rolls = new ArrayList<>();

        // 각 방법은 두 개의 정수로 이루어져 있고,
        // 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다.
        // 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.

        // 문제해결 과정
        // 1. 회전 시킬 톱니바퀴의 번호와 방향을 추출한다.
        // 2. 양 옆 톱니바퀴의 극을 비교해서 양옆의 회전 방향을 계산한다.
        // 3. 회전 시킨다.

        // 맞닿은 톱니바퀴 번호 : 왼쪽 - 6 / 오른쪽 - 2 / 12시 - 0

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 톱니 번호
            int num = Integer.parseInt(st.nextToken()) - 1;
            // 회전 방향 - 시계(t) / 반시계(f)
            boolean dir = Integer.parseInt(st.nextToken()) == 1 ? true : false;

            int lt = findLeft(num);
            int rt = findRight(num);


            // 현재 시계 방향 체크
            int chk = 0;

            // 왼쪽 회전
            for (int p = num; p >= lt; p--) {
                boolean d = chk % 2 == 0 ? dir : !dir;
                rotate(p, d);
                chk++;
            }

            // 오른쪽 회전
            chk = 1;
            for (int p = num + 1; p <= rt; p++) {
                boolean d = chk % 2 == 0 ? dir : !dir;
                rotate(p, d);
                chk++;
            }
        }

        // 총 K번 회전시킨 이후에 12시방향이 S극인 톱니바퀴의 개수를 출력
        int cnt = 0;
        for (ArrayList<Integer> top : tops) {
            if (top.get(0) == 1) cnt++;
        }
        System.out.println(cnt);
    }

    private static void rotate(int num, boolean dir) {
        // 반시계 방향
        if (!dir) {
            Collections.rotate(tops[num], -1);
        } else {
            // 시계방향
            Collections.rotate(tops[num], 1);
        }
    }

    private static int findRight(int num) {
        for (int i = num; i < T - 1; i++) {
            if (tops[i].get(2) == tops[i + 1].get(6)) return i;
        }
        return T - 1;
    }

    private static int findLeft(int num) {
        for (int i = num; i >= 1; i--) {
            if (tops[i].get(6) == tops[i - 1].get(2)) return i;
        }
        return 0;
    }
}
