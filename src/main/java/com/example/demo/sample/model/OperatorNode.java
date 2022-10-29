package com.example.demo.sample.model;


import com.example.demo.sample.utils.Operator;

/**
 * @author 2hu0
 */
public class OperatorNode extends Node{
    /**
     * 存放运算符
     */
    Operator operator;


    public OperatorNode( Node left, Node right, Operator operator) {
        super(null, left, right,0);
        this.operator = operator;
    }

    /**
     * 是否为加号或者减号
     * @return
     */
    public static boolean isAddOrSub(Operator operator) {
        return operator.equals(Operator.ADD)
                || operator.equals(Operator.SUBTRACT);
    }

    /**
     *  是否为乘或者除
     * @return
     */
    public static boolean isMulOrDiv(Operator operator) {
        return operator.equals(Operator.MULTIPLY)
                || operator.equals(Operator.DIVIDE);
    }

    public static boolean leftBrackets(Operator leftOperator, Operator midOperator) {
        return (isAddOrSub(leftOperator) && isMulOrDiv(midOperator));
    }

    /**
     * 判断正确性
     * @param rightOperator
     * @param midOperator
     * @return
     */
    //TODO 优化括号的生成策略
    public static boolean rightBrackets(Operator rightOperator,Operator midOperator) {
        if (midOperator.equals(Operator.DIVIDE) ) {
            return true;
        }
        if (isMulOrDiv(midOperator) && isAddOrSub(rightOperator)){
            return true;
        }
        if (isMulOrDiv(midOperator) && isMulOrDiv(rightOperator)) {
            return false;
        }
        if (midOperator.equals(Operator.ADD)) {
            return false;
        }
        if (midOperator.equals(Operator.SUBTRACT) && isAddOrSub(rightOperator)) {
            return true;
        }
        return !(isAddOrSub(midOperator) && isMulOrDiv(rightOperator));
    }

    @Override
    public String toString() {
        return operator.getOperationName();
    }

}
