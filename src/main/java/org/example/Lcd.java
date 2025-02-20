package org.example;

import org.apache.commons.lang3.StringUtils;

public class Lcd {
    public void print_lcd(Integer num, Integer size) {
        String[][] lcd_symbols = {
                {" _ ", "| |", "   ", "| |", " _ "},
                {"   ", "  |", "   ", "  |", "   "},
                {" _ ", "  |", " _ ", "|  ", " _ "},
                {" _ ", "  |", " _ ", "  |", " _ "},
                {"   ", "| |", " _ ", "  |", "   "},
                {" _ ", "|  ", " _ ", "  |", " _ "},
                {" _ ", "|  ", " _ ", "| |", " _ "},
                {" _ ", "  |", "   ", "  |", "   "},
                {" _ ", "| |", " _ ", "| |", " _ "},
                {" _ ", "| |", " _ ", "  |", " _ "}
        };

        String num_str = num.toString();

        for (int i = 0; i < 2 + size * 2 + 3; i++) {
            String line = "";
            for (int j = 0; j < num_str.length(); j++) {

                int index = Integer.parseInt(String.valueOf(num_str.charAt(j)));

                if (i == 0) {
                    line += lcd_symbols[index][0].charAt(0) + StringUtils.repeat(lcd_symbols[index][0].charAt(1), size) + lcd_symbols[index][0].charAt(2) + " ";
                }

                else if (i < 1 + size) {
                    line += lcd_symbols[index][1].charAt(0) + StringUtils.repeat(lcd_symbols[index][1].charAt(1), size) + lcd_symbols[index][1].charAt(2) + " ";
                }

                else if (i == 1 + size) {
                    line += lcd_symbols[index][2].charAt(0) + StringUtils.repeat(lcd_symbols[index][2].charAt(1), size) + lcd_symbols[index][2].charAt(2) + " ";
                }

                else if (1 + size < i && i < 2 + size * 2) {
                    line += lcd_symbols[index][3].charAt(0) + StringUtils.repeat(lcd_symbols[index][3].charAt(1), size) + lcd_symbols[index][3].charAt(2) + " ";
                }

                else if (i == 2 + size * 2) {
                    line += lcd_symbols[index][4].charAt(0) + StringUtils.repeat(lcd_symbols[index][4].charAt(1), size) + lcd_symbols[index][4].charAt(2) + " ";
                }

                else {
                    line += " ".repeat(size + 2) + " ";
                }
            }
            System.out.println(line);
        }
    }
}
