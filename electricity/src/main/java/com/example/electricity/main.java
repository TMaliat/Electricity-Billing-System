package com.example.electricity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("admin_login.fxml"));
        stage.setTitle("EBMS");
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}