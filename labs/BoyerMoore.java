package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BoyerMoore {
    /** Функция findPattern **/
    public void findPattern(String t, String p)
    {
        char[] text = t.toCharArray();
        char[] pattern = p.toCharArray();
        int pos = indexOf(text, pattern);
        if (pos == -1)
            System.out.println("\nNo Match\n");
        else
            System.out.println("Pattern found at position : "+ pos);
    }

    /** Функция для вычисления индекса подстроки шаблона **/
    public int indexOf(char[] text, char[] pattern)
    {
        if (pattern.length == 0)
            return 0;
        int[] charTable = makeCharTable(pattern);
        int[] offsetTable = makeOffsetTable(pattern);
        for (int i = pattern.length - 1, j; i < text.length;)
        {
            for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j)
                if (j == 0)
                    return i;

            // i += pattern.length - j; // For naive method
            i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
        }
        return -1;
    }
    /** Создает таблицу переходов на основе информации о несовпадающих символах **/
    private int[] makeCharTable(char[] pattern)
    {
        final int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i)
            table[i] = pattern.length;
        for (int i = 0; i < pattern.length - 1; ++i)
            table[pattern[i]] = pattern.length - 1 - i;
        return table;
    }
    /** Создает таблицу переходов на основе смещения сканирования, при котором возникает несоответствие. **/
    private static int[] makeOffsetTable(char[] pattern)
    {
        int[] table = new int[pattern.length];
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length - 1; i >= 0; --i)
        {
            if (isPrefix(pattern, i + 1))
                lastPrefixPosition = i + 1;
            table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
        }
        for (int i = 0; i < pattern.length - 1; ++i)
        {
            int slen = suffixLength(pattern, i);
            table[slen] = pattern.length - 1 - i + slen;
        }
        return table;
    }
    /** функция, чтобы проверить, является ли игла [p: end] префиксом шаблона**/
    private static boolean isPrefix(char[] pattern, int p)
    {
        for (int i = p, j = 0; i < pattern.length; ++i, ++j)
            if (pattern[i] != pattern[j])
                return false;
        return true;
    }
    /** функция, возвращающая максимальную длину подстроки, оканчивающейся на p и являющейся суффиксом **/
    private static int suffixLength(char[] pattern, int p)
    {
        int len = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j)
            len += 1;
        return len;
    }
    /** Main Function **/
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Boyer Moore Algorithm Test\n");
        System.out.println("Введите строку: ");
        String text = br.readLine();
        System.out.println("Введите подстроку: ");
        String pattern = br.readLine();
        System.out.println("Введите чувствительность к регистру(0 - нечувст, 1 - чувст): ");
        int registr = scanner.nextInt();
        BoyerMoore bm = new BoyerMoore();
        if(registr == 0){
            String textLower = text.toLowerCase();
            String patternLower = pattern.toLowerCase();
            bm.findPattern(textLower, patternLower);
        }else if(registr == 1){
            bm.findPattern(text, pattern);
        }
    }
}
