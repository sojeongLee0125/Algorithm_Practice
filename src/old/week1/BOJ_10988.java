package old.week1;

import java.util.Scanner;

public class BOJ_10988 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String tmp = new StringBuilder(str).reverse().toString();
        if (str.equals(tmp)) System.out.println(1);
        else System.out.println(0);
    }
}
