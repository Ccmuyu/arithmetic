package leetcode;

// 389. 找不同
// https://leetcode-cn.com/problems/find-the-difference/
public class FindTheDifference {

    //方法一：计数
    //首先遍历字符串 ss，对其中的每个字符都将计数值加 11；然后遍历字符串 tt，对其中的每个字符都将计数值减 11。当发现某个字符计数值为负数时，说明该字符在字符串 tt 中出现的次数大于在字符串 ss 中出现的次数，因此该字符为被添加的字符。
    //方法二：求和
    //将字符串 ss 中每个字符的 ASCII 码的值求和，得到 A_sA，再减去另外一个字符传的ASCII，差值就是这个字符
    // 方法三：位运算
    //如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。类似于「136. 只出现一次的数字」，我们使用位运算的技巧解决本题。


    public char findTheDifference(String s, String t) {
        int[] cache = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cache[c - 'a'] = cache[c - 'a'] + 1;
        }

        char[] t_chars = s.toCharArray();
        for (char t_char : t_chars) {
            cache[t_char - 'a']--;
            if (cache[t_char - 'a'] < 0) {
                return t_char;
            }
        }
        return ' ';

    }
}
