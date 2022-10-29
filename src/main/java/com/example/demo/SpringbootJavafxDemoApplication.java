package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@MapperScan("com.example.demo.sample.mapper")
@SpringBootApplication
public class SpringbootJavafxDemoApplication extends Application {

    public static ApplicationContext applicationContext;
     //用springboot来管理JavaFx的controller
    public static FXMLLoader loadFxml(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(SpringbootJavafxDemoApplication.class.getResource(fxmlPath));

        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(loadFxml("/sample/doLogin.fxml").load());
        scene.setCursor(new ImageCursor(new Image("/imgs/cursor.png")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("四则运算自测系统");
        primaryStage.getIcons().add(new Image("/imgs/icon.png"));
       primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(SpringbootJavafxDemoApplication.class, args);
        Application.launch(args);
    }

}
