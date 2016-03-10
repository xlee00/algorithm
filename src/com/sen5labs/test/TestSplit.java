/**
 *TestSplit.java[V 1.0.0]
 *classes : com.sen5labs.test.TestSplit
 * Xlee Create at 17 Nov 2015 18:52:20
 */
package com.sen5labs.test;

/**
 * com.sen5labs.test.TestSplit
 * 
 * @author Xlee <br/>
 *         create at 17 Nov 2015 18:52:20
 */
public class TestSplit {
    public static void main(String[] args) {
        // int number = 0xffffffff;
        // System.out.println("number == 0x" + Integer.toHexString(number) +
        // "  " + Integer.toBinaryString(number) + " "
        // + number);
        int i = "s/dfasdf.m3u".lastIndexOf('.');
        System.out.println("Last index of / " + i);
    }
}
