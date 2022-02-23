package leetcode;

// 922. 按奇偶排序数组 II
// https://leetcode-cn.com/problems/sort-array-by-parity-ii/
public class SortArrayByParityII {

    // 给定一个非负整数数组nums，nums 中一半整数是 奇数 ，一半整数是 偶数 。
    // 对数组进行排序，以便当nums[i] 为奇数时，i也是 奇数 ；当nums[i]为偶数时， i 也是 偶数 。
    public int[] sortArrayByParityII(int[] nums) {

        // 双指针，奇数位置为奇数，偶数位置为偶数
        int n = nums.length;
        int j = 1; //奇数下标
        for (int i = 0; i < n; i += 2) {
            if (nums[i] % 2 == 1) { //下标偶数，但是值为奇数
                while (nums[j] % 2 == 1) {
                    j += 2; //找到奇数位，值为偶数的
                }
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        return nums;

    }
}
