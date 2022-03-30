package newcoder;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        System.out.println("hello world");

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Map<String,Set<String>> map = new HashMap<>(); // 结果：key=有序的字符串，value=异构词列表
        for (int i = 0; i < strs.length; i++) {
            String key = getOrderKey(strs[i]); // 对str字符进行排序，并返回作为key
            if (map.containsKey(key)) {
                map.get(key).add(strs[i]);
            } else {
                map.put(key, new HashSet<>());
            }
        }

        StringBuilder builder = new StringBuilder("[");
        // 输出
        for (Map.Entry<String,Set<String>> entry :map.entrySet()) {
            builder.append("[");
            Iterator<String> iterator = entry.getValue().iterator();
            while(iterator.hasNext()) {
                String s = iterator.next();
                builder.append("\"").append(s).append("\"").append(",");
            }
            builder.deleteCharAt(builder.length()-1); // 去掉最后一个,
            builder.append("],");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append("]");

        System.out.println(builder);

    }

    private static String getOrderKey(String s){
        if (s == null || s.isEmpty()) {
            return "";
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) { // 冒泡排序
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    // 交换
                    char tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return new String(arr);
    }
}