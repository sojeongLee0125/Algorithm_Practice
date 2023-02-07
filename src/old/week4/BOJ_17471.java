package old.week4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_17471 {
    static int N, min = Integer.MAX_VALUE;
    static int[] pop;
    static int[] make;
    static int[] chk;
    static ArrayList<Integer>[] map;


    public static void main(String[] args) throws IOException {
        // 구역은 1번부터 N번, 구역을 두 개의 선거구로 나눈다.
        // 선거구는 구역을 적어도 하나 포함, 한 선거구에 포함되어 있는 구역은 모두 연결
        // 두 선거구에 포함된 인구의 차이를 최소, 인구 차이의 최솟값

        // N개를 각각 1과 0으로 나눈다. 1 ~ 1<< N 까지의 경우의 수가 발생
        // 각 선거구가 연결되어 있는지 검사하고
        // 연결되어 있다면 인구수 차이의 최솟값을 갱신한다.

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 구역의 개수

        pop = new int[N + 1]; // 구역의 인구수
        for (int i = 1; i <= N; i++) {
            pop[i] = sc.nextInt();
        }

        // 지도 만들기
        map = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) {
                map[i].add(sc.nextInt());
            }
        }

        // 모두 0 인 경우와 모두 1인 경우는 제외
        for (int i = 1; i < (1 << N) - 1; i++) {
            make = new int[11];
            chk = new int[11];
            int idx1 = -1;
            int idx2 = -1;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    make[j + 1] = 1;
                    idx1 = j + 1;
                } else idx2 = j + 1;
            }
            int[] answer1 = dfs(idx1, 1);
            int[] answer2 = dfs(idx2, 0);
            if (answer1[0] + answer2[0] == N) {
                min = Math.min(min, Math.abs(answer1[1] - answer2[1]));
            }
        }

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static int[] dfs(int cur, int val) {
        chk[cur] = 1;
        int[] rs = {1, pop[cur]};
        for (int nx : map[cur]) {
            if (make[nx] != val) continue;
            if (chk[nx] == 1) continue;
            int[] tmp = dfs(nx, val);
            rs[0] += tmp[0];
            rs[1] += tmp[1];
        }
        return rs;
    }


}
