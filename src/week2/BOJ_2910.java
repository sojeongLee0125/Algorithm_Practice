package week2;

import java.util.*;

public class BOJ_2910 {
    public static class Pair implements Comparable<Pair> {
        int val;
        int fr;
        int or;

        public Pair(int val, int fr, int or) {
            this.val = val;
            this.fr = fr;
            this.or = or;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.fr == o.fr) {
                return this.or - o.or;
            } else {
                return o.fr - this.fr;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> orMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int tmp = sc.nextInt();
            if (map.getOrDefault(tmp, 0) == 0) {
                // 처음 등장하는 수의 경우
                map.put(tmp, 1);
                orMap.put(tmp, i + 1);
            } else {
                map.put(tmp, map.get(tmp) + 1);
            }
        }

        ArrayList<Pair> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new Pair(entry.getKey(), entry.getValue(), orMap.get(entry.getKey())));
        }

        list.sort(Pair::compareTo);

        for (Pair pair : list) {
            for (int i = 0; i < pair.fr; i++) {
                System.out.print(pair.val + " ");
            }
        }

    }
}
