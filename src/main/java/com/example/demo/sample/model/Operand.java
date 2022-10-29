package com.example.demo.sample.model;



import com.example.demo.sample.utils.Operator;
import com.example.demo.sample.utils.RandomUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 2hu0
 */
public class Operand {
    private  BigDecimal val;


    public Operand(BigDecimal val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toPlainString();
    }

    /**
     * 获得操作数
     *
     * @param difficulty
     * @return
     */
    public static Operand getOperand(Difficulty difficulty) {
        boolean hasDecimal = difficulty.isHasDecimal();
        BigDecimal res;
        switch (difficulty.getRange()) {
            case TEN:
                res = hasDecimal ? RandomUtils.getDecimalInTen() : RandomUtils.getIntegerInTen();
                break;
            case HUNDRED:
                res = hasDecimal ? RandomUtils.getDecimalInHundred() : RandomUtils.getIntegerInhundred();
                break;
            case THOUSAND:
                res = hasDecimal ? RandomUtils.getDecimalInThousand() : RandomUtils.getIntegerInthousand();
                break;
            default:
                res = new BigDecimal(BigInteger.ZERO);
                break;
        }
        if (difficulty.isHasNegative()) {
            return new Operand(addNegative(res));
        }
        return new Operand(res);
    }

    /**
     * 给数字加上负号
     *
     * @param res
     * @return
     */
    public static BigDecimal addNegative(BigDecimal res) {
        StringBuilder sb = new StringBuilder(res.toPlainString());
        boolean control = RandomUtils.getRandomTrueOrFalse();
        if (control) {
            sb.insert(0, '-');
        }
        return new BigDecimal(sb.toString());
    }

    /**
     * 运算
     *
     * @param operator 运算符
     * @param o1       运算数o1
     * @param o2       运算数o2
     * @return Operand
     */
    public static Operand doCalculate(Operator operator, Operand o1, Operand o2) {
        switch (operator) {
            case ADD:
                return o1.add(o2);
            case SUBTRACT:
                return o1.subtract(o2);
            case MULTIPLY:
                return o1.multiply(o2);
            default:
                return o1.divide(o2);
        }
    }

    public Operand add(Operand o2) {
        return new Operand(this.val.add(o2.val));
    }

    public Operand subtract(Operand o2) {
        return new Operand(this.val.subtract(o2.val));
    }

    public Operand divide(Operand o2) {

       // Operand operand = new Operand(this.val.divide(o2.val, BigDecimal.ROUND_HALF_UP));
        return new Operand(this.val.divide(o2.val,10,BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public Operand multiply(Operand o2) {
        return new Operand(this.val.multiply(o2.val));
    }
}
