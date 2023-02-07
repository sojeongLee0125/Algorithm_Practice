package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {
    // 각 보석은 무게 Mi와 가격 Vi
    // 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci, 가방에는 최대 한 개의 보석
    // 훔칠 수 있는 보석의 최대 가격
    static class Jewel implements Comparable<Jewel> {
        int m;
        int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.m - o.m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 총 보석의 갯수 1 - 30만
        int K = Integer.parseInt(st.nextToken()); // 가방의 갯수 1 - 30만

        ArrayList<Jewel> jewels = new ArrayList<>();
        ArrayList<Integer> bag = new ArrayList<>();

        // 보석 정보 M / V 최대 100만
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }

        jewels.sort(Jewel::compareTo);

        // 가방 정보 C 최대 10억
        for (int i = 0; i < K; i++) {
            int b = Integer.parseInt(br.readLine());
            bag.add(b);
        }

        bag.sort(Integer::compareTo);

        Long answer = 0L;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewels.get(j).m <= bag.get(i)) {
                pq.offer(jewels.get(j++).v);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}