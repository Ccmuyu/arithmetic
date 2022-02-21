package leetcode;

// 387. 字符串中的第一个唯一字符
// https://leetcode-cn.com/problems/first-unique-character-in-a-string/
public class FirstUniqChar {

    //使用indexOf，根据返回位置比较大小
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            if (ind != -1 && ind == s.lastIndexOf(i))
                ans = Math.min(ans, ind);
        }
        if (ans == Integer.MAX_VALUE)
            return -1;
        return ans;
    }
}
