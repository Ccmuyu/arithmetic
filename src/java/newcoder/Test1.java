package newcoder;


import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        // 如：sdfs(1,2)

        int max = 0; // 最远距离的平方
        int[] target = {0, 0};
        int begin = 0, end = 0; //坐标子串位置
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') { // 坐标开始
                begin = i + 1;
            } else if (c == ')') { //坐标结束
                end = i;
                String position = s.substring(begin, end);
                String[] split = position.split(",");
                if (split.length > 1) {
                    if (isValidNum(split[0]) && isValidNum(split[1])) {
                        int x = Integer.parseInt(split[0]);
                        int y = Integer.parseInt(split[1]);
                        int distance = x * x + y * y;
                        if (max < distance) {
                            max = distance;
                            target[0] = x;
                            target[1] = y;
                        }
                    }
                }

            }
            //其他字符跳过
        }
        System.out.println("(" + target[0] + "," + target[1] + ")");
    }

    private static boolean isValidNum(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return !s.startsWith("0");
    }
}
