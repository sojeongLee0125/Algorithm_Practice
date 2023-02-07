package old.week2;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1068 {
    static int N, root, remove;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static int dfs(int num) {
        int cnt = 0;
        int child = 0;
        for (int n : tree.get(num)) {
            if (n == remove) continue;
            cnt += dfs(n);
            child++;
        }

        if (child == 0) return 1;
        return cnt;
    }

    public static void main(String[] args) {
        // 트리가 주어지고, 지울 노드가 주어진다.
        // 해당 노드가 지워지고 난 뒤 남는 리프노드의 갯수
        // ArrayList<ArrayList> 로 트리를 구성한다.
        // 트리탐색하는데 remove는 제외하고 트리탐색 실시
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 트리 초기화 구성
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int tmp = sc.nextInt();
            if (tmp == -1) root = i;
            else {
                tree.get(tmp).add(i);
            }
        }

        remove = sc.nextInt();

        // 탐색
        if (remove == root) System.out.println(0);
        else {
            System.out.println(dfs(root));
        }


    }
}
