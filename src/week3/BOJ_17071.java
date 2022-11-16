package week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17071 {
    static int MAX = 500000;
    static int[][] chk = new int[2][500004];
    static int N, K, time, sw;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        if (N == K) System.out.println(0);
        else {
            Queue<Integer> q = new LinkedList<>();
            chk[0][N] = 1;
            q.offer(N);
            time = 1;
            while (!q.isEmpty()) {
                K += time;
                if (K > MAX) break;
                if (chk[time % 2][K] != 0) {
                    sw = 1;
                    break;
                }
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int c = q.poll();
                    int[] arr = {c + 1, c - 1, c * 2};
                    for (int n : arr) {
                        if (0 > n || n > MAX || chk[time % 2][n] != 0) continue;
                        chk[time % 2][n] = chk[(time + 1) % 2][c] + 1;
                        if (n == K) {
                            sw = 1;
                            break;
                        }
                        q.offer(n);
                    }
                    if (sw != 0) break;
                }
                if (sw != 0) break;
                time++;
            }
            if (sw == 1) System.out.println(time);
            else System.out.println("-1");
        }
    }
}
