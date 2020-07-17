/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MenuController implements Initializable {
    public TableView<ModelTable> menu;
    public TableView<ModelTable> cart;
    public TableColumn<ModelTable, String> citem;
    public TableColumn<ModelTable, String> cprice;
    public TableColumn<ModelTable, String> item;
    public TableColumn<ModelTable, String> price;
    public int num;
    public ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    public ObservableList<ModelTable> oblist1 = FXCollections.observableArrayList();
    public void changeAllOrderView(ActionEvent event) throws IOException {
	Parent createAccountParent = FXMLLoader.load(getClass().getResource("AllOrderView.fxml"));
	Scene createAccountScene = new Scene(createAccountParent);

	// This line gets the Stage information
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setScene(createAccountScene);
	stage.show();
    }
    public void Add(ActionEvent actionEvent){
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        ObservableList<ModelTable> selectedItem;
        selectedItem = menu.getSelectionModel().getSelectedItems();
        selectedItem.forEach(oblist1::add);
        cart.setItems(oblist1);
        
    }
    public void Del(ActionEvent actionEvent){
        citem.setCellValueFactory(new PropertyValueFactory<>("name"));
        cprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        ObservableList<ModelTable> selectedItem;
        selectedItem = cart.getSelectionModel().getSelectedItems();
        selectedItem.forEach(oblist1::remove);
        cart.setItems(oblist1);
    }
    public void checkout(){
//        oblist1.forEach(s);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM foods");
            while(result.next()){
                oblist.add(new ModelTable(result.getString(2),result.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        item.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        menu.setItems(oblist);
    }    
    
}
