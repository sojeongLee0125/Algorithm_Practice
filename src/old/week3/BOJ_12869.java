package old.week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12869 {
    static int N;
    static int[] scv;
    static int[][][] chk = new int[62][62][62];
    static int[][] nums = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};

    public static int solve(int a, int b, int c) {
        chk[a][b][c] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, b, c});
        // 0 0 0 에 도달하는 최단 경로 - BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int A = cur[0];
            int B = cur[1];
            int C = cur[2];
            // scv 유닛이 모두 0이 되었을 때 break
            if (chk[0][0][0] > 0) break;
            // 점수 조합으로 공격
            for (int i = 0; i < 6; i++) {
                // 음수 방지
                int na = Math.max(0, A - nums[i][0]);
                int nb = Math.max(0, B - nums[i][1]);
                int nc = Math.max(0, C - nums[i][2]);
                if (chk[na][nb][nc] > 0) continue;
                chk[na][nb][nc] = chk[A][B][C] + 1;
                q.offer(new int[]{na, nb, nc});
            }
        }

        return chk[0][0][0] - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        scv = new int[3];
        for (int i = 0; i < N; i++) {
            scv[i] = sc.nextInt();
        }
        System.out.println(solve(scv[0], scv[1], scv[2]));

    }
}
