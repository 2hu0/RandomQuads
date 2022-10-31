package com.example.demo.sample.model;

import com.example.demo.sample.utils.RandomUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 表达式
 *
 * @author 2hu0
 */
public class Expression {

    private ExercisesGenerator exercisesGenerator;
    private HashMap<String, String> map;
    /**
     * 问题
     */
    private List<String> expressions = new ArrayList<>();



    public void setQuestionAnswer(List<String> questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    /**
     * 答案 (值)
     */
    private List<String> answers = new ArrayList<>();
    /**
     * 各种题型的答案
     */
    private List<String> questionAnswer = new ArrayList<>();
    /**
     * 存放各种题型的字符串表达形式
     */
    private List<String> questionList = new ArrayList<>();
    /**
     * 存放完整题目
     */
    private List<String> fullVersionQues = new ArrayList<>();

    public List<String> getFullVersionQues() {
        for (int i = 0; i < questionList.size();i++) {
            fullVersionQues.add(addBlank(expressions.get(i)) + questionList.get(i));
        }
        return fullVersionQues;
    }

    public void setFullVersionQues(List<String> fullVersionQues) {
        this.fullVersionQues = fullVersionQues;
    }

    public Expression(Difficulty difficulty) {
        map = new HashMap<>();
        exercisesGenerator = new ExercisesGenerator(difficulty);
    }

    public List<String> getQuestionAnswer() {
        return questionAnswer;
    }



    /**
     * 每十个题目为一组数据
     */
    public void getExpressionMap(){
        int count = 0;
        for (int i = 0; i < 10; i++) {
            //避免出现1 ÷ 0 类似情况
            try {

                exercisesGenerator.generateNode();

            } catch (ArithmeticException ignored) {
                i--;
                continue;
            }

            //控制出现括号的情况()
            if (!exercisesGenerator.getDifficulty().isHasBrackets()) {
                if(!checkHasBrackets(exercisesGenerator.toString())){
                    i--;
                    continue;
                }
            }
            //避免出现0.00 0.0
            if (checkZero(exercisesGenerator.getRes())) {
                i--;
                continue;
            }
            //避免太长的结果
            if (!checkRes(exercisesGenerator.getRes())){
                i--;
                continue;
            }
            //避免太长的运算式子
            if (exercisesGenerator.toString().length()>=50) {
                i--;
                continue;
            }
            //避免重复
            if (map.get(exercisesGenerator.toString()) != null) {
                System.out.println("重复生成了！！！！！");
                System.out.println("重复:" + exercisesGenerator.toString());
                i--;
                continue;
            }
            expressions.add(exercisesGenerator.toString());
            answers.add(exercisesGenerator.getRes());
            map.put(exercisesGenerator.toString(), exercisesGenerator.getRes());
        }
//        System.out.println("失败次数" + count);
    }

    /**
     * 保证不含有括号的情况
     *
     * @param res
     */
    private boolean checkHasBrackets(String res) {
        //处理开头的括号
        if (res.charAt(0) == '(' && res.charAt(1) !='-'){
            return false;
        }
        if (res.contains(" ( ")) {
            return false;
        }
        return true;
    }

    /**
     * 避免长结果
     * @param res
     * @return
     */
    private boolean checkRes(String res) {
        BigDecimal decimal = new BigDecimal(res);
        if (decimal.compareTo(new BigDecimal("10000")) >= 0) {
            return  false;
        }
        if (decimal.compareTo(new BigDecimal("-10000")) <=0) {
            return false;
        }
        return true;
    }

    private boolean checkZero(String res) {
        if ("0.0".equals(res) || "0.00".equals(res)) {
            return true;
        }
        return false;

    }

    public ExercisesGenerator getExercisesGenerator() {
        return exercisesGenerator;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    /**
     * 生成选择题，判断题，填空题
     */
    public void createQuestions() {
        //先获取个数
        int choiceNum = exercisesGenerator.getDifficulty().getChoiceQuestionNum();
        int judgeNum = exercisesGenerator.getDifficulty().getJudgeQuestionNum();

        //处理选择题
        for (int i = 0; i < choiceNum; i++) {
            questionList.add(createChoice(i));
        }
        //处理判断题
        for (int i = choiceNum; i < judgeNum+choiceNum; i++) {
            questionList.add(createJudge(i));
        }
        //处理填空题
        for (int i = choiceNum + judgeNum; i < map.size(); i++) {
            questionList.add(createFill(i));
        }
    }

    /**
     * 生成填空题
     *
     * @param target 把map里的第target条算术式 生成对应的题型
     * @return s
     */
    public String createFill(int target) {
        questionAnswer.add(answers.get(target));
        return "( )";
    }

    /**
     * 生成判断题
     *
     * @return  s
     */
    public String createJudge(int target) {
        boolean control = RandomUtils.getRandomTrueOrFalse();
        if (control) {
            String res = RandomUtils.getErrorRes(answers.get(target));
            expressions.set(target,expressions.get(target) +" = " + res);
            questionAnswer.add("F");
        } else {
            expressions.set(target,expressions.get(target) +" = " +answers.get(target));
            questionAnswer.add("T");
        }

        return "T or F";
    }



    /**
     * 生成选择题
     * @return s
     */
    public String createChoice(int target) {
        int control = RandomUtils.getRandomIntegerInRange(4);
        String answer = answers.get(target);
        String s = "";
        switch (control) {
            case 0:
                s += "A:" + answer+ " B:"+RandomUtils.getErrorRes(answer) +" C:"+RandomUtils.getErrorRes(answer)+" D:"+RandomUtils.getErrorRes(answer);
                questionAnswer.add("A");
                break;
            case 1:
                s += "A:" + RandomUtils.getErrorRes(answer)+ " B:"+answer +" C:"+RandomUtils.getErrorRes(answer)+" D:"+RandomUtils.getErrorRes(answer);
                questionAnswer.add("B");
                break;
            case 2:
                s += "A:" + RandomUtils.getErrorRes(answer)+ " B:"+RandomUtils.getErrorRes(answer) +" C:"+answer+" D:"+RandomUtils.getErrorRes(answer);
                questionAnswer.add("C");
                break;
            case 3:
                s += "A:" + RandomUtils.getErrorRes(answer)+ " B:"+RandomUtils.getErrorRes(answer) +" C:"+RandomUtils.getErrorRes(answer)+" D:"+answer;
                questionAnswer.add("D");
                break;
            default:
                break;
        }
        return s;
    }

    /**
     * 加空格
     * @param str
     * @return
     */
    public String addBlank(String str) {
        if (str.length()!=55) {
            while (str.length()<55) {
                str = str+" ";
            }
        }
        return str;
    }

    public void setExercisesGenerator(ExercisesGenerator exercisesGenerator) {
        this.exercisesGenerator = exercisesGenerator;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getQuestionList() {
        if (questionList.size() != 10) {
            questionList.forEach(System.out::println);
        }
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }
}
