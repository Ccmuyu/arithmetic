package leetcode;

import java.util.HashMap;
import java.util.Map;

// 290. 单词规律
// https://leetcode-cn.com/problems/word-pattern/
public class WordPattern {

    //给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
    //这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //输入: pattern = "abba", str = "dog cat cat dog"
    //输出: true
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < words.length; i++) {
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i)) {
                return false;
            }
        }
        return true;
    }
}
