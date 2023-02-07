package old.week2;

import java.util.*;

public class BOJ_17825 {
    static int[] dice = new int[10];
    static int[] mal = new int[4];
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static HashMap<Integer, Integer> map = new HashMap<>();

    // 윷놀이 판 만들기
    public static void makeMap() {
        for (int i = 0; i < 50; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i <= 19; i++) {
            list.get(i).add(i + 1);
            map.put(i, i * 2);
        }

        // 20번째 40 추가 + 마지막 도착점 추가
        map.put(20, 40);
        map.put(50, 0);
        list.get(20).add(50);

        // 중간 연결 지점들 추가
        map.put(21, 13);
        map.put(22, 16);
        map.put(23, 19);
        map.put(24, 25);
        map.put(25, 22);
        map.put(26, 24);
        map.put(27, 28);
        map.put(28, 27);
        map.put(29, 26);
        map.put(30, 30);
        map.put(31, 35);

        // 연결고리 형성
        list.get(5).add(21);
        list.get(15).add(27);
        list.get(10).add(25);
        list.get(21).add(22);
        list.get(22).add(23);
        list.get(23).add(24);
        list.get(25).add(26);
        list.get(26).add(24);
        list.get(27).add(28);
        list.get(28).add(29);
        list.get(29).add(24);
        list.get(24).add(30);
        list.get(30).add(31);
        list.get(31).add(20);
    }

    // 이동 후 도착 인덱스 구하기
    public static int move(int curIdx, int diceVal) {
        if (curIdx == 50) return 50;
        if (list.get(curIdx).size() >= 2) {
            // 갈래길의 경우 사이길을 선택한다.
            curIdx = list.get(curIdx).get(1);
            diceVal--;
        }
        if (diceVal > 0) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(curIdx);
            int next = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                next = list.get(cur).get(0);
                if (next == 50) break;
                q.offer(next);
                diceVal--;
                if (diceVal == 0) break;
            }
            return next;
        } else return curIdx;
    }

    // 도착지점 말 존재여부
    public static boolean isMal(int locIdx, int malIdx) {
        if (locIdx == 50) return false;
        for (int i = 0; i < 4; i++) {
            if (i == malIdx) continue;
            if (mal[i] == locIdx) return true;
        }
        return false;
    }

    public static int game(int cnt) {
        int rs = 0;
        if (cnt == 10) return 0;
        else {
            for (int i = 0; i < 4; i++) {
                int cur_idx = mal[i];
                int next_idx = move(cur_idx, dice[cnt]);
                if (isMal(next_idx, i)) continue;
                mal[i] = next_idx;
                rs = Math.max(rs, game(cnt + 1) + map.get(next_idx));
                // 경우의 수 해제
                mal[i] = cur_idx;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // 시작 칸에 말이 4개 있다.
        // 10개의 주사위 숫자가 주어질 때 얻을 수 있는 최대 점수
        // 해당 칸에 다른 말이 있으면 도착하지 못한다.
        // 4개의 말을 차례대로 주사위로 이동시키는 재귀함수로 경우의 수를 구한다.
        // 최대 점수를 갱신한다.

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            dice[i] = sc.nextInt();
        }
        makeMap();

        System.out.println(game(0));
    }
}
