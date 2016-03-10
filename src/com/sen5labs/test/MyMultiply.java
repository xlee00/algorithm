/**
 *MyMultiply.java[V 1.0.0]
 *classes : com.sen5labs.test.MyMultiply
 * Xlee Create at 2016-3-10 上午10:38:36
 */
package com.sen5labs.test;

/**
 * com.sen5labs.test.MyMultiply
 * 
 * @author Xlee <br/>
 *         create at 2016-3-10 上午10:38:36
 */
public class MyMultiply {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("Hello world");

        String str1 = "1234";
        String str2 = "1234";
        // String str1 = "1076060999";
        // String str2 = "90188905567";
        // String str1 = "23456789009877666555544444";
        // String str2 = "346587436598437594375943875943875";

        int len1 = str1.length();
        int len2 = str2.length();

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // 高低位对调
        covertdata(s1, len1);
        covertdata(s2, len2);

        System.out.println("乘数：" + str1);
        System.out.println("乘数：" + str2);
        multiply(s1, len1, s2, len2);

    }

    public static void covertdata(char data[], int len) {
        // 高低位对调
        for (int i = 0; i < len / 2; i++) {
            data[i] += data[len - 1 - i];
            data[len - 1 - i] = (char) (data[i] - data[len - 1 - i]);
            data[i] = (char) (data[i] - data[len - 1 - i]);
        }
    }

    public static void multiply(char a[], int alen, char b[], int blen) {
        // 两数乘积位数不会超过乘数位数和+3位
        int csize = alen + blen + 3;
        // 开辟乘积数组
        int[] c = new int[csize];
        // C++ / C 语言需要初始化，java中默认数组值为0
        // for (int ii = 0; ii < csize; ii++) {
        // c[ii] = 0;
        // }
        // 对齐逐位相乘
        for (int j = 0; j < blen; j++) {
            for (int i = 0; i < alen; i++) {
                c[i + j] += Integer.parseInt(String.valueOf(a[i])) * Integer.parseInt(String.valueOf(b[j]));
            }
        }
        int m = 0;
        // 进位处理
        for (m = 0; m < csize; m++) {
            int carry = c[m] / 10;
            c[m] = c[m] % 10;
            if (carry > 0)
                c[m + 1] += carry;
        }
        // 找到最高位
        for (m = csize - 1; m >= 0; m--) {
            if (c[m] > 0)
                break;
        }
        // 由最高位开始打印乘积
        System.out.print("乘积：");
        for (int n = 0; n <= m; n++) {
            System.out.print(c[m - n]);
        }
        System.out.println("");
    }
}
