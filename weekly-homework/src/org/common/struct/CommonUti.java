package org.common.struct;

/**
 * @author liuyue
 * @Description:
 * @date 2021/12/14 18:36
 */
public class CommonUti {

    public static char[][] leetCodeStringToCharArray(String s) {
        String substring = s.substring(1, s.length() - 1);
        String[] sp = substring.split("],");
        char[][] result = new char[sp.length][];
        for (int i = 0; i < sp.length; i++) {
            String[] sub = sp[i].substring(1, sp[i].length()).split(",");
            result[i] = new char[sub.length];
            for (int j = 0; j < sub.length; j++) {
                result[i][j] = sub[j].charAt(1);
            }
        }
        return result;
    }
}
