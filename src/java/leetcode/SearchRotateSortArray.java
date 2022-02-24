package leetcode;

// 33. 搜索旋转排序数组
// https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
public class SearchRotateSortArray {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2; // 寻找中位点
            if (nums[mid] == target) { // 重要：最终的出口是这里
                return mid;
            }
            if (nums[0] <= nums[mid]) { // 左侧有序
                if (nums[0] <= target && target < nums[mid]) { // 左侧再二分， 说明目标值在左侧
                    r = mid - 1; //  左侧，右边缩进
                } else {
                    l = mid + 1;// 右侧，左边缩进
                }
            } else { // 右侧有序
                if (nums[mid] < target && target <= nums[n - 1]) { // 右侧再二分， 目标值在中位数的右侧
                    l = mid + 1; // 左侧缩进
                } else {
                    r = mid - 1; // 右侧缩进
                }
            }
        }
        return -1;
    }
}
