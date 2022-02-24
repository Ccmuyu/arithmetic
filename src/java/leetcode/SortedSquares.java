package leetcode;

// https://leetcode-cn.com/problems/squares-of-a-sorted-array/
// 977. 有序数组的平方
public class SortedSquares {

    // 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
    // 使用两个指针分别指向位置 0 和 n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针
    public int[] sortedSquares(int[] nums) {
        // 双指针
        int n = nums.length;
        int[] res = new int[nums.length];
        // 头尾各一个指针，比较平方后的值大小，即可
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            int _i = nums[i] * nums[i];
            int _j = nums[j] * nums[j];
            if (_i > _j) { // 因为本身已是有序，所以只需解决，负数时带来的平方问题
                res[pos] = _i; // 从末尾插入，直接取的即是最大值，无法再额外排序
                i++;
            } else {
                res[pos] = _j;
                j--;
            }
            pos--;
        }
        return res;

    }
}
