package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2109 {
    static class Lecture implements Comparable<Lecture> {
        int day;
        int pay;

        public Lecture(int day, int pay) {
            this.day = day;
            this.pay = pay;
        }

        // 날짜순, 페이순 오름차순 정렬
        @Override
        public int compareTo(Lecture o) {
            if (this.day == o.day) return this.pay - o.pay;
            return this.day - o.day;
        }
    }

    public static void main(String[] args) throws IOException {
        // d(1 ≤ d ≤ 10,000)일 안에 와서 강연을 해 주면 p(1 ≤ p ≤ 10,000)만큼의 강연료
        // 하루에 최대 한 곳에서만 강연
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Lecture> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine()); // 대학의 수 0 - 10000

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Lecture(d, p));
        }

        list.sort(Lecture::compareTo);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            Lecture c = list.get(i);
            pq.offer(c.pay);

            if (pq.size() > c.day) {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
