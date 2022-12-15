package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14469 {
    public static class Cow implements Comparable<Cow> {
        int arr;
        int chk;

        public Cow(int arr, int chk) {
            this.arr = arr;
            this.chk = chk;
        }

        @Override
        public int compareTo(Cow o) {
            return this.arr - o.arr;
        }

    }

    public static void main(String[] args) throws IOException {
        // 1. N마리 소
        // 2. 도착한 시간과 검문받는 데 걸리는 시간은 소마다 다르다. (물론 같을 수도 있다.)
        // 3. 두 소가 동시에 검문을 받을 수는 없다.
        // 모든 소가 농장에 입장하는데 걸리는 시간

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N마리 소 (1-100)
        PriorityQueue<Cow> pq = new PriorityQueue<>();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int arrive = Integer.parseInt(st.nextToken()); // 1- 100만
            int chk = Integer.parseInt(st.nextToken()); // 1- 100만
            pq.offer(new Cow(arrive, chk));
        }

        int time = 0;
        while (!pq.isEmpty()) {
            Cow cow = pq.poll();
            time = Math.max(time, cow.arr) + cow.chk;
        }

        System.out.println(time);
    }
}
