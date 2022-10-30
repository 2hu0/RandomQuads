package com.example.demo.sample.model;

import com.example.demo.sample.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 存放每次练习的结果 ,用于写入文件
 *
 * @author 2hu0
 */
public class ExercisesResult {
    /**
     * 当前用户
     */
    private User user;
    /**
     *
     */
    private int point;
    /**
     * 试题列表
     */
    private List<String> questionList = new ArrayList<>();
    /**
     * 题目答案列表
     */
    private List<String> questionAnswer = new ArrayList<>();
    /**
     * 我的答案列表
     */
    private List<String> myAnswer = new ArrayList<>();
    /**
     * 当前时间
     */
    private Date date = new Date();
    /**
     * 错题列表
     */
    private List<String> errorQuestion = new ArrayList<>();

    public ExercisesResult() {
        this.user = user;
        this.point = point;
        this.questionList = questionList;
        this.questionAnswer = questionAnswer;
        this.myAnswer = myAnswer;
        this.date = date;
        this.errorQuestion = errorQuestion;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }

    public List<String> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(List<String> questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public List<String> getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(List<String> myAnswer) {
        this.myAnswer = myAnswer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getErrorQuestion() {
        return errorQuestion;
    }

    public void setErrorQuestion(List<String> errorQuestion) {
        this.errorQuestion = errorQuestion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("========================================================" + "\n");
        sb.append(DateUtils.strDate(date)).append("   Username : ").append(this.user.getUsername()).append("  point: ").append(point).append("\n");
        //问题
        for (int i = 0; i < questionList.size(); i++) {
            if (i !=9 ) {
                sb.append(i + 1).append(".  ").append(questionList.get(i)).append("\n");
            }else{
                sb.append(i + 1).append(". ").append(questionList.get(i)).append("\n");
            }
        }
        sb.append("\n");
        sb.append("试题答案:" + "\n");
        //试题答案
        for (int i = 0; i < questionAnswer.size(); i++) {
            sb.append(i + 1).append(". ").append(questionAnswer.get(i)).append("  ");
            if (i + 1 == 5) {
                sb.append("\n");
            }
        }
        sb.append("\n");
        //我的答案
        sb.append("我的答案:" + "\n");
        for (int i = 0; i < myAnswer.size(); i++) {
            sb.append(i + 1).append(". ").append(myAnswer.get(i)).append("  ");
            if (i + 1 == 5) {
                sb.append("\n");
            }
        }
        sb.append("\n");
        //错题
        sb.append("错题列表:" + "\n");
        for (int i = 0; i < errorQuestion.size(); i++) {
            sb.append(errorQuestion.get(i)).append("\n");
        }
        sb.append("========================================================" + "\n");
        return sb.toString();
    }
}
