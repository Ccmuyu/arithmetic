package mubu;

import java.util.Stack;

public class DecodeString {

    // 394. 字符串解码
    // https://leetcode-cn.com/problems/decode-string/comments/
    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']')
                stack.push(c); //push everything but ]
            else {
                //step 1:
                //if you find a closing ] then
                //retrieve the string it encapsulates

                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }

                String sub = sb.toString(); //this is the string contained in [ ]
                stack.pop(); //Discard the '[';


                //step 2:
                //after that get the number of
                // times it should repeat from stack

                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek()))
                    sb.insert(0, stack.pop());

                int count = Integer.parseInt(sb.toString()); //this is the number


                //step 3:
                //repeat the string within the [ ] count
                //number of times and push it back into stack

                while (count > 0) {
                    for (char ch : sub.toCharArray())
                        stack.push(ch);
                    count--;
                }
            }
        }

        //final fetching and returning the value in stack
        StringBuilder retv = new StringBuilder();
        while (!stack.isEmpty())
            retv.insert(0, stack.pop());

        return retv.toString();
    }

    public static void main(String[] args) {
        //
        //decodeString(String s)

        String s = "3[a]2[bc]";
        String s1 = decodeString(s);
        System.out.println(s1);

        Stack<Character> stack = new Stack<>();

        StringBuilder sb;
        //除了]字符，都push进去
        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(c);
                continue;
            }
            sb = new StringBuilder();

            // 1、还原[]内的字符串，由于使用stack，所有字符都是倒序的
            while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                sb.insert(0, stack.pop());
            }
            String sub = sb.toString(); // []内的子串
            stack.pop(); // 忽略 '['

            // 2、还原[]前的次数
            sb = new StringBuilder();
            while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                sb.insert(0, stack.pop());
            }
            int count = Integer.parseInt(sb.toString());

            // 3、按次数对[]内的字符进行还原
            while (count > 0) {
                for (char c1 : sub.toCharArray()) {
                    stack.push(c1); // 子串全部塞回栈内
                }
                count--; //按循环次数
            }
        }

        // 4、获取栈内所有的字符串
        sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        // 最终结果
        String finalStr = sb.toString();
        System.out.println(finalStr);
    }
}
