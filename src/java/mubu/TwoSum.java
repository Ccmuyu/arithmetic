package mubu;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    //1、两数之和
    //https://leetcode-cn.com/problems/two-sum/
    //高赞答案：https://leetcode.com/problems/two-sum/discuss/141/Very-short-and-simple-Java-code-for-Two-Sum
    // 注意：高赞答案是错的
    public  int[] twoSum(int[] nums, int target) {
        // 思路：
        // 1、使用map缓存当前数字的位置，检查当前差值值（target - nums[i]）是否等于map中的值
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer diff = target - nums[i];
            if (hash.containsKey(diff)) {
                return new int[]{hash.get(diff), i};
            }
            hash.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
