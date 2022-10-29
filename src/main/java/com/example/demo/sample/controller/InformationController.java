package com.example.demo.sample.controller;

import com.example.demo.SpringbootJavafxDemoApplication;
import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.Difficulty;
import com.example.demo.sample.model.Expression;
import com.example.demo.sample.model.User;
import com.example.demo.sample.utils.Range;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 2hu0
 */

@Controller
public class InformationController {
    @Autowired
    public UserMapper userMapper;

    public static Difficulty difficulty;

    public Expression expression;
    //问题列表
    public List<String> questionList;
    //答案列表
    public List<String> resList;

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
    /**
     * 显示当前时间
     */
    @FXML
    public Label dateLabel;
    /**
     * 数据分析(近十次)
     */
    @FXML
    public LineChart<String,Number> analysisChart;
    /**
     * 进入习题界面
     */


    /**
     * 生成题目列表
     */
    public void createQuestions() {
         difficulty = new Difficulty();
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
        showDate();
        showChart();
       //得到登录对象
        User loginUser = LoginController.loginUser;
        welcomeText.setText("       Hello, " + loginUser.getUsername());
    }

    /**
     * 注销登录
     * @param event
     */
    public void doLogout(ActionEvent event) throws IOException {
        LoginController.loginUser = null;
        //重新加载
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/doLogin.fxml").load(), 1000, 800);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("/imgs/icon.png"));
//        stage.hide();
        stage.setScene(scene);
//        stage.show();
    }

    /**
     * 去做题界面
     * @param event
     */
    public void create(ActionEvent event) throws IOException {
        //1.挂载数据
        mountData();
        //2.切换场景
        gotoExercises(event);
//        cancleTextArea();
//        createQuestions();
//        for (int i = 0 ; i <questionList.size();i++) {
//            System.out.println(questionList.get(i));
//            System.out.println("=============");
//        }
    }



    /**
     * 挂载数据
     */
    private void mountData() {
        difficulty = new Difficulty();
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
    }

    /**
     * 去做题的那个场景
     */
    private void gotoExercises(ActionEvent event) throws IOException {
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/main.fxml").load(), 1400, 980);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaxWidth(1400);
        stage.getIcons().add(new Image("/imgs/icon.png"));
//        stage.hide();
        stage.setScene(scene);
//        stage.setResizable(true);
//        stage.setFullScreen(true);
//        stage.show();
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
     *
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
     * 显示当前时间
     */
    public void showDate() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * 最近十次的提交记录
     */
    public void showChart() {
        XYChart.Series<String,Number> xy = new XYChart.Series<>();
        xy.setName("point");
        XYChart.Data<String,Number> d1 = new XYChart.Data<>("1",1);
        XYChart.Data<String,Number> d2 = new XYChart.Data<>("2",2);
        XYChart.Data<String,Number> d3 = new XYChart.Data<>("3",3);
        XYChart.Data<String,Number> d4 = new XYChart.Data<>("4",4);
        XYChart.Data<String,Number> d5 = new XYChart.Data<>("5",5);
        XYChart.Data<String,Number> d6 = new XYChart.Data<>("6",6);
        XYChart.Data<String,Number> d7 = new XYChart.Data<>("7",7);
        XYChart.Data<String,Number> d8 = new XYChart.Data<>("8",8);
        XYChart.Data<String,Number> d9 = new XYChart.Data<>("9",9);
        XYChart.Data<String,Number> d10 = new XYChart.Data<>("10",10);
        xy.getData().add(d1);
        xy.getData().add(d2);
        xy.getData().add(d3);
        xy.getData().add(d4);
        xy.getData().add(d5);
        xy.getData().add(d6);
        xy.getData().add(d7);
        xy.getData().add(d8);
        xy.getData().add(d9);
        xy.getData().add(d10);
        analysisChart.getData().add(xy);
    }
}
