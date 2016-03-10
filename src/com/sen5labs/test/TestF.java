/**
 *TestF.java[V 1.0.0]
 *classes : com.sen5labs.test.TestF
 * Xlee Create at 2016-3-8 下午4:53:36
 */
package com.sen5labs.test;

import java.util.ArrayList;

/**
 * com.sen5labs.test.TestF
 * 
 * @author Xlee <br/>
 *         create at 2016-3-8 下午4:53:36
 */
public class TestF {
    public static void main(String[] args) {
        Test2 t = new Test2();
        t.math4(45);
    }
}

class Test2 {
    public void math() {
        int num1 = 1, num2 = 1;
        int num = num1 + num2;
        System.out.print(" " + num1 + " " + num2 + " " + num);
        for (int i = 3; i < 20; i++) {
            int num3 = num + num2;
            num2 = num;
            num = num3;
            System.out.print(" " + num3);
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }

    public void math2(int n) {
        int num1 = 1, num2 = 1;
        int num = num1 + num2;
        System.out.print(" " + num1 + " " + num2 + " " + num);
        for (int i = 3; i < n; i++) {
            int num3 = num + num2;
            num2 = num;
            num = num3;
            System.out.print(" " + num3);
            if (0 == i % 10) {
                System.out.println();
            }
        }
    }

    public void math3(int n) {
        int results[] = new int[n + 1];
        results[1] = 1;
        results[2] = 1;
        System.out.print(" " + results[1] + " " + results[2]);
        for (int i = 3; n > i; i++) {
            results[i] = results[i - 1] + results[i - 2];
            System.out.print(" " + results[i]);
            if (0 == i % 10) {
                System.out.println();
            }
        }
    }

    private static ArrayList<Integer> results = new ArrayList<Integer>();

    public void math4(int n) {
        results.add(-1);
        results.add(1);
        results.add(1);
        System.out.print(" " + results.get(1) + " " + results.get(2));
        for (int i = 3; n > i; i++) {
            results.add(results.get(i - 1) + results.get(i - 2));
            System.out.print(" " + results.get(i));
            if (0 == i % 10) {
                System.out.println();
            }
        }
    }
}
