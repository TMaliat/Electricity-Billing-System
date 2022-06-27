package com.example.electricity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class deleteConn {
    @FXML
    private Button close_del;
    private Connection c;
    private Statement s;
    ResultSet r;
    @FXML
    private PasswordField passAdmin_field;
    @FXML
    private Button submit_pass;
    @FXML
    private Text pass_txt, main_txt;
    @FXML
    private Pane del_pane;
    @FXML
    private TextField inbox_search_field;
    @FXML
    Button search_cus;
    @FXML
    private Text del_cus;
    @FXML
    Button del_cus_per;
    private String c_id;

    public static void show(ActionEvent event) throws IOException {
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(cus_details.class.getResource("delete_conn.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        //stage.setScene(scene);
        try {
            scene.setFill(Color.TRANSPARENT);
            //stage.initStyle(StageStyle.TRANSPARENT);
            // stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene((scene));
        } catch (Exception e) {
            System.out.println(e);
        }
        stage.show();
        // new FadeIn(root).play();
    }

    public void close_del_conn(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) close_del.getScene().getWindow();
        stage1.close();
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("customer_details.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        //new FadeIn(root).play();
    }

    public void pass() throws SQLException {
        String s = passAdmin_field.getText();
        if (s.equals("")) {
            pass_txt.setText("");
            pass_txt.setText("Enter correct password!");
        } else if (s.equals("ptd")) {
            pass_txt.setText("");
            del_pane.setVisible(true);
            //new FadeOut(submit_pass).play();
            // new FadeOut(passAdmin_field).play();
            // new FadeIn(del_pane).play();
            main_txt.setFill(Color.INDIANRED);
            main_txt.setX(160);
            main_txt.setY(30);
            main_txt.setText("Be careful while deleting!!!                    once deleted it can't be undone!");
            passAdmin_field.setVisible(false);
            submit_pass.setVisible(false);
        } else{
            pass_txt.setText("");
            pass_txt.setText("Enter the Correct Password!");
        }
    }

    public void search_customer() {
        if (inbox_search_field.getText().isEmpty()) {
            del_cus.setText("Enter a number!");
        } else {
            try {
                connect c = new connect();

                int count = -1;
                String id = inbox_search_field.getText();
                String count_string = "SELECT COUNT(c_id) FROM user_login WHERE c_id=" + id + ";";
                this.s = this.c.createStatement();
                this.r = this.s.executeQuery(count_string);
                r.next();
                count = r.getInt(1);
                if (count == 0) {
                    del_cus.setText("");
                    del_cus.setText("Customer Does Not Exit!");
                } else {
                    del_cus.setText("");
                    del_cus.setText(c_id + "Customer Found!");
                   // count = 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void del_customer(){

        ButtonType good_btn = new ButtonType("Delete");
        ButtonType bad_btn = new ButtonType("Cancel");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", good_btn, bad_btn);
        // new FadeIn(alert.getDialogPane()).play();
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this connection?");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().setStyle("-fx-background-color: #2e9cca; -fx-border-color: #2e9cca; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-width: 10;");
        alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(res -> {
            if (res.equals(good_btn)) {
                String sql1 = "DELETE FROM CUSTOMER_DETAILS WHERE c_id=" + c_id + ";";
                String sql2 = "DELETE FROM user_login WHERE c_id=" + c_id + ";";
                try {
                    connect c = new connect();
                    this.s = this.c.createStatement();
                    this.s.executeUpdate(sql1);
                    this.s.executeUpdate(sql2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                del_cus.setText("Deleted Successfully!");
            }
        });
    }
}