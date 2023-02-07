package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1911 {
    // N (1 <= N <= 10,000) 개의 물웅덩이
    // 물웅덩이를 덮을 수 있는 길이 L (L은 양의 정수) 짜리 널빤지들
    // 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수

    static class Water implements Comparable<Water> {
        int start;
        int end;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Water w) {
            if (this.start == w.start) return this.end - w.end;
            else return this.start - w.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ArrayList<Water> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Water(start, end));
        }

        list.sort(Water::compareTo);

        int endPoint = 0;
        int result = 0;

        for (int i = 0; i < N; i++) {
            Water w = list.get(i);
            int cnt = 0;

            // 시작점이 현재 널빤지 범위 안에 들어오는 경우
            if (w.end <= endPoint) continue;

            // 시작점이 현재 널빤지 범위를 넘는 경우
            if (endPoint < w.start) {
                int a = (w.end - w.start) % L == 0 ? 0 : 1;
                cnt = ((w.end - w.start) / L) + a;
                endPoint = w.start + (cnt * L);
                // 시작점이 현재 널빤지 범위 안인경우
            } else {
                int a = (w.end - endPoint) % L == 0 ? 0 : 1;
                cnt = ((w.end - endPoint) / L + a);
                endPoint = endPoint + (cnt * L);
            }

            result += cnt;
        }

        System.out.println(result);
    }
}
