package week2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2870 {
    public static void main(String[] args) {
        // 문자열을 순회하면서 숫자를 찾는다.
        // 앞 뒤로 문자일 경우 독립된 숫자이다.
        // 연속된 숫자일 경우 연속된 숫자 그대로 반영한다.
        // 오름차순으로 정렬 출력한다.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<BigInteger> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < str.length(); j++) {
                // 숫자가 등장하면 리스트에 넣는다.
                // 자신의 앞이 숫자면 리스트의 마지막 요소를 뺀다.
                // 자신의 앞을 비교해서 자신의 앞숫자 * 10 + 자신 하고 리스트에 더한다.
                if (Character.isDigit(str.charAt(j))) {
                    if (j > 0 && Character.isDigit(str.charAt(j - 1))) {
                        BigInteger tmp = list.get(list.size() - 1);
                        list.remove(list.size() - 1);
                        BigInteger mul = tmp.multiply(BigInteger.valueOf(10));
                        list.add(mul.add(BigInteger.valueOf(str.charAt(j) - '0')));
                    } else {
                        list.add(BigInteger.valueOf(str.charAt(j) - '0'));
                    }
                }
            }
        }

        list.sort(BigInteger::compareTo);
        for (BigInteger bigInteger : list) {
            System.out.println(bigInteger);
        }
    }
}
