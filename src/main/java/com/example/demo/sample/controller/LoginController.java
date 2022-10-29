package com.example.demo.sample.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.SpringbootJavafxDemoApplication;
import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.User;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;


/**
 * @author 2hu0
 */
@Controller
public class LoginController {

    @Autowired
    private  UserMapper userMapper;

    public static User loginUser;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;
    /**
     * register
     */
    @FXML
    private JFXTextField registerNameField;

    @FXML
    private JFXPasswordField registerPassField;

    @FXML
    private AnchorPane context;

    @FXML
    private JFXDialog errorDialog;
    /**
     * 注册时候的重试提醒
     */
    @FXML
    public Label noticeLabel;

    @FXML
    private JFXDialog registerDialog;

    private StackPane dialogStackPane;



    /**
     * 用户登录
     *
     * @param actionEvent
     */

    public void doLogin(ActionEvent actionEvent) throws IOException {
        String name = usernameField.getText();
        String password = passwordField.getText();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if ("".equals(name) || "".equals(password)) {
            alert(errorDialog);
        } else {
            queryWrapper.eq("user_name", name);
            System.out.println("userMapper = " + userMapper);
            User user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                alert(errorDialog);
            } else {
                if (user.getPassword().equals(password)) {
                    usernameField.setText("");
                    passwordField.setText("");
                    loginUser = user;
                    //进入新的scene
                    changeToBody(actionEvent);
                } else {
                    usernameField.setText("");
                    passwordField.setText("");
                    alert(errorDialog);
                }
            }
        }
    }

    /**
     * 弹出JFXDialog弹窗
     */
    public void alert(JFXDialog dialog) {
        addDialogStackPane();
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show(dialogStackPane);
    }

    /**
     * 关闭JFXDialog弹窗
     */
    public void close(ActionEvent actionEvent) {
        System.out.println(1);
        errorDialog.close();
        dialogStackPane.getChildren().remove(errorDialog);
        context.getChildren().remove(dialogStackPane);
    }

    public void removeContext() {
        context.getChildren().remove(dialogStackPane);
    }

    /**
     * 注册弹窗,并且关闭弹窗
     */
    public void doRegister(ActionEvent actionEvent) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println("IAM");
        String username = registerNameField.getText();
        String password = registerPassField.getText();
        //error
        if (!checkRegisterUser(username, password)) {
            //todo
            clear();
        } else {
            queryWrapper.eq("user_name", username);
            User oldUser = userMapper.selectOne(queryWrapper);
            //error
            if (oldUser != null) {
                //todo
                clear();
                exitRegister(actionEvent);
            } else {
                //插入到数据库里
                User user = new User(username, password);
                System.out.println("insert");
                //userMapper.insert(user);
                registerDialog.close();
                dialogStackPane.getChildren().remove(registerDialog);
                context.getChildren().remove(dialogStackPane);
            }
        }
    }

    /**
     * 新增JFXDialog弹窗容器
     */

    private void addDialogStackPane() {

        dialogStackPane = new StackPane();
        dialogStackPane.setPrefHeight(context.getHeight());
        dialogStackPane.setPrefWidth(context.getWidth());
        context.getChildren().add(dialogStackPane);
    }

    /**
     * 进入info.fxml界面
     *
     * @param event
     * @throws IOException
     */
    private void changeToBody(ActionEvent event) throws IOException {
        Scene scene = new Scene(SpringbootJavafxDemoApplication.loadFxml("/sample/info.fxml").load(), 1000, 900);
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaxWidth(1200);
        stage.getIcons().add(new Image("/imgs/icon.png"));
        stage.setScene(scene);
    }


    /**
     * 开启注册弹窗
     *
     * @param event
     */
    public void openRegisterDialog(ActionEvent event) {
        alert(registerDialog);
    }

    /**
     * 退出注册弹窗
     *
     * @param event
     */
    public void exitRegister(ActionEvent event) {
        noticeLabel.setVisible(false);
        registerDialog.close();
        dialogStackPane.getChildren().remove(registerDialog);
        context.getChildren().remove(dialogStackPane);
    }

    /**
     * check注册用户信息的合法性
     */
    private boolean checkRegisterUser(String username, String password) {
        if ("".equals(username) || "".equals(password)) {
            return false;
        }
        if (username.length() < 3 || password.length() < 6) {
            return false;
        }
        //判断是否为数字
        for (char ch : username.toCharArray()) {
            if (!Character.isDigit(ch) && !Character.isLetter(ch)) {
                return false;
            }
        }

        return true;
    }

    public void clear() {
        registerPassField.setText("");
        registerNameField.setText("");
        noticeLabel.setVisible(true);
    }

    public void initialize() {


    }


}
