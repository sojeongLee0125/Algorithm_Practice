package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int S = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();

            switch (op) {
                case "all":
                    S = (1 << 21) - 1;
                    break;
                case "empty":
                    S = 0;
                    break;
                default:
                    int num = Integer.parseInt(st.nextToken());
                    switch (op) {
                        case "add":
                            S |= (1 << num);
                            break;
                        case "remove":
                            S &= ~(1 << num);
                            break;
                        case "check":
                            sb.append((S & (1 << num)) == 0 ? 0 : 1).append("\n");
                            break;
                        case "toggle":
                            S ^= (1 << num);
                            break;
                    }
            }
        }

        System.out.println(sb);
    }
}
