package newcoder;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 左1，右2、前3、后4、上5、下6
        char[] init = {'1', '2', '3', '4', '5', '6'};
        String s = scanner.nextLine();
        char[] commands = s.toCharArray(); // 代表初始状态：1，2，3，4，5，6
        for (char command : commands) {
            switch (command) {
                case 'L':
                    left(init);
                    break;
                case 'R':
                    right(init);
                    break;
                case 'F':
                    forward(init);
                    break;
                case 'B':
                    back(init);
                    break;
                case 'A':
                    anti(init);
                    break;
                case 'C':
                    clock(init);
                    break;
                default:
            }
        }

        System.out.println(new String(init));

    }

    private static void left(char[] chars) {
        // 1,2,3,4,5,6
        // 5,6,3,4,2,1

        // 交换下标
        char tmp = chars[0];
        chars[0] = chars[4]; // 1,5交换
        chars[4] = tmp;

        // 5,2,3,4,1,6
        tmp = chars[1]; //2,6 交换
        chars[1] = chars[5];
        chars[5] = tmp;

        // 5,6,3,4,1,2
        tmp = chars[4]; //1,2交换
        chars[4] = chars[5];
        chars[5] = tmp;
        //结束
    }

    private static void right(char[] chars) {
        // 1,2,3,4,5,6
        // 6,5,3,4,1,2

        char tmp = chars[0];//1,6 交换
        chars[0] = chars[5];
        chars[5] = tmp;

        // 6,2,3,4,5,1
        tmp = chars[1]; //2,5 交换
        chars[1] = chars[4];
        chars[4] = tmp;

        // 6,5,3,4,2,1
        tmp = chars[4]; //1,2交换
        chars[4] = chars[5];
        chars[5] = tmp;
        //结束
    }

    private static void forward(char[] chars) {
        // 1,2,3,4,5,6
        // 1,2,5,6,4,3

        char tmp = chars[2]; // 3,5 交换
        chars[2] = chars[4];
        chars[4] = tmp;

        // 1,2,5,4,3,6
        tmp = chars[3]; //4,6 交换
        chars[3] = chars[5];
        chars[5] = tmp;

        // 1,2,5,6,3,4
        tmp = chars[4]; //3,4交换
        chars[4] = chars[5];
        chars[5] = tmp;
    }

    private static void back(char[] chars) {
        // 1,2,3,4,5,6
        // 1,2,6,5,3,4
        char tmp = chars[2]; // 3,6交换
        chars[2] = chars[5];
        chars[5] = tmp;

        //1,2,6,4,5,3
        tmp = chars[3]; //4,5 交换
        chars[3] = chars[4];
        chars[4] = tmp;

        // 1,2,6,5,4,3
        tmp = chars[4]; //3,4交换
        chars[4] = chars[5];
        chars[5] = tmp;
    }

    private static void anti(char[] chars) {
        // 1,2,3,4,5,6
        // 4,3,1,2,5,6

        char tmp = chars[0]; // 1,4 交换
        chars[0] = chars[3];
        chars[3] = tmp;

        //4,2,3,1,5,6
        tmp = chars[1]; //2,3交换
        chars[1] = chars[2];
        chars[2] = tmp;

        //4,3,2,1,5,6
        tmp = chars[2]; //1,2交换
        chars[2] = chars[3];
        chars[3] = tmp;
    }

    private static void clock(char[] chars) {
        // 1,2,3,4,5,6
        // 3,4,2,1,5,6

        char tmp = chars[0]; // 1,3交换
        chars[0] = chars[2];
        chars[2] = tmp;

        //3,2,1,4,5,6
        tmp = chars[1]; //2,4交换
        chars[1] = chars[3];
        chars[3] = tmp;

        //3,4,1,2,5,6
        tmp = chars[2]; //1,2交换
        chars[2] = chars[3];
        chars[3] = tmp;

    }
}
