/**
 *ListAdd.java[V 1.0.0]
 *classes : com.sen5labs.test.ListAdd
 * Xlee Create at 2016-3-8 下午5:12:28
 */
package com.sen5labs.test;

/**
 * com.sen5labs.test.ListAdd
 * 
 * @author Xlee <br/>
 *         create at 2016-3-8 下午5:12:28
 */
public class ListAdd {
    // 两个有序数组的合并函数
    public static int[] MergeList(int a[], int b[]) {
        int result[];
        if (checkSort(a) && checkSort(b)) { // 检查传入的数组是否是有序的
            result = new int[a.length + b.length];

            int i = 0, j = 0, k = 0; // i:用于标示a数组 j：用来标示b数组 k：用来标示传入的数组

            while (i < a.length && j < b.length)
                if (a[i] <= b[j]) {
                    result[k++] = a[i++];
                } else {
                    result[k++] = b[j++];
                }

            /* 后面连个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入 */
            while (i < a.length)
                result[k++] = a[i++];
            while (j < b.length)
                result[k++] = b[j++];

            return result;
        } else {
            System.out.print("非有序数组，不可排序！");
            return null;
        }
    }

    // 检查数组是否是顺序存储的
    public static boolean checkSort(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1])
                return false;
        }
        return true;
    }

    // 打印函数
    public static void print(int b[]) {
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + (i % 10 == 9 ? "\n" : "\t"));
        }
    }

    public static void main(String args[]) {
        // int a[] = { 1, 2, 2, 3, 5, 6, 7, 7 };
        // int b[] = { 1, 2, 4, 5, 8, 8, 9, 10, 11, 12, 12, 13, 14 };
        // int c[] = MergeList(a, b);
        // if (c != null)
        // print(c);
        // else
        // System.out.println("");

        int d[] = { 12, 22, 4, 122, 44, 3, 22, 56, 23, 2, 1, 334, 555, 98, 89 };
        int result[] = binarySort(d);
        for (int i = 0; result.length > i; i++) {
            System.out.print(" " + result[i]);
        }
    }

    public static int[] BarnarySort(int[] data) {
        int[] temp = new int[data.length];
        for (int i = 0; i < temp.length; i++) {
            if (i == 0) {
                temp[i] = data[i];
            } else {
                for (int j = 0, k = i - 1; j < i && k >= 0;) {
                    if (temp[(j + k) / 2] >= data[i]) {
                        if ((j + k) / 2 == 0) {
                            for (int iter = i; 0 < iter; iter--) {
                                temp[iter] = temp[iter - 1];
                            }
                            temp[0] = data[i];
                            break;
                        } else if (temp[(j + k) / 2 - 1] <= data[i]) {
                            for (int iter = i; iter > (j + k) / 2; iter--) {
                                temp[iter] = temp[iter - 1];
                            }
                            temp[(j + k) / 2] = data[i];
                            break;
                        } else {
                            k = (k + j) / 2 - 1;
                        }
                    } else if (temp[(j + k) / 2] < data[i]) {
                        if ((j + k) / 2 == i - 1) {
                            temp[i] = data[i];
                            break;
                        } else {
                            j = (k + j) / 2 + 1;
                        }
                    }
                }
            }
        }
        return temp;
    }

    public static int[] binarySort(int[] datas) {
        if (null == datas || 1 >= datas.length) {
            return datas;
        }
        int[] results = new int[datas.length];
        for (int i = 0; datas.length > i; i++) {
            if (0 == i) {
                results[i] = datas[i];
            } else {
                for (int j = 0, k = i - 1; j < i && k >= 0;) {
                    if (results[(j + k) / 2] >= datas[i]) {
                        if (0 == (j + k) / 2) {
                            for (int iter = i; 0 < iter; iter--) {
                                results[iter] = results[iter - 1];
                            }
                            results[0] = datas[i];
                            break;
                        } else if (results[(j + k) / 2 - 1] <= datas[i]) {
                            for (int iter = i; (j + k) / 2 < iter; iter--) {
                                results[iter] = results[iter - 1];
                            }
                            results[(j + k) / 2] = datas[i];
                            break;
                        } else {
                            k = (j + k) / 2 - 1;
                        }
                    } else if (results[(j + k) / 2] < datas[i]) {
                        if ((j + k) / 2 == i - 1) {
                            results[i] = datas[i];
                            break;
                        } else {
                            j = (j + k) / 2 + 1;
                        }
                    }
                }
            }
        }
        return results;
    }
}