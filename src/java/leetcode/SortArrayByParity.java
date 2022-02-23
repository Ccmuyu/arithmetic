package leetcode;

// 905. 按奇偶排序数组
// https://leetcode-cn.com/problems/sort-array-by-parity/
public class SortArrayByParity {

    //在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
    public int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;

        while (i < j) {
            if (nums[i] % 2 > nums[j] % 2) { //奇数在前
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            if (nums[i] % 2 == 0) i++; //如果i已经是偶数
            if (nums[j] % 2 == 1) j--; //如果j已经奇数
        }
        return nums;
    }
}
