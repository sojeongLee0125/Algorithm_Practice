package baekjoonStep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Step14_1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            // 출발점과 도착점의 좌표
            int stx = Integer.parseInt(st.nextToken());
            int sty = Integer.parseInt(st.nextToken());
            int edx = Integer.parseInt(st.nextToken());
            int edy = Integer.parseInt(st.nextToken());

            // 행성 갯수
            int planet = Integer.parseInt(br.readLine());
            int cnt = 0;
            while (planet-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                boolean isStartInPlanet = Math.pow(stx - x, 2) + Math.pow(sty - y, 2) < Math.pow(r, 2);
                boolean isEndInPlanet = Math.pow(edx - x, 2) + Math.pow(edy - y, 2) < Math.pow(r, 2);

                // 출발점과 도착점 모두가 행성 안에 있을 경우
                if (isStartInPlanet && isEndInPlanet) continue;
                    // 출발점이나 도착점 중 하나가 행성 안에 잇을 경우
                else if (isStartInPlanet || isEndInPlanet) {
                    cnt++;
                }
                // 두점 모두 행성 밖에 있을 경우는 안 들어가도 되므로 pass
            }
            System.out.println(cnt);
        }
    }

}
