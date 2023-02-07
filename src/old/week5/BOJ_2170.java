package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2170 {
    // 선을 그었을 때, 그려진 선(들)의 총 길이
    static class Line implements Comparable<Line> {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 선을 그은 횟수 1 - 100만
        ArrayList<Line> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); // 두점의 위치 -10억 ~ +10억
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Line(s, e));
        }
        list.sort(Line::compareTo);

        int lt = list.get(0).start;
        int rt = list.get(0).end;

        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (rt < list.get(i).start) {
                ans += (rt - lt);
                lt = list.get(i).start;
                rt = list.get(i).end;
            } else if (list.get(i).start <= rt && list.get(i).end >= rt) {
                // st 순으로 정렬했으니 rt만 늘리면 된다.
                rt = list.get(i).end;
            }
        }
        ans += (rt - lt);
        System.out.println(ans);
    }
}
