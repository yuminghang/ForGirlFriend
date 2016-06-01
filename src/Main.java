import java.util.Stack;

public class Main {

    static String[] values = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    static String[] pos = {"拾", "佰", "仟", "万"};
    private static Stack<String> mStack;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 99999; i++) {
            if (i >= 10 && i < 20) {
                System.out.println("对不起 第" + change(i).substring(1) + "遍");
            } else {
                System.out.println("对不起 第" + change(i) + "遍");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("运行时间" + (end - start) + "ms");
    }

    private static String change(int i) {
        boolean flag = false;
        int len = 0;
        int num = i;
        while (i > 0) {
            i /= 10;
            len++;
        }
        if (len > 1) {
            mStack = new Stack<String>();
            for (int j = 1; j < len; j++) {
                if (numAtInteger(num, j) != 0)
                    mStack.push(pos[j - 1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = len - 1; j > 0; j--) {
            int temp = (num / ((int) Math.pow(10, j)));
            num %= ((int) Math.pow(10, j));
            if (temp != 0) {
                sb.append(values[temp]);
                sb.append(mStack.pop());
            }
            if (temp == 0 && !flag) {
                sb.append("零");
                flag = true;
            }
        }
        if (num == 0) {
            if (sb.toString().endsWith("零")) {
                return sb.substring(0, sb.length() - 1);
            }
            return sb.toString();
        } else {
            sb = sb.append(values[num]);
            return sb.toString();
        }
    }
    static int numAtInteger(int num, int j) {
        while (j > 0) {
            num /= 10;
            j--;
        }
        return num % 10;
    }
}
