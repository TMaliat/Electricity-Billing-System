package com.example.electricity;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView admin_logo, img_icon, exit_img, login_img;
    @FXML
    private TextField adminName_field, text_slide_field;
    @FXML
    private PasswordField passAdmin_field;
    @FXML
    private Text warning_text;
    @FXML
    private Button exit, login_btn;
    @FXML
    private Rectangle rect1;
    Stage stage;
    Scene scene;
    public Connection c;
    private int count_text_slide = 1;
    public int count = 0;

    public void login(ActionEvent event) throws SQLException, IOException {

        connect c = new connect();
        Connection con = c.getConnection();
        String name_field = adminName_field.getText();
        String pass_field = passAdmin_field.getText();
        String sql = "SELECT * FROM admin_login WHERE name ='" + name_field + "' AND password = '"+pass_field+"'";
        //r = c.s.executeQuery(sql);
        try{
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery(sql);
            while (result.next()){
                String name = result.getString("name");
                String password = result.getString("password");
                if(name_field.equals(name) && pass_field.equals(password)){
                    log_in(event);
                    //break;
                }
                else{
                    warning_text.setText("Username or Password is Incorrect!");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void log_in(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) login_btn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin_UI.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void close_onExit(){
        ButtonType good_btn = new ButtonType("Quit");
        ButtonType bad_btn = new ButtonType("Cancel");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", good_btn, bad_btn);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to quit?");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().setStyle("-fx-background-color: #2e9cca; -fx-border-color: #2e9cca; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-width: 10;");
        alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(res -> {
            if (res.equals(good_btn)) {
                Stage stage1 = (Stage) exit.getScene().getWindow();
                stage1.close();
            }
        });
    }

    public void text_slide(){
        String[] facts = new String[6];
        facts[0] = "100% of the population had access to electricity in Bangladesh as of 2022.";
        facts[1] = "Bangladesh has one national grid with an installed capacity of 25,514 MW.";
        facts [2] = "62.9% of generated electricity of Bangladesh comes from natural gas.";
        facts[3] = "Bangladesh has 15 MW solar energy capacity through rural households.";
        facts[4] = "Bangladesh has 1.9 MW wind power in Kutubdia and Feni.";
        facts[5] = "A solar power plant having capacity of 28 MW has recently started its operation in Teknaf of Cox's Bazar.";
        text_slide_field.setText(facts[0]);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
            text_slide_field.setText(facts[count_text_slide]);
            count_text_slide++;
            if(count_text_slide == 6){
                count_text_slide = 0;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        text_slide();
    }
}