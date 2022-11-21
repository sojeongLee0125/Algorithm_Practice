package week4;

public class BitMasking_1 {
    // 비트마스킹으로 경우의 수 구하기
    public static void main(String[] args) {
        String[] arr = {"사과", "딸기", "포도", "배"};
        int n = arr.length;

        for (int i = 0; i < (1 << n); i++) {
            String rs = "";
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    rs += (arr[j]);
                }
            }
            System.out.println(rs);
        }
    }
}
