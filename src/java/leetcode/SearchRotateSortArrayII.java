package leetcode;

// 81. 搜索旋转排序数组 II
// https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
public class SearchRotateSortArrayII {

    //已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
    //在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
    //给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
    public boolean search(int[] nums, int target) {

        int n = nums.length;
        if (n == 0) { // 空数组直接返回
            return false;
        }
        if (n == 1) {
            return nums[0] == target; // 数组为1，直接比较即可
        }
        int l = 0, r = n - 1; // 左：0，右：n-1
        while (l <= r) {
            int mid = (l + r) / 2; // 中位数
            if (nums[mid] == target) { // 找到目标值
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l; // 左、右边界，如果=mid，直接缩进
                --r;
            } else if (nums[l] <= nums[mid]) { // 左边界比中位数小
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

}
