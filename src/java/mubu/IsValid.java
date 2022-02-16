package mubu;

import java.util.Stack;

public class IsValid {

    public static void main(String[] args) {


    }

    //有效的括号
    public boolean isValid(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }
        return s.length() == 0;
        //也可以用压栈的方法
    }

    public boolean isValid_1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                //弹出的与当前的，如果不相等
                return false;
            }
        }
        return stack.isEmpty();
    }


}
