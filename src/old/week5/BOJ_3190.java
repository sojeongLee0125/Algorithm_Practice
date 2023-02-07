package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190 {
    // 조건
    // 1. 사과를 먹으면 뱀 길이가 늘어난다.
    // 2. 벽 또는 자기 자신의 몸과 부딪히면 게임이 끝난다.
    // 3. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1, 뱀은 처음에 오른쪽을 향한다.
    // 4. 뱀은 매 초마다 이동
    //  - 몸길이를 늘려 머리를 다음칸에 위치
    //  - 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
    //  - 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 몸길이는 변하지 않는다.

    // 문제
    // 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.

    static int N, K, L, idx;
    static int[][] board;
    static int[][] chk;
    static Deque<int[]> dq = new ArrayDeque<>();
    static ArrayList<int[]> time = new ArrayList<>();

    //                 위  오  아  왼
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100)
        board = new int[N][N];
        chk = new int[N][N];

        K = Integer.parseInt(br.readLine()); // 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y - 1][x - 1] = 1;
        }

        L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            // 오른쪽일 경우 방향 인덱스 + 1을 해준다.
            if (C == 'D') time.add(new int[]{X, 1});
                // 왼쪽일 경우 방향 인덱스 + 3을 해준다.
            else time.add(new int[]{X, 3});
        }

        dq.offer(new int[]{0, 0});
        int cnt = 0;
        int dir = 1;
        while (!dq.isEmpty()) {
            cnt++;
            int[] cur = dq.peekFirst();
            int ny = cur[0] + dy[dir];
            int nx = cur[1] + dx[dir];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || chk[ny][nx] != 0) break;
            // 사과가 없는 경우
            if (board[ny][nx] == 0) {
                int[] p = dq.pollLast();
                chk[p[0]][p[1]] = 0;
            } else {
                // 사과가 있는 경우
                board[ny][nx] = 0;
            }

            chk[ny][nx] = 1;
            dq.offerFirst(new int[]{ny, nx});

            // 방향 전환하는 경우
            if (cnt == time.get(idx)[0]) {
                dir = (dir + time.get(idx++)[1]) % 4;
                idx = Math.min(idx, time.size() - 1);
            }
        }

        System.out.println(cnt);
    }
}
