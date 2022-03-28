package com.example.demo.dedup;

import java.util.Random;

import static java.lang.System.out;

public class StringDeduplicate {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            byte[] strBytes = new byte[10000];
            int strLen = random.nextInt(10000);
            for (int j = 0; j < strLen; j++) {
                strBytes[j] = (byte) ('a' + random.nextInt(2));
            }
            //out.println(new String(strBytes));
            out.println(transferDuplicate(new String(strBytes, 0, strLen)));
        }
    }

    /**
     * deduplication
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string. Repeat this process until
     * there is no more than 3 identical characters sitting besides each other.
     *
     * @param  input
     *          String which only contains alphabet characters a-z
     *
     */
    public static String deduplicate(String input) {
        if (null == input || input.length() < 3) return input;
        byte[] inpBytes = input.getBytes();//输入的byte数组
        byte[] resBytes = new byte[inpBytes.length];//结果数组
        int resIndex = 0;//结果位置
        int count = 0;
        int i = 0;
        byte cur = 0;
        while (i < inpBytes.length) {
            //向右读取inpBytes
            while (i < inpBytes.length && cur == inpBytes[i]) {
                resBytes[resIndex++] = inpBytes[i++];
                count++;
            }
            if (count > 2) {
                resIndex -= count;//resIndex归位置
            }
            if (i >= inpBytes.length) break;
            cur = inpBytes[i];
            resBytes[resIndex++] = inpBytes[i++];
            count = 1;
            while (resIndex - count - 1 >= 0 && cur == resBytes[resIndex - 1 - count]) count++;
            if (i >= inpBytes.length) {
                if (count > 2) {
                    resIndex -= count;//resIndex归位置
                }
            }
        }
        return new String(resBytes, 0, resIndex);
    }
    /**
     * deduplication
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical,  replace them with a single character that comes before it alphabetically. Repeat this process until
     * there is no more than 3 identical characters sitting besides each other.
     *
     * @param  input
     *          String which only contains alphabet characters a-z
     *
     */
    public static String transferDuplicate(String input) {
        if (null == input || input.length() < 3) return input;
        byte[] inpBytes = input.getBytes();//输入的byte数组
        byte[] resBytes = new byte[inpBytes.length];//结果数组
        int resIndex = 0;//结果位置
        int count = 0;
        int i = 0;
        byte cur = 0;
        while (i < inpBytes.length) {
            //向右读取inpBytes
            while (i < inpBytes.length && cur == inpBytes[i]) {
                resBytes[resIndex++] = inpBytes[i++];
                count++;
            }
            if (count > 2) {
                resIndex -= count;//resIndex归位置}
                if (cur - 'a' > 0) {
                    inpBytes[--i] = (byte) (cur - 1);
                }
            }
            if (i >= inpBytes.length) break;
            cur = inpBytes[i];
            resBytes[resIndex++] = inpBytes[i++];
            count = 1;
            while (resIndex - count - 1 >= 0 && cur == resBytes[resIndex - 1 - count]) count++;
            if (i >= inpBytes.length) {
                if (count > 2) {
                    resIndex -= count;//resIndex归位置
                }
            }
        }
        return new String(resBytes, 0, resIndex);
    }
}
