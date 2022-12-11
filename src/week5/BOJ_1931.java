package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1931 {
    static class Meet implements Comparable<Meet> {
        int st;
        int ed;
        public Meet(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }
        @Override
        public int compareTo(Meet m) {
            if (this.ed == m.ed) return this.st - m.st;
            else return this.ed - m.ed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 회의의 수

        // 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
        PriorityQueue<Meet> pq = new PriorityQueue<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Meet(s, e));
        }

        int cnt = 1;
        Meet first = pq.poll();
        int ed = first.ed;
        while (!pq.isEmpty()) {
            Meet cur = pq.poll();
            if (cur.st >= ed) {
                cnt++;
                ed = cur.ed;
            }
        }

        System.out.println(cnt);
    }
}
