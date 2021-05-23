package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Knut_Morris_Pratt {
    /** Failure array **/
    private final int[] Pi;
    /** Constructor **/
    public Knut_Morris_Pratt(String text, String pattern)
    {
        /** pre construct failure array for a pattern **/
        Pi = new int[pattern.length()];
        fail(pattern);
        /** find match **/
        int pos = posMatch(text, pattern);
        if (pos == -1)
            System.out.println("\nNo match found");
        else
            System.out.println("\nMatch found at index "+ pos);
    }
    /** Failure function for a pattern **/
    private void fail(String pat)
    {
        int n = pat.length();
        Pi[0] = 0;
        for (int j = 1; j < n; j++)
        {
            int i = Pi[j - 1];
            while ((pat.charAt(j) != pat.charAt(i + 1)) && i >= 0)
                i = Pi[i];
            if (pat.charAt(j) == pat.charAt(i + 1))
                Pi[j] = i + 1;
            else
                Pi[j] = -1;
        }
    }
    /** Function to find match for a pattern **/
    private int posMatch(String text, String pat)
    {
        int i = 0, j = 0;
        int lens = text.length();
        int lenp = pat.length();
        while (i < lens && j < lenp)
        {
            if (text.charAt(i) == pat.charAt(j))
            {
                i++;
                j++;
            }
            else if (j == 0)
                i++;
            else
                j = Pi[j - 1] + 1;
        }
        return ((j == lenp) ? (i - lenp) : -1);
    }
    /** Main Function **/
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Knuth Morris Pratt Test\n");
        System.out.println("Введите строку: ");
        String text = br.readLine();
        System.out.println("Введите подстроку: ");
        String pattern = br.readLine();
        System.out.println("Введите чувствительность к регистру(0 - нечувст, 1 - чувст): ");
        int registr = scanner.nextInt();
        if(registr == 0){
            String textLower = text.toLowerCase();
            String patternLower = pattern.toLowerCase();
            Knut_Morris_Pratt kmp = new Knut_Morris_Pratt(textLower, patternLower);
        }else if(registr == 1){
            Knut_Morris_Pratt kmp = new Knut_Morris_Pratt(text, pattern);
        }
    }
}
