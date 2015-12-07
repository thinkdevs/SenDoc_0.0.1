package com.thinkdevs.sendoc.view.homeOverview;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.thinkdevs.sendoc.Main;
import com.thinkdevs.sendoc.model.Order;

/**
 * Order overview controller
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
        orderNameColumn.setCellValueFactory(cellData -> cellData.getValue().orderNumberProperty());
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
            orderUrlLabel.setText(order.getRepository());
        }
        else {

            orderUrlLabel.setText("");
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
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
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

}
