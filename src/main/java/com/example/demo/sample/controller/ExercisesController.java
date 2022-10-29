package com.example.demo.sample.controller;

import com.example.demo.SpringbootJavafxDemoApplication;
import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.Difficulty;
import com.example.demo.sample.model.Expression;
import com.example.demo.sample.utils.Range;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 做题界面
 */
@Controller
public class ExercisesController {
    @Autowired
    public UserMapper userMapper;

    public Expression expression;
    //问题列表
    public List<String> questionList;
    //答案列表
    public List<String> resList;
    //右侧题目信息
    public List<String> rightInfo;

    //存放对错
    public List<String> myRes = new ArrayList<>();
    /**
     * 存放题目的textArea
     */
    @FXML
    public JFXTextArea textArea1;
    @FXML
    public JFXTextArea textArea2;
    @FXML
    public JFXTextArea textArea3;
    @FXML
    public JFXTextArea textArea4;
    @FXML
    public JFXTextArea textArea5;
    @FXML
    public JFXTextArea textArea6;
    @FXML
    public JFXTextArea textArea7;
    @FXML
    public JFXTextArea textArea8;
    @FXML
    public JFXTextArea textArea9;
    @FXML
    public JFXTextArea textArea10;
    /**
     * 存放用户输入答案的地方
     */
    @FXML
    public JFXTextField testField1;
    @FXML
    public JFXTextField testField2;
    @FXML
    public JFXTextField testField4;
    @FXML
    public JFXTextField testField3;
    @FXML
    public JFXTextField testField5;
    @FXML
    public JFXTextField testField7;
    @FXML
    public JFXTextField testField9;
    @FXML
    public JFXTextField testField6;
    @FXML
    public JFXTextField testField10;
    @FXML
    public JFXTextField testField8;

    /**
     * 是否含有负数
     */
    @FXML
    public JFXCheckBox negativeControl;
    /**
     * 是否含有小数
     */
    @FXML
    public JFXCheckBox decimalControl;
    /**
     * 是否含有括号
     */
    @FXML
    public JFXCheckBox bracketsControl;
    /**
     * 运算符数
     */
    @FXML
    public JFXTextField jfxOperatorNum;
    /**
     * 数据范围
     */
    @FXML
    public JFXTextField jfxRangeNum;
    /**
     * 选择题数
     */
    @FXML
    public JFXTextField jfxChoiceNum;
    /**
     * 填空题数
     */
    @FXML
    public JFXTextField jfxFillNum;
    /**
     * 判断题数
     */
    @FXML
    public JFXTextField jfxJudgeNum;
    @FXML
    public JFXTextArea welcomeText;

    /**
     * 显示得分信息
     */
    @FXML
    public Label pointLabel;
    @FXML
    public Label questionLab1;
    @FXML
    public Label questionLab3;
    @FXML
    public Label questionLab4;
    @FXML
    public Label questionLab5;
    @FXML
    public Label questionLab6;
    @FXML
    public Label questionLab7;
    @FXML
    public Label questionLab8;
    @FXML
    public Label questionLab9;
    @FXML
    public Label questionLab10;
    @FXML
    public Label questionLab2;
    @FXML
    public Label rightLab1;
    @FXML
    public Label rightLab10;
    @FXML
    public Label rightLab9;
    @FXML
    public Label rightLab8;
    @FXML
    public Label rightLab7;
    @FXML
    public Label rightLab6;
    @FXML
    public Label rightLab5;
    @FXML
    public Label rightLab4;
    @FXML
    public Label rightLab2;
    @FXML
    public Label rightLab3;
    @FXML
    public Label type2;
    @FXML
    public Label type3;
    @FXML
    public Label type1;

    /**
     * 生成题目列表
     */
    public void createQuestions() {
        Difficulty difficulty = new Difficulty();
        //是否有小数
        difficulty.setHasDecimal(decimalControl.isSelected());
        //是否含有负数
        difficulty.setHasNegative(negativeControl.isSelected());
        String operatorNum = jfxOperatorNum.getText();
        String rangeNum = jfxRangeNum.getText();
        String choiceNum = jfxChoiceNum.getText();
        String judgeNum = jfxJudgeNum.getText();
        String fillNum = jfxFillNum.getText();
        //运算数的个数
        difficulty.setMaxOperationNum(Integer.parseInt(operatorNum));
        //运算数的范围
        difficulty.setRange(Range.getRange(rangeNum));
        //选择题个数
        difficulty.setChoiceQuestionNum(Integer.parseInt(choiceNum));
        //判断题个数
        difficulty.setJudgeQuestionNum(Integer.parseInt(judgeNum));
        //填空题个数
        difficulty.setFillBlankQuestionNum(Integer.parseInt(fillNum));
        //是否含有括号 todo
        difficulty.setHasBrackets(bracketsControl.isSelected());

        expression = new Expression(difficulty);
        //生成
        expression.getExpressionMap();
        expression.createQuestions();
        //得到题目
        questionList = expression.getQuestionList();
        //得到答案
        resList = expression.getAnswers();
    }

    /**
     * 加载时候自动挂载
     */
    @FXML
    public void initialize() {
        //展示左侧
        showQuestions();
        showQuestionTypes();
        //展示右侧
        showRight();

    }

    /**
     * 展示题目类型
     */
    private void showQuestionTypes() {
        int choiceNum = InformationController.difficulty.getChoiceQuestionNum();
        int judgeNum  = InformationController.difficulty.getJudgeQuestionNum();
        int fillNum = InformationController.difficulty.getFillBlankQuestionNum();
        if (choiceNum!=0) {
            type1.setText("选择题:");
            type1.setVisible(true);
        }
        if (judgeNum!=0) {
            type2.setText("判断题:");
            type2.setLayoutX(type1.getLayoutX());
            type2.setLayoutY(type1.getLayoutY() + choiceNum*60);
            type2.setVisible(true);
        }
        if (fillNum!=0) {
            type3.setVisible(true);
            type3.setText("填空题:");
            type3.setLayoutX(type1.getLayoutX());
            type3.setLayoutY(type1.getLayoutY() + (choiceNum + judgeNum)*60);
        }



    }

    /**
     * 展示题目右边的选项  括号等信息
     */
    private void showRight() {
        rightInfo = expression.getQuestionList();
        rightLab1.setText(rightInfo.get(0));
        rightLab2.setText(rightInfo.get(1));
        rightLab3.setText(rightInfo.get(2));
        rightLab4.setText(rightInfo.get(3));
        rightLab5.setText(rightInfo.get(4));
        rightLab6.setText(rightInfo.get(5));
        rightLab7.setText(rightInfo.get(6));
        rightLab8.setText(rightInfo.get(7));
        rightLab9.setText(rightInfo.get(8));
        rightLab10.setText(rightInfo.get(9));
    }

    /**
     * 显示数据
     */
    private void showQuestions() {
        expression = new Expression(InformationController.difficulty);
        expression.getExpressionMap();
        expression.createQuestions();
        questionList =  expression.getExpressions();

        questionList.forEach(System.out::println);
        questionLab1.setText(questionList.get(0));
        questionLab2.setText(questionList.get(1));
        questionLab3.setText(questionList.get(2));
        questionLab4.setText(questionList.get(3));
        questionLab5.setText(questionList.get(4));
        questionLab6.setText(questionList.get(5));
        questionLab7.setText(questionList.get(6));
        questionLab8.setText(questionList.get(7));
        questionLab9.setText(questionList.get(8));
        questionLab10.setText(questionList.get(9));
    }



    /**
     * 注销登录
     * @param event
     */
    public void doLogout(ActionEvent event) throws IOException {

        //重新加载
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/doLogin.fxml").load(), 1000, 800);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("/imgs/icon.png"));
//        stage.hide();
        stage.setScene(scene);
//        stage.show();
    }

    public void create(ActionEvent event) {
        createQuestions();
        resList.forEach(System.out::println);
    }



    /**
     * 验证答案
     */
    public void verify() {
        //得到答案
        List<String> questionAnswer = expression.getQuestionAnswer();
        questionAnswer.forEach(System.out::println);
        if (testField1.getText().equals(questionAnswer.get(0))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField2.getText().equals(questionAnswer.get(1))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField3.getText().equals(questionAnswer.get(2))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField4.getText().equals(questionAnswer.get(3))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField5.getText().equals(questionAnswer.get(4))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField6.getText().equals(questionAnswer.get(5))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField7.getText().equals(questionAnswer.get(6))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
        if (testField8.getText().equals(questionAnswer.get(7))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }

        if (testField9.getText().equals(questionAnswer.get(8))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }

        if (testField10.getText().equals(questionAnswer.get(9))) {
            myRes.add("1");
        }else {
            myRes.add("0");
        }
    }

    /**
     * 批改答案
     * @param event
     */
    public void correctResult(ActionEvent event) {
        int point = 0;
        verify();
        //得分
        for (String str :myRes) {
            if ("1".equals(str)){
                point++;
            }
        }
        pointLabel.setText(pointLabel.getText() +"\n"+"       " +point);
        pointLabel.setVisible(true);
    }

    /**
     * 返回到信息页面
     * @param event
     * @throws IOException
     */
    public void backInfoScene(ActionEvent event) throws IOException {
        //        LoginController.loginUser = null;
        //重新加载
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/info.fxml").load(), 1000, 900);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("/imgs/icon.png"));
        stage.setScene(scene);
    }
}
