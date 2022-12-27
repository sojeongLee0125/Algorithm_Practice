package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17825 {
    // 1. 시작 칸에 말 4개가 있다.
    // 2. 말은 게임판에 그려진 화살표의 방향대로만 이동할 수 있다.
    // 3. 게임은 10개의 턴으로 이루어진다.
    // 4. 턴마다 1부터 5까지 한 면에 하나씩 적혀있는 5면체 주사위를 굴리고,
    //    도착 칸에 있지 않은 말을 하나 골라 주사위에 나온 수만큼 이동시킨다.
    // 5. 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
    // 6. 말이 이동을 마칠 때마다 칸에 적혀있는 수가 점수에 추가된다.
    // 주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 최댓값

    // 1. 맵 정보를 arrayList로 만든다.
    // 2. 10개의 수를 dfs로 선택하면서 10개를 선택할 때마다 최댓값을 구한다. (dfs(cnt = 0, score = 0))
    // 3. 각 말이 있는 위치의 인덱스 정보를 저장해서 해당 인덱스에 말이 없으면 도착한다.

    static int[] dice = new int[10];
    static int[] mal = new int[5];
    static ArrayList<Integer>[] map = new ArrayList[105];
    static Map<Integer, Integer> score = new HashMap<>();

    private static void makeMap() {
        // 연결 정보
        for (int i = 0; i <= 100; i++) {
            map[i] = new ArrayList<>();
        }

        // 직진 코스 연결 정보 + 점수 정보 저장
        for (int i = 0; i < 20; i++) {
            map[i].add(i + 1);
            score.put(i, i * 2);
        }

        // 마지막점 연결정보 저장
        score.put(20, 40);
        score.put(100, 0);
        map[20].add(100);

        // 교차로 코스 정보 저장
        score.put(21, 13);
        score.put(22, 16);
        score.put(23, 19);
        score.put(24, 25);
        score.put(25, 22);
        score.put(26, 24);
        score.put(27, 28);
        score.put(28, 27);
        score.put(29, 26);
        score.put(30, 30);
        score.put(31, 35);

        // 10 - 13 - 16 - 19 - 25
        map[5].add(21);
        map[21].add(22);
        map[22].add(23);
        map[23].add(24);

        // 20 - 22 - 24 -25
        map[10].add(25);
        map[25].add(26);
        map[26].add(24);

        // 30 - 28 - 27 -26
        map[15].add(27);
        map[27].add(28);
        map[28].add(29);
        map[29].add(24);

        // 24 -30 -31 - 20
        map[24].add(30);
        map[30].add(31);
        map[31].add(20);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // 윷놀이 맵 정보 만들기
        makeMap();

        // 게임 시작
        System.out.println(game(0));
    }

    private static int game(int cnt) {
        int s = 0;
        if (cnt == 10) return s;
        for (int i = 0; i < 4; i++) {
            int curIdx = mal[i];
            int nextIdx = move(curIdx, dice[cnt]);
            if (isAlready(nextIdx, i)) continue;
            mal[i] = nextIdx;
            s = Math.max(s, game(cnt + 1) + score.get(nextIdx));
            mal[i] = curIdx;
        }
        return s;
    }

    private static boolean isAlready(int nextIdx, int malIdx) {
        if (nextIdx == 100) return false;
        for (int i = 0; i < 4; i++) {
            if (malIdx == i) continue;
            if (mal[i] == nextIdx) return true;
        }
        return false;
    }

    private static int move(int curIdx, int dice) {
        if (curIdx == 100) return 100;

        if (map[curIdx].size() > 1) {
            curIdx = map[curIdx].get(1);
            dice--;
        }

        if (dice > 0) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(curIdx);
            int next = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                next = map[cur].get(0);
                if (next == 100) break;
                q.offer(next);
                dice--;
                if (dice == 0) break;
            }
            return next;
        } else return curIdx;
    }


}
