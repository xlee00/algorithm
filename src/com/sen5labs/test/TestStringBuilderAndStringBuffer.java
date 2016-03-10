/**
 *TestStringBuilderAndStringBuffer.java[V 1.0.0]
 *classes : com.sen5labs.test.TestStringBuilderAndStringBuffer
 * Xlee Create at 2016-2-17 上午11:10:50
 */
package com.sen5labs.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * com.sen5labs.test.TestStringBuilderAndStringBuffer
 * 
 * @author Xlee <br/>
 *         create at 2016-2-17 上午11:10:50
 */
public class TestStringBuilderAndStringBuffer {
    private static final String base = " base string. ";
    private static final int count = 2000000;

    public static void stringTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        String test = new String(base);
        for (int i = 0; i < count / 100; i++) {
            test = test + " add ";
        }
        end = System.currentTimeMillis();
        System.out.println(String.format("%1$-5d millis has elapsed when used StringBuilder. ", (end - begin)));
    }

    public static void stringBufferTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuffer test = new StringBuffer(base);
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        end = System.currentTimeMillis();
        System.out.println(String.format("%1$-5d millis has elapsed when used StringBuilder. ", (end - begin)));
    }

    public static void stringBuilderTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuilder test = new StringBuilder(base);
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        end = System.currentTimeMillis();
        System.out.println(String.format("%1$-5d millis has elapsed when used StringBuilder. ", (end - begin)));
    }

    public static String appendItemsToStringBuiler(List<String> list) {
        StringBuilder b = new StringBuilder();

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            b.append(i.next()).append(" ");
        }

        return b.toString();
    }

    public static void addToStringBuilder() {
        List<String> list = new ArrayList<String>();
        list.add(" I ");
        list.add(" play ");
        list.add(" Bourgeois ");
        list.add(" guitars ");
        list.add(" and ");
        list.add(" Huber ");
        list.add(" banjos ");

        System.out.println(TestStringBuilderAndStringBuffer.appendItemsToStirngBuffer(list));
    }

    public static String appendItemsToStirngBuffer(List<String> list) {
        StringBuffer b = new StringBuffer();

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            b.append(i.next()).append(" ");
        }

        return b.toString();
    }

    public static void addToStringBuffer() {
        List<String> list = new ArrayList<String>();
        list.add(" I ");
        list.add(" play ");
        list.add(" Bourgeois ");
        list.add(" guitars ");
        list.add(" and ");
        list.add(" Huber ");
        list.add(" banjos ");
        System.out.println(TestStringBuilderAndStringBuffer.appendItemsToStirngBuffer(list));
    }

    public static void main(String[] args) {
        stringTest();
        stringBufferTest();
        stringBuilderTest();
        addToStringBuffer();
        addToStringBuilder();
    }
}
