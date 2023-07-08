package SystematicClass.Class1;

public class NotEqualProbRandom {

    //x以不等的概率返回0，1
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    //用x写一个以相同概率返回0，1; 00和11不要；01是0，10是1
    public static int y() {
        int ans1 = 0;
        int ans2 = 0;
        do {
            ans1 = x();
            ans2 = x();
        }while (ans1 + ans2 != 1);
        return ans1 == 1 && ans2 == 0 ? 0 : 1;
    }

    //左神版y（）
    public static int y_zuo() {
        int ans = 0;
        do {
            ans = x();
        }while (ans == x());
        return ans;
    }


    public static void main(String[] args) {
        var count1 = new int[2];
        for (int i = 0; i <= 1000000; i++) {
            int num = y_zuo();
            count1[num]++;
        }
        for(int i = 0; i < 2; i++){
            System.out.println(i + "showed " + count1[i] + " times.");
        }
    }

}
