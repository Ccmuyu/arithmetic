package newcoder;

import sun.misc.SoftCache;

import java.util.*;

public class Test2 {

    // 10个数字，
    //每个数字仅使用一次
    //=15

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<Set<Integer>> result = new ArrayList<>();

        Integer sum = 15;

        for (int i = 0; i < arr.length; i++) {
            Set<Integer> dest = new HashSet<>();// 存放每一组
            Set<Integer> resultOne = getSum(arr, i, sum, dest);
            if (resultOne == null) {
                continue;
            }
            result.add(dest);
        }

        System.out.println(result);

    }

    private static Set<Integer> getSum(int[] arr, int i, Integer sum, Set<Integer> dest) {

        int res = sum - arr[i];
        if (res == 0) {
            dest.add(arr[i]);
            return dest;
        } else if (res < 0) {
            //不符合
            return null;
        } else {
            // >0
            dest.add(arr[i]);
            return getSum(arr, i++, res, dest);
        }

    }

}
