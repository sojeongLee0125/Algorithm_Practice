package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_14729 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 8 - 1000만
        PriorityQueue<Float> pq = new PriorityQueue<>(Comparator.reverseOrder());

        while (N-- > 0) {
            float f = Float.parseFloat(br.readLine());
            if (pq.size() == 7) {
                pq.offer(f);
                pq.poll();
            } else pq.offer(f);
        }

        ArrayList<Float> answer = new ArrayList<>(pq);
        answer.sort(Float::compareTo);

        for (Float a : answer) {
            System.out.printf("%.3f", a);
            System.out.println();
        }
    }
}
