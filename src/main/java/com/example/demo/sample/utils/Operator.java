package com.example.demo.sample.utils;


import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

/**枚举运算符及其优先级大小
 *
 * @author 2hu0
 */
public enum Operator {

    ADD("+",5),
    SUBTRACT("-",5),
    MULTIPLY("*",6),
    DIVIDE("÷",6),
    LEFT_BRACKETS("(",7),
    RIGHT_BRACKETS(")",7);

    private final String operationName;

    private final int priority;

    Operator(String operationName, int priority) {
        this.operationName = operationName;
        this.priority = priority;
    }


    public String getOperationName() {
        return operationName;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * 根据string类型的运算符名称找到对用优先级
     * @param operation
     * @return
     */
    public static int getPriorityValue(String operation) {
        for (Operator o: Operator.values()) {
            if (o.getOperationName().equals(operation)) {
                return o.priority;
            }
        }
        //error
        return -1;
    }

    public static Operator getOperator(String operator) {
        switch (operator) {
            case "-":
                return SUBTRACT;
            case "+":
                return ADD;
            case "*":
                return MULTIPLY;
            default:
                return DIVIDE;
        }
    }
}
