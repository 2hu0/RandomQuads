package com.example.demo.sample.utils;


import java.math.BigDecimal;
import java.util.Random;

/**
 * @author 2hu0
 */
public class RandomUtils {

    /**
     * 获得指定范围内的随机整数
     * [0,range]
     */

    public final static Random RANDOM = new Random();

    public static int getRandomIntegerInRange(int range) {
        return RANDOM.nextInt(range);
    }

    /**
     * 随机生成true或者false
     */
    public static boolean getRandomTrueOrFalse() {
        return RANDOM.nextBoolean();
    }

    /**
     * 随机生成 0 - 2（小数点后面位数控制）使用
     * 0 为 0 位  1 为 1 位  2 为 2 位
     */
    public static int getDecimalController() {
        return getRandomIntegerInRange(3);
    }

    /**
     * 返回 1 - 100中的数  无小数版本
     * 1 - 20 21 - 100
     * 分成 3/5 + 2/5
     * 分别进行加权
     *
     * @return
     */
    public static int getIntegerInHundred() {
        int range = 20;
        int control = getRandomIntegerInRange(5) + 1;
        if (control <= 3) {
            return getRandomIntegerInRange(range - 1) + 1;
        } else {
            return getRandomIntegerInRange(80) + 1 + 20;
        }
    }

    public static BigDecimal getIntegerInhundred() {
        return new BigDecimal(getIntegerInHundred());
    }

    public static BigDecimal getIntegerInthousand() {
        return new BigDecimal(getIntegerInThousand());
    }

    /**
     * 返回 1 - 100中的数 带小数版本
     *
     * @return
     */

    public static BigDecimal getDecimalInHundred() {

        int val = getIntegerInHundred();
        //分为五等份
        int control = getRandomIntegerInRange(5) + 1;

        if (control <= 3) {
            return addPoint2Integer(val, true);
        }

        return new BigDecimal(val);


    }

    /**
     * 返回 1 - 1000中的数  无小数版本
     * 1 - 100 101 - 500 501 - 1000
     * 分成 5   3    2
     * 分别进行加权
     */
    public static int getIntegerInThousand() {
        int control = getRandomIntegerInRange(5) + 1;
        if (control <= 2) {
            // 501 - 1000
            return getRandomIntegerInRange(500) + 1 + 500;
        } else if (control >= 5) {
            // 1 - 100;
            return getRandomIntegerInRange(100) + 1;
        } else {
            // 101 - 500
            return getRandomIntegerInRange(400) + 1 + 100;
        }
    }

    /**
     * 返回 1 - 1000中的数字 有小数版本
     *
     * @return
     */
    public static BigDecimal getDecimalInThousand() {

        int val = getIntegerInThousand();
        int control = getRandomIntegerInRange(5) + 1;
        if (control <= 2) {
            return addPoint2Integer(val, true);
        } else {
            return new BigDecimal(val);
        }
    }


    /**
     * 小数点后的位数可以是一位或者两位
     * isHaveZero 表示是否可以不含有小数点
     *
     * @param val 传过来的原始值
     */

    public static BigDecimal addPoint2Integer(int val, boolean isHaveZero) {
        //decimalController是控制小数点后面有几位的控制标志
        //true 为 1位  false  为2位数
        int decimalController = getDecimalController();
        //当isHaveZero为false  且 decimalConroller等于0 时候要重新赋值
        if (!isHaveZero && decimalController == 0) {
            decimalController = getRandomIntegerInRange(2) + 1;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(val));
        switch (decimalController) {
            case 0:
                break;
            case 1:
                sb.append(".").append(RANDOM.nextInt(10));
                if (sb.toString().endsWith(".0")) {
                    sb = new StringBuilder(sb.substring(0, sb.indexOf(".")));
                }
                break;
            case 2:
                sb.append(".").append(RANDOM.nextInt(10)).append(RANDOM.nextInt(10));
                if (sb.toString().endsWith(".00")) {
                    sb = new StringBuilder(sb.substring(0, sb.indexOf(".")));
                }
                break;
            default:
                break;
        }
        // System.out.println("sb.toPlainString() = " + sb.toPlainString());

        return new BigDecimal(sb.toString());
    }

    /**
     * 返回指定的带小数点的数字
     *
     * @param range
     * @return
     */
    public static BigDecimal getDecimalInRange(int range) {
        switch (range) {
            case 10:
                return addPoint2Integer(getRandomIntegerInRange(11), false);
            case 100:
                return addPoint2Integer(getRandomIntegerInRange(101), false);
            case 1000:
                return addPoint2Integer(getRandomIntegerInRange(1001), false);
            default:
                return new BigDecimal("");
        }

    }


    /**
     * 随机生成 加号减号
     *
     * @return
     */
    public static Operator getRandomAddOrSubOperation() {
        int control = getRandomIntegerInRange(2);
        if (control == 0) {
            return Operator.ADD;
        } else {
            return Operator.SUBTRACT;
        }
    }

    public static Operator getRandomMulOrDivOperation() {
        int control = getRandomIntegerInRange(2);
        if (control == 0) {
            return Operator.MULTIPLY;
        } else {
            return Operator.DIVIDE;
        }
    }

    /**
     * 随机生成加减乘除
     *
     * @return
     */
    public static Operator getRandomAllOperation() {
        boolean control = getRandomTrueOrFalse();
        if (control) {
            return getRandomAddOrSubOperation();
        } else {
            return getRandomMulOrDivOperation();
        }
    }

    public static void main(String[] args) {

    }

    /**
     * 10以内
     *
     * @return
     */
    public static BigDecimal getIntegerInTen() {
        return new BigDecimal(getRandomIntegerInRange(10) + 1);
    }

    /**
     * 10以内 小数版本
     *
     * @return
     */
    public static BigDecimal getDecimalInTen() {

        return addPoint2Integer(getIntegerInTen().intValue(), false);

    }


    /**
     *选择题错误答案
     */
    public static String getErrorRes(String targetStr) {
        boolean control = RandomUtils.getRandomTrueOrFalse();
        BigDecimal target = new BigDecimal(targetStr);
        BigDecimal decimalInTen = getDecimalInTen();
        String str= "";
        if (control) {
             str = target.add(decimalInTen).toPlainString();
        } else {
            str = target.subtract(decimalInTen).toPlainString();
        }
        if (str.contains("E")) {
            System.out.println(str);
        }
        if (str.indexOf(".") != -1) {
            if (str.length() - str.indexOf(".") >3) {
                System.out.println(str);
            }
        }
        return str;
    }
}
