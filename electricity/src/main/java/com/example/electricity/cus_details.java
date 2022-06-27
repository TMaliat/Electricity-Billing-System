package com.example.electricity;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class cus_details extends loginController implements Initializable {
    public Button clear_table_btn;
    public Button inbox_search_btn;
    public Button customer_details_btn;
    public Button inbox_show_btn;
    public Button new_conn_btn;

    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private TableView<Customer> cus_table;
    @FXML
    private TableColumn<Customer, Integer> cid_col;
    @FXML
    private TableColumn<Customer, String> name_col;
    @FXML
    private TableColumn<Customer, String> email_col;
    @FXML
    private TableColumn<Customer, String> occ_col;
    @FXML
    private TableColumn<Customer, Integer> phone_col;
    @FXML
    private TableColumn<Customer, String> dob_col;
    @FXML
    private TableColumn<Customer, Integer> flat_col;
    @FXML
    private TableColumn<Customer, String> address_col;
    @FXML
    private TableColumn<Customer, String> pincode_col;
    @FXML
    private TableColumn<Customer, String> division_col;
    @FXML
    private TableColumn<Customer, String> city_col;
    private ObservableList<Customer> list = FXCollections.observableArrayList();
    private int count = 0;
    @FXML
    private Text txt_box;
    @FXML
    private TextField name_search;
    @FXML
    private Button ter_conn;

    public cus_details() {
    }

    // switching between windows
    @Override
    public void my_acc_pane(ActionEvent event) throws IOException {
        super.my_acc_pane(event);
    }

    @Override
    public void inbox(ActionEvent event) throws IOException {
        super.inbox(event);
    }
    public void new_cus(ActionEvent event) throws IOException {
        try {
            Stage stage1 = (Stage) new_conn_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("new_conn_pane.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            //new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void bills_pane(ActionEvent event) throws IOException {
        super.bills_pane(event);
    }

    @Override
    public void customer_details(ActionEvent event) throws IOException {
        super.customer_details(event);
    }

    @Override
    public void logout(ActionEvent event) {
        super.logout(event);
    }
    // end of switching windows methods

    public void show_cus(){
        if(count == 0){
            txt_box.setText("No Customers!!!");
        } else {
            list.clear();
            name_search.setText("");
            txt_box.setText("");
            cus_table.getItems().clear();
            try {
                this.s = this.c.createStatement();
                //CallableStatement callableStatement = c.prepareCall("{CALL CUSTOMER_DETAILS}");
                String s = "SELECT * FROM CUSTOMER_DETAILS";
                this.r = this.s.executeQuery(s);
                while (r.next()) {
                    list.add(new Customer(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("occupation"), r.getBigDecimal("phone"), r.getString("dob"), r.getInt("flat_no"), r.getString("address"), r.getInt("pincode"), r.getString("division"), r.getString("city")));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try {
                cid_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
                name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
                email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
                occ_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOcc()));
                phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
                dob_col.setCellValueFactory(new PropertyValueFactory<>("dob"));
                flat_col.setCellValueFactory(new PropertyValueFactory<>("flat_no"));
                address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
                pincode_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPin_code()));
                division_col.setCellValueFactory(new PropertyValueFactory<>("division"));
                city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
                cus_table.setItems(list);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void search() throws SQLException {
        if(name_search.getText().equals("")){
            txt_box.setText("Enter a name to search!");
        } else {
            cus_table.getItems().clear();
            txt_box.setText("");
            String s = "SELECT * FROM CUSTOMER_DETAILS WHERE name='" + name_search.getText() + "';";
            this.r = this.s.executeQuery(s);
            while (r.next()) {
                list.add(new Customer(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("occupation"), r.getBigDecimal("phone"), r.getString("dob"), r.getInt("flat_no"), r.getString("address"), r.getInt("pincode"), r.getString("division"), r.getString("city")));
            }
            if(list.isEmpty()){
                txt_box.setText("Customer Not Found!");
            } else {
            cid_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
            name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            occ_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOcc()));
            phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
            dob_col.setCellValueFactory(new PropertyValueFactory<>("dob"));
            flat_col.setCellValueFactory(new PropertyValueFactory<>("flat_no"));
            address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
            pincode_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPin_code()));
            division_col.setCellValueFactory(new PropertyValueFactory<>("division"));
            city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
            cus_table.setItems(list);
            }
        }
    }

    public void clear_table(){
        cus_table.getItems().clear();
        txt_box.setText("");
        name_search.setText("");
    }

    public void delete_conn(ActionEvent event){
            ter_conn.setOnAction(e -> {
                try {
                    deleteConn.show(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://localhost/oop", "root", "oopSql");
            String count_string = "SELECT COUNT(c_id) FROM user_login";
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(count_string);
            r.next();
            count = r.getInt(1);
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
