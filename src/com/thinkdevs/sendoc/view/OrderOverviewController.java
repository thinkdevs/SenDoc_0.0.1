package com.thinkdevs.sendoc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.thinkdevs.sendoc.Main;
import com.thinkdevs.sendoc.model.Order;

/**
 * Model class for Order
 *
 * @author Maxim Tikhanovskiy
 */
public class OrderOverviewController {
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderNameColumn;

    @FXML
    private Label orderNameLable;

    @FXML
    private Label orderUrlLabel;

    private Main main;

    public OrderOverviewController() {
    }

    @FXML
    private void initialize(){
        orderNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showOrderHistory(null);
        ordersTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showOrderHistory(newValue)));
    }

    public void setMain(Main main) {
        this.main = main;
        ordersTable.setItems(main.getOrdersData());
    }


    private void showOrderHistory(Order order){
        if(order != null){
            orderUrlLabel.setText(order.getUrl());
        }
        else {
            orderUrlLabel.setText("");
        }
    }

}
