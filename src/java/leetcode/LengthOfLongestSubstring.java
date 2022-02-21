package leetcode;

import java.util.HashMap;

// 3. 无重复字符的最长子串
// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>(); // 每个字符的位置
        int max = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(map.get(ch) + 1, start); // 字符重复，则更新start指针到下一个位置
            }
            // 更新max值
            max = Math.max(max, end - start + 1); // 比较更新后的字符长度，与已知最大长度
            map.put(ch, end); // 缓存字符最后一次出现的位置
        }
        return max;
    }
}
