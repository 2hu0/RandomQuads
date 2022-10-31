package com.example.demo;

import com.example.demo.sample.model.Difficulty;
import com.example.demo.sample.model.ExercisesGenerator;
import com.example.demo.sample.model.Expression;
import com.example.demo.sample.model.Operand;
import com.example.demo.sample.utils.RandomUtils;
import com.example.demo.sample.utils.Range;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

public class TestDemo {

    @org.junit.Test
    public void test1() {
        ExercisesGenerator exercisesGenerator = new ExercisesGenerator();
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.TEN);

        difficulty.setHasBrackets(true);
        difficulty.setHasDecimal(true);
        difficulty.setMaxOperationNum(3);

        exercisesGenerator.setDifficulty(difficulty);
        // exercisesGenerator.generator();
    }

    @org.junit.Test
    public void test100() {
        int count = 0;
        while (true) {
            count++;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 10; i++) {
                BigDecimal decimalInHundred = RandomUtils.getDecimalInHundred();
                System.out.println("decimalInHundred = " + decimalInHundred);
                if (!decimalInHundred.toPlainString().contains(".")) {
                    sb.append("1");
                }
            }
            System.out.println("=============");
            if (sb.length() == 10) {
                System.out.println("error");
                System.out.println(count);
                break;
            } else {
                sb = new StringBuilder("");
                System.out.println("sb 置 0 ");
            }
        }
    }

    @org.junit.Test
    public void test1000() {
        int count = 0;
        while (true) {
            count++;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 10; i++) {

                BigDecimal decimalInThousand = RandomUtils.getDecimalInThousand();
                System.out.println("decimalInThousand = " + decimalInThousand);
                if (!decimalInThousand.toPlainString().contains(".")) {
                    sb.append("1");
                }
            }

            System.out.println("=============");
            if (sb.length() == 10) {
                System.out.println("error");
                System.out.println(count);
                break;
            } else {
                sb = new StringBuilder("");
                System.out.println("sb 置 0 ");
            }
        }
    }

    @org.junit.Test
    public void testExpression() {
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.HUNDRED);
        difficulty.setHasDecimal(true);
        difficulty.setMaxOperationNum(4);
        ExercisesGenerator exercisesGenerator = new ExercisesGenerator();
        exercisesGenerator.setDifficulty(difficulty);
        int count = 20000;
        while (count-- > 0) {
            exercisesGenerator.generateNode();
            String string = exercisesGenerator.toString();
            System.out.println("string =" + string + " " + "res = " + exercisesGenerator.getRes());
        }

    }

    @org.junit.Test
    public void testError() {
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.HUNDRED);
        difficulty.setHasDecimal(true);
        difficulty.setMaxOperationNum(3);
        ExercisesGenerator exercisesGenerator = new ExercisesGenerator();
        exercisesGenerator.setDifficulty(difficulty);
        int count = 20000;
        while (count-- > 0) {
            exercisesGenerator.generateNode();
            String string = exercisesGenerator.toString();
            System.out.println("string =" + string + " " + "res = " + exercisesGenerator.getRes());
        }
    }

    @org.junit.Test
    public void testOperand() {
        Operand o1 = new Operand(new BigDecimal("-8"));
        Operand o2 = new Operand(new BigDecimal("19.38"));

//        System.out.println("o1.divide(o2) = " + o1.divide(o2));
        BigDecimal b1 = new BigDecimal("-8");
        BigDecimal b2 = new BigDecimal("19.38");
        BigDecimal divide = b1.divide(b2, 2, RoundingMode.HALF_UP);
        System.out.println("divide = " + divide);
    }

    @org.junit.Test
    public void test2() {

        BigDecimal b1 = new BigDecimal("-1");
        BigDecimal b2 = new BigDecimal("55");

        BigDecimal bigDecimal = new BigDecimal("-1123123.9786");
        String string = bigDecimal.toPlainString();
        int index = string.indexOf(".");

        BigDecimal divide = b1.divide(b2, 10, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(new BigDecimal("3"));
        bigDecimal = bigDecimal.round(new MathContext(index + 1, RoundingMode.HALF_UP));
        System.out.println("bigDecimal = " + bigDecimal);

    }

    /**
     * 测试生成的表达式
     */
    @org.junit.Test
    public void finalTest() {
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.THOUSAND);
        difficulty.setHasDecimal(false);
        difficulty.setMaxOperationNum(8);
        difficulty.setHasNegative(true);
        difficulty.setHasBrackets(false);
        Expression expression = new Expression(difficulty);
        expression.getExpressionMap();
        HashMap<String, String> map = expression.getMap();
        map.forEach((k, v) -> {
            System.out.println(k + "= " + v);
            ;
        });
    }

    @org.junit.Test
    public void testFormat() {
        BigDecimal val = new BigDecimal("-0.983");
        boolean isZero = false;
        //避免0 出现的 bug
        String valStr = val.toPlainString();
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
        }
        if (isZero) {
            String newStr = val.toPlainString().replaceFirst("1\\.", prefix);
            return;
        } else {
            return;
        }

    }

    @org.junit.Test
    public void testString() {
        String valStr = "0.983";
        int index = valStr.indexOf(".");
        String prefix = valStr.substring(0, index);
        System.out.println("valStr.substring(0,index) = " + valStr.substring(0, index));
        //System.out.println("valStr = " + valStr);
        String s = valStr.replaceFirst("-*0\\.", "1.");
//        System.out.println("s = " + s);
    }

    @org.junit.Test
    public void testZero() {
        String s = "123124.00";
        boolean matches = s.matches("-*\\d+\\.[0-9]0");
        System.out.println("matches = " + matches);
        System.out.println("{======");
        System.out.println("s.substring(0,5) = " + s.substring(0, 5));
    }

    @org.junit.Test
    public void testFema() {
        int x = 64 % 3;
        System.out.println("x = " + x);
    }


    @org.junit.Test
    public void testCreateQuestion() {
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.HUNDRED);
        difficulty.setHasDecimal(true);
        difficulty.setMaxOperationNum(3);
        difficulty.setHasNegative(true);
        Expression expression = new Expression(difficulty);
        expression.getExpressionMap();
        List<String> expressions = expression.getExpressions();
//        expressions.forEach(System.out::println);
        int count = 0;
        while (count <= 9) {
            String fill = expression.createChoice(count++);
        }
    }

    @org.junit.Test
    public void testStr() {
        String x = "-3 + 4 + 3 - 4 * 3";
        String list = "A:45 B:12 C:13 D:24";
        x = x + "\n" + list;
        System.out.println(x);
    }

    @org.junit.Test
    public void test() {
        Difficulty difficulty = new Difficulty();
        difficulty.setRange(Range.THOUSAND);
        difficulty.setHasDecimal(true);
        difficulty.setMaxOperationNum(6);
        difficulty.setHasNegative(true);
        difficulty.setChoiceQuestionNum(9);
        difficulty.setJudgeQuestionNum(1);
        difficulty.setFillBlankQuestionNum(0);
        Expression expression = new Expression(difficulty);

            expression.getExpressionMap();
            expression.createQuestions();
            List<String> questionList = expression.getQuestionList();
            for (String str : questionList) {
                System.out.println("str = " + str);
            }

    }

    @Test
    public void testPrint() {
        String x = "( (-292) + (-55) ) * 3 ÷ ( (-763) ÷ ( (-72) - (-157.22) ) )";
        System.out.println(x.length());
    }

    @Test
    public void testRes() {
        BigDecimal decimal = new BigDecimal("-10000");
        System.out.println("decimal = " + decimal);

    }

    @Test
    public  void testStringToInt() {
    }

    @Test
    public void testSql() {

    }

}
