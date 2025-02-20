package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer size = scanner.nextInt();
        Integer num = scanner.nextInt();

        Lcd lcd = new Lcd();
        lcd.print_lcd(num, size);
    }
}