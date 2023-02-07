package old.week4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_5430 {
    public static void main(String[] args) {
        // R : 배열에 있는 수의 순서를 뒤집는 함수
        // D : 첫 번째 수를 버리는 함수, 배열이 비어있는데 D를 사용한 경우에는 에러가 발생

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 갯수(최대 100)

        while (T-- > 0) {
            String p = sc.next(); // 수행할 함수 1 ~ 10만
            int n = sc.nextInt(); // 배열에 들어있는 수의 갯수 0 - 10만
            String[] nums = sc.next()
                    .replace("[", "")
                    .replace("]", "")
                    .split(","); // 배열의 정수 (1-100)

            Deque<Integer> deque = new ArrayDeque<>();

            for (String str : nums) {
                if(str.equals("")) break;
                int number = Integer.parseInt(str);
                deque.offer(number);
            }

            boolean err = false;
            boolean rev = false;
            for (char c : p.toCharArray()) {
                if (c == 'R') rev = !rev;
                else {
                    // D인 경우
                    if (deque.isEmpty()) {
                        err = true;
                        break;
                    }
                    if (rev) deque.removeLast();
                    else deque.removeFirst();
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");

            if (err) System.out.println("error");
            else {
                while(!deque.isEmpty()){
                    sb.append(rev ? deque.removeLast() : deque.removeFirst());
                    if(deque.size() != 0) sb.append(",");

                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
