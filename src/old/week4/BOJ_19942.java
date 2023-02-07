package old.week4;

import java.util.*;

public class BOJ_19942 {
    static int N, mp, mf, ms, mv, min = Integer.MAX_VALUE;
    static int[][] map;
    static PriorityQueue<String> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][5];

        mp = sc.nextInt();
        mf = sc.nextInt();
        ms = sc.nextInt();
        mv = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 6개의 총 경우의 수만큼 골라서 해당 경우의 수에서 최소의 비용을 고른다.
        for (int i = 1; i < (1 << N); i++) {
            int p = 0;
            int f = 0;
            int s = 0;
            int v = 0;
            int price = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(j + 1 + " ");
                    p += map[j][0];
                    f += map[j][1];
                    s += map[j][2];
                    v += map[j][3];
                    price += map[j][4];
                }
            }
            if (p >= mp && f >= mf && s >= ms && v >= mv) {
                if (min > price) {
                    pq.clear();
                    min = price;
                    pq.add(sb.toString());
                } else if (min == price) {
                    pq.add(sb.toString());
                }
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(min);
            System.out.println(pq.poll());
        }
    }
}
