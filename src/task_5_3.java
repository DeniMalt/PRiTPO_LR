package src;

import java.util.Scanner;

public class task_5_3 {
    public static void ones(int n) {
        if ( (n % 2 == 0) || (n % 5 == 0) ) {
            System.out.println( "Решение отсутствует\n" );
            return;
        }

        int k = 1;
        boolean bLeading = true;
        int i = 1;

        while (true) {
            int result = k / n;
            int remainder = k % n;

            if ( (!bLeading) || (result != 0) ) {
                bLeading = false;
            }

            if (remainder == 0) break;
            k = remainder*10 + 1;
            i += 1;
        }

        System.out.println(i);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ones(n);
    }
}