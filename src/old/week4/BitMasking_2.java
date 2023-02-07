package old.week4;

public class BitMasking_2 {
    // 2개의 조합을 뽑는 경우의 수 비트마스킹으로 해결
    static String[] arr = {"사과", "딸기", "포도", "배"};
    static int n = arr.length;

    public static void sol(int num) {
        String rs = "";
        for (int i = 0; i < 4; i++) {
            if ((num & (1 << i)) != 0) rs += arr[i];
        }
        System.out.println(rs);
    }

    public static void main(String[] args) {
        for (int i = 1; i < n; i++) {
            // 0번째 요소인 사과를 포함하는 2개의 조합 경우의 수 구하기
            sol(1 | (1 << i));
        }
    }
}
