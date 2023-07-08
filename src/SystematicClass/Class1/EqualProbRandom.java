package SystematicClass.Class1;

public class EqualProbRandom {
    /**
     * 经典面试题
     * 条件f() 1-5 f不能动
     * 目标g() 1-7
     *
     * 先把f改成0，1发生器：1，2得0；4，5得1；3重来
     */

    //f() 1-5
    public static int f() {
        return (int)(Math.random() * 5) + 1;
    }

    //0，1发生器
    public static int f01() {
        int ans = 0;
        do {
            ans = f();
        }while(ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 用二进制，得到000-111（其实就是0-7）
    public static int f07() {
        return (f01() << 2) + (f01() << 1) + f01();
    }

    // 得到0-6
    public static int f06() {
        int ans = 0;
        do {
            ans = f07();
        }while (ans == 7);
        return ans;
    }

    // 得到1-7
    public static int g() {
        int ans = 0;
        do {
            ans = f07();
        }while (ans == 0);
        return ans;
    }

    public static void main(String[] args) {
        var count = new int[7];
        for (int i = 0; i <= 1000000; i++) {
            int num = f06();
            count[num]++;
        }
        for(int i = 0; i < 7; i++){
            System.out.println(i+1 + "showed " + count[i] + " times.");
        }

    }

}
