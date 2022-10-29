package com.example.demo.sample.model;


/**
 * @author 2hu0
 */
public class Node {
    /**
     * 节点存放的数据
     */
    public Operand res;

    public Node left;

    public Node right;

    public int high;

    public Node(Operand res, Node left, Node right, int high) {
        this.res = res;
        this.left = left;
        this.right = right;
        this.high = high;
    }




    @Override
    public String toString() {
        return res.toString();
    }

    public String printNode() {
        if (res.getVal().toPlainString().startsWith("-")){
            return "(" + res.toString() + ")";
        }
        return res.toString();
    }
}
