package week3;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_9934 {
    static int K;
    static ArrayList<Integer> rs[] = new ArrayList[15];
    static int[] arr;

    public static void main(String[] args) {
        // 중위 순회를 보고 트리를 재구성하는 문제
        // 중간 값을 찾는다. 해당 중간 값의 오른쪽 왼쪽 범위를 나눈다.
        // 길이가 1이 되면 본인의 값을 리턴한다.
        // 백트레킹으로 리턴하여 트리를 구성하는 문제
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        arr = new int[(int) Math.pow(2, K) - 1];

        for (int i = 0; i < 15; i++) {
            rs[i] = new ArrayList<>();
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        find(0, arr.length - 1, 1);

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < rs[i].size(); j++) {
                System.out.print(rs[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    private static void find(int st, int ed, int lev) {
        if (st > ed) return;
        if (st == ed) {
            rs[lev].add(arr[st]);
        } else {
            int mid = (st + ed) / 2;
            rs[lev].add(arr[mid]);
            find(st, mid - 1, lev + 1);
            find(mid + 1, ed, lev + 1);
        }
    }

}
