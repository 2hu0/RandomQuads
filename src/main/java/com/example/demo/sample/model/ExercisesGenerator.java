package com.example.demo.sample.model;

import com.example.demo.sample.utils.Operator;
import com.example.demo.sample.utils.RandomUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;

/**
 * 随机生成四则运算
 *
 * @author 2hu0
 */
public class ExercisesGenerator {

    /**
     * 生成的表达式会存放在这里
     */
    private Node root;
    /**
     * 难度控制
     */
    private Difficulty difficulty;

    /**
     * 存放问题表达式
     */
    private final LinkedList questionList = new LinkedList();

    private String[] str = {"18", "2"};

    private String[] str1 = {"÷"};

    private static int index = 0;
    private static int index1 = 0;

    private boolean isDivideForZero = false;

    public ExercisesGenerator(Node root, Difficulty difficulty, boolean isDivideForZero) {
        this.root = root;
        this.difficulty = difficulty;
        this.isDivideForZero = isDivideForZero;
    }

    public ExercisesGenerator() {
    }

    public ExercisesGenerator(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LinkedList getQuestionList() {
        return questionList;
    }

    public boolean isDivideForZero() {
        return isDivideForZero;
    }

    public void setDivideForZero(boolean divideForZero) {
        isDivideForZero = divideForZero;
    }

    public void generateNode() {
        root = generateNode(difficulty.getMaxOperationNum() - 1);
        // root = generateNode(3);

       // root = test(1);
        //格式化
        answerFormat();
    }


    /**
     * 构建生成四则运算表达式的二叉树
     *
     * @param number 运算符的个数
     * @return
     */
    public Node generateNode(int number) {
        //如果为0就构造叶子节点
        if (number == 0) {
            return new Node(Operand.getOperand(difficulty), null, null, 1);
        }
        // 1 否则构造符号节点
        OperatorNode parent = new OperatorNode(null, null, RandomUtils.getRandomAllOperation());
         //左子树的符号数目
        int left = RandomUtils.getRandomIntegerInRange(number);
        //2.递归下去构造左孩子和有孩子
        parent.left = generateNode(left);

        parent.right = generateNode(number - left - 1);
        //3.计算结果
        parent.res = Operand.doCalculate(parent.operator, parent.left.res, parent.right.res);
        //4.计算树高
        parent.high = Math.max(parent.left.high, parent.right.high) + 1;
        return parent;
    }

    public Node test(int number) {
        //测试
        if (number == 0) {
            return new Node(new Operand(new BigDecimal(str[index++])), null, null, 1);
        }
        // 1 否则构造符号节点
//        OperatorNode parent = new OperatorNode(null, null, RandomUtils.getRandomAllOperation());
        OperatorNode parent = new OperatorNode(null, null, Operator.getOperator(str1[index1++]));
        int left = RandomUtils.getRandomIntegerInRange(number);
        //2.递归下去构造左孩子和有孩子
        parent.left = test(left);
        parent.right = test(number - left - 1);
        //3.计算结果
        parent.res = Operand.doCalculate(parent.operator, parent.left.res, parent.right.res);
        //4.计算树高
        parent.high = Math.max(parent.left.high, parent.right.high) + 1;
        return parent;
    }

    /**
     * 对答案进行格式化，超过两位小数的保留两位小数,xx.0格式的把0去掉
     */
    //TODO
    public void answerFormat() {

        boolean isZero = false;
        BigDecimal val = root.res.getVal();
        String valStr = val.toPlainString();
        //.0 ..0 情况
        if (valStr.endsWith(".0") || valStr.matches("-*\\d+\\.[0-9]00*")) {
            int index = valStr.indexOf(".");
            switch (valStr.charAt(index + 1)) {
                case '0':
                    valStr = valStr.substring(0, index);
                    break;
                default:
                    valStr = valStr.substring(0, index + 2);
                    break;
            }
            root.res.setVal(new BigDecimal(valStr));
        } else {
            int index = valStr.indexOf(".");
            String prefix = "";
            String newVal = "";
            if (index != -1) {
                if (valStr.startsWith("0.") || valStr.startsWith("-0.")) {
                    //截取前缀
                    prefix = valStr.substring(0, index + 1);
                    //替换
                    newVal = valStr.replaceFirst("-*0\\.", "1.");
                    val = new BigDecimal(newVal);
                    isZero = true;
                }
                if (valStr.startsWith("-")) {
                    index--;
                }
                val = val.round(new MathContext(index + 2, RoundingMode.HALF_UP));
                if (isZero) {
                    String newStr = val.toPlainString().replaceFirst("1\\.", prefix);
                    val = new BigDecimal(newStr);
                }
                root.res.setVal(val);
            }
        }
    }


    @Override
    public String toString() {
        String res =  printExpression(root);
        //进一步格式化，给符号加上括号
        res = res.trim();
        String[] s = res.split(" ");
        StringBuilder sb = new StringBuilder(s[0]+" ");

        for (int i = 1 ;i <s.length;i++) {
            if (!s[i].equals("-") && s[i].startsWith("-")) {
                s[i]  = "(" + s[i] + ")";
            }
            sb.append(s[i]+" ");
        }

        return sb.toString();
    }

    /**
     * 中序打印root
     *
     * @param node
     * @return
     */
    private String printExpression(Node node) {
        if (node == null) {
            return " ";
        }
        String left = printExpression(node.left);
        String mid = node.toString();

        if (node.left instanceof OperatorNode && node instanceof OperatorNode) {
//            if ((OperatorNode) ((OperatorNode) node.left).isAddOrSub()
            if (OperatorNode.leftBrackets(((OperatorNode) node.left).operator,
                    ((OperatorNode) node).operator)) {
                left = " " + Operator.LEFT_BRACKETS.getOperationName() + left + Operator.RIGHT_BRACKETS.getOperationName() + " ";
            }
        }

        String right = printExpression(node.right);
        if (node.right instanceof OperatorNode && node instanceof OperatorNode) {
            if (OperatorNode.rightBrackets(((OperatorNode) node.right).operator,
                    ((OperatorNode) node).operator)) {
                right = " " + Operator.LEFT_BRACKETS.getOperationName() + right + Operator.RIGHT_BRACKETS.getOperationName() + " ";
            }
        }
        return left + mid + right;
    }




    public String getRes() {
        OperatorNode node = (OperatorNode) root;
        return node.res.toString();
    }


}
