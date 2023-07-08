package SystematicClass.Class3;

public class EvenTimesOddTimes {

    // arr中只有一种数出现奇数次，剩下的出现偶数次
    public static int printOddTimeNum1(int[] arr) {
        int eor = 0;
        for (int item = 0; item < arr.length; item++){
            eor ^= arr[item];
        }
        return eor;
    }

    //将一个int最右侧的1提取出来: a&(-a)   -a = ~a + 1
    //arr中有2种数出现奇数次，剩下的出现偶数次
    //因为第一次eor得到a^b, 所以要找一点区分a和b：eor最右侧的1就是（任意一个1都是）a有b没有的
    //以这个位置将arr分为两类，有转化为了第一种

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1


        int onlyOne = 0; // eor'
        for (int i = 0 ; i < arr.length;i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
}
