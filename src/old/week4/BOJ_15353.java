package old.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String a = st.nextToken();
        String b = st.nextToken();
        System.out.println(add(a, b));
    }
    private static String add(String a, String b) {
        int sum = 0;
        String rs = "";

        while (a.length() != 0 || b.length() != 0 || sum != 0) {
            if (a.length() != 0) {
                sum += a.charAt(a.length() - 1) - '0';
                a = a.substring(0, a.length() - 1);
            }
            if (b.length() != 0) {
                sum += b.charAt(b.length() - 1) - '0';
                b = b.substring(0, b.length() - 1);
            }

            rs += (sum % 10);
            sum /= 10;
        }

        return new StringBuilder(rs).reverse().toString();
    }
}
