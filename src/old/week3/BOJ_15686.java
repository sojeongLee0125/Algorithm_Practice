package old.week3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_15686 {
    static int N, M;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Point> hm = new ArrayList<>();
    static ArrayList<Point> ck = new ArrayList<>();
    static boolean[] chk = new boolean[20];

    public static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void dfs(int idx, int cnt) {
        if (cnt == M) {
            int total = 0;
            for (int i = 0; i < hm.size(); i++) {
                int sum = Integer.MAX_VALUE;
                for (int j = 0; j < ck.size(); j++) {
                    if (chk[j]) {
                        int dist = Math.abs(hm.get(i).y - ck.get(j).y) + Math.abs(hm.get(i).x - ck.get(j).x);
                        sum = Math.min(sum, dist);
                    }
                }
                // 현재 선택된 집의 치킨거리를 더해준다.
                total += sum;
            }
            // 현재 조합에서의 치킨거리값 갱신
            min = Math.min(total, min);
            return;
        }

        // 치킨 집 고르기
        for (int i = idx; i < ck.size(); i++) {
            if (!chk[i]) {
                chk[i] = true;
                dfs(i + 1, cnt + 1);
                chk[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 집의 갯수 <= 2N && M <= 치킨집의 갯수 < 13
        // 치킨거리 : 가장 가까운 치킨집 사이의 거리
        // 0: 빈칸 / 1 : 집 / 2 : 치킨집
        // 치킨집 최대 M개를 골랐을 때(나머지는 폐업)
        // 치킨거리가 가장 작게 되도록 구하기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) hm.add(new Point(i, j));
                else if (map[i][j] == 2) ck.add(new Point(i, j));
            }
        }

        // dfs로 치킨집을 0개부터 M개씩 고르면서 치킨 거리의 최소값을 갱신
        dfs(0, 0);
        System.out.println(min);
    }
}
