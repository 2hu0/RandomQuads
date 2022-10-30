package com.example.demo.sample.controller;

import com.example.demo.SpringbootJavafxDemoApplication;
import com.example.demo.sample.mapper.RecordMapper;
import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.ExercisesResult;
import com.example.demo.sample.model.Expression;
import com.example.demo.sample.model.Record;
import com.example.demo.sample.model.User;
import com.example.demo.sample.utils.FileUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 做题界面
 */
@Controller
public class ExercisesController {

    @Resource
    public UserMapper userMapper;

    @Resource
    public RecordMapper recordMapper;
    /**
     * 问题
     */
    public Expression expression;
    /**
     * 提交结果
     */
    public ExercisesResult exercisesResult;
    /**
     * 试题分数
     */
    public int point = 0;
    /**
     * 问题列表
     */
    public List<String> questionList;
    /**
     * 答案列表
     */
    List<String> questionAnswer;
    /**
     * 右侧题目信息
     */
    public List<String> rightInfo;

    /**
     * 完整信息
     */
    public List<String> fullVersionQues;

    /**
     * 存放对错
     */
    public List<String> isRight = new ArrayList<>();

    //存放我的结果
    public List<String> myRes = new ArrayList<>();

    //存放错题
    public List<String> errorQuestion = new ArrayList<>();

    //提交记录信息
    public Record record;


    @FXML
    public JFXTextField welcomeText;

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
    @FXML
    public Label dateTime;

    @FXML
    public JFXButton submitButton;
    @FXML
    public JFXTextField resField1;
    @FXML
    public JFXTextField resField2;
    @FXML
    public JFXTextField resField3;
    @FXML
    public JFXTextField resField4;
    @FXML
    public JFXTextField resField5;
    @FXML
    public JFXTextField resField10;
    @FXML
    public JFXTextField resField9;
    @FXML
    public JFXTextField resField8;
    @FXML
    public JFXTextField resField7;
    @FXML
    public JFXTextField resField6;

    /**
     * 生成题目列表
     */

    /**
     * 加载时候自动挂载
     */
    @FXML
    public void initialize() {
        //展示信息
        showInfo();
        //展示时间
        showTime();
        //展示左侧
        showQuestions();
        showQuestionTypes();
        //展示右侧
        showRight();
    }


    /**
     * 展示信息
     */
    private void showInfo() {
        welcomeText.setText("Hello, " + LoginController.loginUser.getUsername());
        welcomeText.setEditable(false);
    }

    /**
     * 展示时间
     */
    private void showTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * 展示题目类型
     */
    private void showQuestionTypes() {
        int choiceNum = InformationController.difficulty.getChoiceQuestionNum();
        int judgeNum = InformationController.difficulty.getJudgeQuestionNum();
        int fillNum = InformationController.difficulty.getFillBlankQuestionNum();
        if (choiceNum != 0) {
            type1.setText("选择题:");
            type1.setVisible(true);
        }
        if (judgeNum != 0) {
            type2.setText("判断题:");
            type2.setLayoutX(type1.getLayoutX());
            type2.setLayoutY(type1.getLayoutY() + choiceNum * 60);
            type2.setVisible(true);
        }
        if (fillNum != 0) {
            type3.setVisible(true);
            type3.setText("填空题:");
            type3.setLayoutX(type1.getLayoutX());
            type3.setLayoutY(type1.getLayoutY() + (choiceNum + judgeNum) * 60);
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
        questionList = expression.getExpressions();

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
     *
     * @param event
     */
    public void doLogout(ActionEvent event) throws IOException {

        //重新加载
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/doLogin.fxml").load(), 1000, 800);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("/imgs/icon.png"));
        stage.setScene(scene);
    }


    /**
     * 验证答案
     */
    public void verify() {
        fullVersionQues = expression.getFullVersionQues();
        //得到答案
        questionAnswer = expression.getQuestionAnswer();
        System.out.println("1111111111111111111111");
        questionAnswer.forEach(System.out::println);
        System.out.println("1111111111111111111111");
        if (resField1.getText().equals(questionAnswer.get(0))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("1.  " + fullVersionQues.get(0));
        }
        myRes.add(resField1.getText());
        if (resField2.getText().equals(questionAnswer.get(1))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("2.  " + fullVersionQues.get(1));
        }
        myRes.add(resField2.getText());
        if (resField3.getText().equals(questionAnswer.get(2))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("3.  " + fullVersionQues.get(2));
        }
        myRes.add(resField3.getText());
        if (resField4.getText().equals(questionAnswer.get(3))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("4.  " + fullVersionQues.get(3));
        }
        myRes.add(resField4.getText());
        if (resField5.getText().equals(questionAnswer.get(4))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("5.  " + fullVersionQues.get(4));
        }
        myRes.add(resField5.getText());
        if (resField6.getText().equals(questionAnswer.get(5))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("6.  " + fullVersionQues.get(5));
        }
        myRes.add(resField6.getText());
        if (resField7.getText().equals(questionAnswer.get(6))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("7.  " + fullVersionQues.get(6));
        }
        myRes.add(resField7.getText());
        if (resField8.getText().equals(questionAnswer.get(7))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("8.  " + fullVersionQues.get(7));
        }
        myRes.add(resField8.getText());

        if (resField9.getText().equals(questionAnswer.get(8))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("9.  " + fullVersionQues.get(8));
        }
        myRes.add(resField9.getText());

        if (resField10.getText().equals(questionAnswer.get(9))) {
            point++;
            isRight.add("1");
        } else {
            isRight.add("0");
            errorQuestion.add("10. " + fullVersionQues.get(9));
        }
        myRes.add(resField10.getText());
    }

    /**
     * 批改答案
     *
     * @param event
     */
    public void correctResult(ActionEvent event) throws IOException {
        verify();
        pointLabel.setText(pointLabel.getText() + point);
        pointLabel.setVisible(true);
        submitButton.setDisable(true);
        //汇总数据
        generateUserData();
    }

    /**
     * 汇总数据 准备写入文件和数据库
     */
    @Transactional
    public void generateUserData() throws IOException {
        // 1.写入文件
        exercisesResult = new ExercisesResult();
        exercisesResult.setDate(new Date());
        exercisesResult.setUser(LoginController.loginUser);
        exercisesResult.setQuestionList(fullVersionQues);
        exercisesResult.setPoint(point);
        exercisesResult.setMyAnswer(myRes);
        exercisesResult.setQuestionAnswer(questionAnswer);
        exercisesResult.setErrorQuestion(errorQuestion);
        FileUtils.writeFile(exercisesResult.getUser().getUsername(), exercisesResult.toString());
        //2.更新用户信息数据库
        User user = LoginController.loginUser;
        //做题数目加10
        user.setCompletedExercises(user.getCompletedExercises() + 10);
        //更新错误题目数量
        user.setErrorExercises(user.getErrorExercises() + 10 - point);
        userMapper.updateById(user);
        //3.提交记录写入数据库
        record = new Record();
        record.setRecordName(LoginController.loginUser.getUsername());
        record.setRecordPoint(point);
        record.setRecordTime(new Date());
        recordMapper.insert(record);
    }

    /**
     * 返回到信息页面
     *
     * @param event
     * @throws IOException
     */
    public void backInfoScene(ActionEvent event) throws IOException {
        //重新加载
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/info.fxml").load(), 1000, 900);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("/imgs/icon.png"));
        stage.setScene(scene);
    }
}
