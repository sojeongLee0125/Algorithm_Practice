package week2;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_17136 {
    public static int[][] board = new int[10][10];
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static int rs = Integer.MAX_VALUE;

    public static boolean check(int y, int x, int size) {
        if (y + size > 10 || x + size > 10) return false;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static void put(int y, int x, int size, int value) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                board[i][j] = value;
            }
        }
    }

    public static void dfs(int y, int x, int cnt) {
        // 기존 값보다 갯수가 늘어날 경우 return
        if (cnt > rs) return;

        // x가 끝에 도달했을 경우 -> y 증가
        if (x == 10) {
            dfs(y + 1, 0, cnt);
            return;
        }

        // y가 끝에 도달했을 경우 -> rs 값 갱신
        if (y == 10) {
            rs = Math.min(rs, cnt);
            return;
        }

        // 현재 위치가 0일 경우 종이를 붙이지 않고 다음 dfs 진행
        if (board[y][x] == 0) {
            dfs(y, x + 1, cnt);
            return;
        } else {
            // 현재 위치가 0이 아닐경우 종이 붙이기
            // 색종이 사이즈 5부터 차례대로 적용
            for (int i = 5; i >= 1; i--) {
                // 색종이를 다 쓴 경우 pass
                if (map.get(i) == 5) continue;

                // 현재 위치에 현재 사이즈 색종이가 적용 가능할 경우
                if (check(y, x, i)) {
                    // 색종이 사용 갯수 증가
                    map.put(i, map.get(i) + 1);
                    // 색종이 적용부분 0으로 만들기
                    put(y, x, i, 0);
                    // 다음 dfs 실시
                    dfs(y, x + i, cnt + 1);
                    // 색종이 적용 부분 해제
                    put(y, x, i, 1);
                    // 색종이 적용 갯수 감소
                    map.put(i, map.get(i) - 1);
                }
            }
        }


    }

    public static void main(String[] args) {
        // 색종이의 종류 정사각형 크기 1, 2, 3, 4, 5 5개씩
        // 10 x 10 칸에 모든 1을 색종이로 덮기
        // 필요한 색종이읜 최소 갯수
        // 완전 탐색 + 백트래킹
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // map의 색종이 크기별 갯수 초기화
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);

        // dfs 탐색 실시
        dfs(0, 0, 0);

        if (rs == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(rs);
    }
}
