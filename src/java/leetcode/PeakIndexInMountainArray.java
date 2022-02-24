package leetcode;

// https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
// 852. 山脉数组的峰顶索引
public class PeakIndexInMountainArray {

    // 直接遍历是可以的，复杂度o(n)
    // 但如果是o(log n),只能是二分法
    public int peakIndexInMountainArray(int[] arr) {

        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) { //左右相等时，即是峰点
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) { //峰点的右侧
                right = mid - 1;
                ans = mid; //此时也可能是峰点
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
