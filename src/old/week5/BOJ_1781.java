package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1781 {
    // N개의 문제를 주고서, 각각의 문제를 풀었을 때 컵라면을 몇 개 줄 것
    // 문제를 푸는데는 단위 시간 1이 걸리며, 각 문제의 데드라인은 N이하의 자연수
    // 받을 수 있는 컵라면 수 최대로 받을 수 있는 컵라면 수는 모두 2^31 이하 자연수

    static class Question implements Comparable<Question> {
        int deadline;
        int cup;

        public Question(int deadline, int cup) {
            this.deadline = deadline;
            this.cup = cup;
        }

        @Override
        public int compareTo(Question o) {
            if (this.deadline == o.deadline) return this.cup - o.cup;
            else return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 숙제의 갯수 (1 - 20만)
        ArrayList<Question> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Question(d, c));
        }
        list.sort(Question::compareTo);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            Question cur = list.get(i);
            cnt += cur.cup;
            pq.offer(cur.cup);

            if (pq.size() > cur.deadline) {
                cnt -= pq.poll();
            }
        }

        System.out.println(cnt);
    }
}
