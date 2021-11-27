package org.train.secondweek.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyue
 * @Description: 随课练习2
 * @date 2021/11/24 22:18
 */
public class ClassTestTwo {

    /**
     * 串联所有单词的子串
     * @link https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
     */

    public List<Integer> findSubstring(String s, String[] words) {
        int k = words[0].length();
        int total = k * words.length;
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + total > s.length()) break;
            String subStr = s.substring(i, i + total);
            if (valid(subStr, wordsMap, k)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean valid(String s, Map<String, Integer> wordsMap, int k) {
        HashMap<String, Integer> mapA = new HashMap<>();

        for (int i = 0; i < s.length(); i += k) {
            String substring = s.substring(i, i + k);
            mapA.put(substring, mapA.containsKey(substring) ? mapA.get(substring) + 1 : 1);
        }
        return mapA.equals(wordsMap);
    }

    public static void main(String[] args) {
        ClassTestTwo classTestTwo = new ClassTestTwo();
        classTestTwo.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"});
    }
}
