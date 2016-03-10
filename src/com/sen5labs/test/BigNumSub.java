package com.sen5labs.test;

/**
 *BigNumSub.java[V 1.0.0]
 *classes : com.sen5labs.test.BigNumSub
 * Xlee Create at 2016-3-10 上午11:22:54
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * com.sen5labs.test.BigNumSub
 * 
 * @author Xlee <br/>
 *         create at 2016-3-10 上午11:22:54
 */
public class BigNumSub {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String minuend;// 被减数
        String meiosis;// 减数
        String difference;// 差值
        while (cin.hasNext()) {
            minuend = cin.next();
            meiosis = cin.next();
            if (!valid(minuend) || !valid(meiosis))// 合法性验证
                return;
            difference = bigNumSub(minuend, meiosis);
            System.out.println(difference);
        }
    }

    /**
     * @param minuend
     *            被减数
     * @param meiosis
     *            减数
     * @return difference 差值
     * 
     *         1.判断被减数和减数的大小 (1):被减数minuend的位数大于meiosis减数的位数，则直接相减
     *         (2):被减数minuend的位数小于meiosis减数的位数，交换相减，结果加上-(负号)
     *         (3):被减数minuend的位数等于meiosis减数的位数，判断两者的大小： a:minuend>=meiosis，则直接相减
     *         b:minuend<meiosis,交换相减，结果加上-(负号)
     */
    private static String bigNumSub(String minuend, String meiosis) {
        int minLen = minuend.length();// 被减数的长度
        int meiLen = meiosis.length();// 减数的长度
        boolean addSign = false;// 是否交换被减数和减数
        String difference;// 结果
        if (minLen < meiLen) {
            addSign = true;
            String temp = minuend;
            minuend = meiosis;
            meiosis = temp;
        } else if (minLen == meiLen) {// 两个位数相等
            // 判断大小
            int re = judge(minuend, meiosis);
            if (re == -1)// 小于，要交换
            {
                addSign = true;
                String temp = minuend;
                minuend = meiosis;
                meiosis = temp;
            } else if (re == 0) {
                return "0";
            }
        }
        // 相减
        difference = sub(minuend, meiosis);
        if (addSign)
            return "-" + difference;
        return difference;
    }

    /**
     * @param minuend
     * @param meiosis
     * @return difference 两个大数相减，这里按照数学上的大小已经是被减数minuend>=meiosis
     */
    private static String sub(String minuend, String meiosis) {
        int minLen = minuend.length();// 被减数的长度
        int meiLen = meiosis.length();// 减数的长度
        int dif = minLen - meiLen;// 两个数的相差几位
        boolean borrow = false;// 借位
        List<Integer> list = new ArrayList<Integer>();// 放置每位相减的结果
        int i = meiLen - 1;
        int j = i + dif;
        int min = 0;
        int mei = 0;
        for (; i >= 0; j--, i--) {
            min = Integer.valueOf(minuend.charAt(j)) - 48;
            mei = Integer.valueOf(meiosis.charAt(i)) - 48;
            int result = 0;

            if (borrow) {// 有借位情况
                min = min - 1;
            }
            if (min >= mei) {
                result = min - mei;
            } else {// 需要借位
                result = min + 10 - mei;
                borrow = true;
            }
            list.add(result);
        }
        // 将剩余的的被减数的位记录下来
        for (; j >= 0; j--) {
            min = Integer.valueOf(minuend.charAt(j)) - 48;
            if (borrow) {// 上一次有借位
                min = min - 1;
                borrow = false;
            }
            list.add(min);
        }
        StringBuilder sb = new StringBuilder();
        // 这里注意把去掉前面的零位
        boolean remove = true;
        for (int k = list.size() - 1; k >= 0; k--) {
            int re = list.get(k);
            if (re != 0 || remove == false) {
                sb.append(re);
                remove = false;
            }
        }
        return sb.toString();
    }

    /**
     * @param minuend
     * @param meiosis
     * @return result 判断两个等位数大数在数学意义上的大小关系
     */
    private static int judge(String minuend, String meiosis) {
        int result = 0;// 默认被减数大于减数
        int len = minuend.length();
        int i = 0;
        for (; i < len; i++) {
            if (minuend.charAt(i) > meiosis.charAt(i)) {// 大于
                result = 1;
                return result;
            } else if (minuend.charAt(i) < meiosis.charAt(i)) {// 小于
                result = -1;
                return result;
            }
        }
        return result;
    }

    // 输入合法性验证
    private static boolean valid(String number) {
        if (null == number && "".equals(number))
            return false;
        char[] charArr = number.toCharArray();
        if (charArr[0] == '0')// 首字符不能为0
            return false;
        for (int i = 1; i < charArr.length; i++) {// 每个字符介于0到9
            char temp = charArr[i];
            if (temp < '0' || temp > '9')
                return false;
        }
        return true;
    }

}