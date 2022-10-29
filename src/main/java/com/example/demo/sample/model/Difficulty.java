package com.example.demo.sample.model;


import com.example.demo.sample.utils.Range;

/**
 * 控制难度的类
 * @author 2hu0
 */

public class Difficulty {
    /**
     *运算数的范围 (10,100,1000),枚举表示
     */
    private Range range;

    /**
     * 操作数个数
     */
    private int maxOperationNum;

    /**
     * 是否含有负数
     */
    private boolean hasNegative;

    /**
     * 是否含有括号
     */
    private boolean hasBrackets;

    /**
     * 是否含有小数
     */
    private boolean hasDecimal;
    /**
     * 选择题
     */
    private int choiceQuestionNum;
    /**
     * 判断题
     */
    private int judgeQuestionNum;
    /**
     * 填空题
     */
    private int fillBlankQuestionNum;

    public int getChoiceQuestionNum() {
        return choiceQuestionNum;
    }

    public void setChoiceQuestionNum(int choiceQuestionNum) {
        this.choiceQuestionNum = choiceQuestionNum;
    }

    public int getJudgeQuestionNum() {
        return judgeQuestionNum;
    }

    public void setJudgeQuestionNum(int judgeQuestionNum) {
        this.judgeQuestionNum = judgeQuestionNum;
    }

    public int getFillBlankQuestionNum() {
        return fillBlankQuestionNum;
    }

    public void setFillBlankQuestionNum(int fillBlankQuestionNum) {
        this.fillBlankQuestionNum = fillBlankQuestionNum;
    }

    public Difficulty(Range range, int maxOperationNum, boolean hasNegative, boolean hasBrackets, boolean hasDecimal) {
        this.range = range;
        this.maxOperationNum = maxOperationNum;
        this.hasNegative = hasNegative;
        this.hasBrackets = hasBrackets;
        this.hasDecimal = hasDecimal;
    }

    public Difficulty(Range range, int maxOperationNum, boolean hasNegative, boolean hasBrackets, boolean hasDecimal, int choiceQuestionNum, int judgeQuestionNum, int fillBlankQuestionNum) {
        this.range = range;
        this.maxOperationNum = maxOperationNum;
        this.hasNegative = hasNegative;
        this.hasBrackets = hasBrackets;
        this.hasDecimal = hasDecimal;
        this.choiceQuestionNum = choiceQuestionNum;
        this.judgeQuestionNum = judgeQuestionNum;
        this.fillBlankQuestionNum = fillBlankQuestionNum;
    }

    public Difficulty() {

    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public int getMaxOperationNum() {
        return maxOperationNum;
    }

    public void setMaxOperationNum(int maxOperationNum) {
        this.maxOperationNum = maxOperationNum;
    }

    public boolean isHasNegative() {
        return hasNegative;
    }


    public void setHasBrackets(boolean hasBrackets) {
        this.hasBrackets = hasBrackets;
    }

    public boolean isHasDecimal() {
        return hasDecimal;
    }

    public void setHasDecimal(boolean hasDecimal) {
        this.hasDecimal = hasDecimal;
    }

    public void setHasNegative(boolean hasNegative) {
        this.hasNegative = hasNegative;
    }

    public boolean isHasBrackets() {
        return hasBrackets;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "range=" + range +
                ", maxOperationNum=" + maxOperationNum +
                ", hasNegtive=" + hasNegative +
                ", hasBrackets=" + hasBrackets +
                ", hasDecimal=" + hasDecimal +
                '}';
    }
}
