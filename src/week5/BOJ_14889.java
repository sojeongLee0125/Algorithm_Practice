package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889 {
    // i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji
    // 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소
    static int N, min = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // (4 ≤ N ≤ 20, N은 짝수)
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비트마스킹으로 경우의 수를 구해서 해당 경우의 수에서의 차이값 중 최소값을 갱신하는 방식
        for (int i = 1; i < (1 << N) - 1; i++) {
            if (i % 2 == 1) continue;
            ArrayList<Integer> team1 = new ArrayList<>();
            ArrayList<Integer> team2 = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    team1.add(j);
                } else team2.add(j);
            }
            if (team1.size() != team2.size()) continue;
            min = Math.min(min, cal(team1, team2));
        }

        System.out.println(min);
    }

    private static int cal(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < (N / 2); i++) {
            for (int j = 0; j < (N / 2); j++) {
                if (i == j) continue;
                sum1 += map[team1.get(i)][team1.get(j)];
                sum2 += map[team2.get(i)][team2.get(j)];
            }
        }

        return Math.abs(sum1 - sum2);
    }

}