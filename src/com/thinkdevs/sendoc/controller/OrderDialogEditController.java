package com.thinkdevs.sendoc.controller;

import com.thinkdevs.sendoc.model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Order DialogEdit controller
 *
 * @author Maxim Tikhanovskiy
 */
public class OrderDialogEditController {

    @FXML
    private TextField bidNumber;
    @FXML
    private TextField dateOpen;
    @FXML
    private TextField manager;
    @FXML
    private TextField customer;
    @FXML
    private TextField orderNumber;
    @FXML
    private TextField volumeNumber;
    @FXML
    private TextField dateShipping;

    private Stage dialogStage;
    private Order order;
    private boolean okClicked = false;


    @FXML
    private void initialize(){

    }

    /**
     * Sets the stage of this dialog
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the Order to be edited in the dialog.
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;

        bidNumber.setText(order.getBidNumber());
        dateOpen.setText(order.getDateOpen());
        manager.setText(order.getManager());
        customer.setText(order.getCustomer());
        orderNumber.setText(order.getOrderNumber());
        volumeNumber.setText(order.getVolumeNumber());
        dateShipping.setText(order.getDateShipping());
    }

    /**
     * Return true if user clicked 'OK', false otherwise
     * @return
     */
    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * Called when the user clicks 'OK'
     */
    @FXML
    private void handleOk(){
        if(isInputValid()){
            order.setBidNumber(bidNumber.getText());
            order.setDateOpen(dateOpen.getText());
            order.setManager(manager.getText());
            order.setCustomer(customer.getText());
            order.setOrderNumber(orderNumber.getText());
            order.setVolumeNumber(volumeNumber.getText());
            order.setDateShipping(dateShipping.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks 'Cancel'
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }


    /**
     * Validates user input in the text fields
     * @return true if the input valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if(null == orderNumber.getText() || orderNumber.getText().length() == 0){
            errorMessage += "Введите номер наряд заказа \n";
        }

        if(errorMessage.length() == 0){
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некоректные данные");
            alert.setHeaderText("Исправте некоректные данные");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
