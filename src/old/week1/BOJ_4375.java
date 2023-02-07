package old.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            int cnt = 1;
            int ret = 1;

            while (true) {
                if (ret % n == 0) {
                    break;
                } else {
                    ret = (ret * 10) + 1;
                    ret = ret % n;
                    cnt++;
                }
            }

            System.out.println(cnt);
        }

        br.close();
    }
}
