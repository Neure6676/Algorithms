package SystematicClass.Class1;

/**
 * //Math.random() -> double [0,1) 等概率
 * double ans = Math.random();
 */
public class RandToRand {

    /**
     * 返回[0，1）的小数
     * 任意的x属于[0，1），[0，x）范围出现的概率由x调整为x平方
     */
    public static double xToXPower2() {
        return Math.max(Math.random(),Math.random());
    }


}
