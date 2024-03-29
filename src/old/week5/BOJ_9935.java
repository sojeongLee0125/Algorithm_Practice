package old.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9935 {
    // 1. 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열이 된다.
    // 2. 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
    // 3. 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
    // 모든 폭발이 끝난 후 문자열 / 남아있는 문자가 없는 경우가 있다. 이때는 "FRULA"를 출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine(); // 문자열 (1 - 100만) - 알파벳 소문자와 대문자, 숫자 0, 1, ..., 9
        String bomb = br.readLine(); // 폭발문자열(1-36) - 알파벳 소문자와 대문자, 숫자 0, 1, ..., 9
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            sb.append(c);
            int st = Math.max(sb.length() - bomb.length(), 0);
            int ed = Math.min(sb.length() + bomb.length(), sb.length());
            if (sb.length() >= bomb.length() && sb.substring(st, ed).equals(bomb)) {
                sb.delete(st, ed);
            }
        }

        if (sb.length() == 0) System.out.println("FRULA");
        else System.out.println(sb);
    }
}
