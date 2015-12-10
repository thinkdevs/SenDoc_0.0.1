package com.thinkdevs.sendoc.controller;

import com.thinkdevs.sendoc.view.message.ListViewMessageCell;
import com.thinkdevs.sendoc.model.Message;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.thinkdevs.sendoc.Main;
import com.thinkdevs.sendoc.model.Order;
import javafx.util.Callback;

import java.util.List;

/**
 * Order overview controller
 *
 * @author Maxim Tikhanovskiy
 */
public class OrderOverviewController {

    private List<Order> orders;
    @FXML
    private ListView<Order> ordersList;
    @FXML
    private ListCell<Order> orderNameColumn;
    @FXML
    private ListView<Message> messageList;


    private StringProperty bidNumber;
    private StringProperty orderNumber;
    private StringProperty dateOpen;
    private StringProperty manager;
    private StringProperty customer;
    private StringProperty volumeNumber;
    private StringProperty dateShipping;
    private StringProperty repository;

    @FXML
    private Label orderNumberLabel;
    @FXML
    private Label bidNumberLabel;
    @FXML
    private Label dateOpenLabel;
    @FXML
    private Label dateShippingLabel;
    @FXML
    private Label volumeNumberLabel;
    @FXML
    private Label managerLabel;
    @FXML
    private Label customerLabel;

    private Main main;

    public OrderOverviewController() {
    }

    @FXML
    private void initialize(){
//        orderNameColumn.setCellValueFactory(cellData -> cellData.getValue().orderNumberProperty());
        showOrderHistory(null);
        ordersList.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showOrderHistory(newValue)));

        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new ListViewMessageCell();
            }
        });

    }

    public void setMain(Main main) {
        this.main = main;
        ordersList.setItems(main.getOrdersData());
        messageList.setItems(main.getMessages());
    }


    private void showOrderHistory(Order order){
        if(order != null){
            orderNumberLabel.setText(order.getOrderNumber());
            bidNumberLabel.setText(order.getBidNumber());
            dateOpenLabel.setText(order.getDateOpen());
            dateShippingLabel.setText(order.getDateShipping());
            volumeNumberLabel.setText(order.getVolumeNumber());
            managerLabel.setText(order.getManager());
            customerLabel.setText(order.getCustomer());
        }
        else {
            orderNumberLabel.setText("----");
            bidNumberLabel.setText("----");
            dateOpenLabel.setText("----");
            dateShippingLabel.setText("----");
            volumeNumberLabel.setText("----");
            managerLabel.setText("----");
            customerLabel.setText("----");
        }
    }

    @FXML
    private void handleNewOrder(){
        Order tempOrder = new Order();
        boolean okClicked = main.showOrderEditDialog(tempOrder);
        if(okClicked){
            main.getOrdersData().add(tempOrder);
        }
    }

    @FXML
    private void handleEditOrder(){
        Order selectedOrder = ordersList.getSelectionModel().getSelectedItem();
        if(selectedOrder != null){
            boolean okClicked = main.showOrderEditDialog(selectedOrder);
            if(okClicked){
                showOrderHistory(selectedOrder);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Зазказ не выбран");
            alert.setContentText("Выберете заказ для редактирования из таблицы");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewMessage(){
        System.out.println("new message");
        main.showMessageNewDialog();
    }

}
