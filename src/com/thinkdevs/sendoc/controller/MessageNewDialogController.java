package com.thinkdevs.sendoc.controller;

import com.thinkdevs.sendoc.Main;
import com.thinkdevs.sendoc.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Order DialogEdit controller
 *
 * @author Maxim Tikhanovskiy
 */
public class MessageNewDialogController {

    @FXML
    private TextField textFieldAuthorMsg;
    @FXML
    private TextField textFieldSubjectMsg;
    @FXML
    private TextArea textAreaTextMsg;
    @FXML
    private ListView<String> listViewFilesMsg;
    @FXML
    private Button buttonAddFileMsg;
    @FXML
    private Button buttonSendMsg;

    private Stage dialogStage;
    private Message message;
    private Main main;
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
    private void handleSend(){
        if(isInputValid()){
            String author = textFieldAuthorMsg.getText();
            String subject = textFieldSubjectMsg.getText();
            String textMsg = textAreaTextMsg.getText();
            List<String> filesMsg = listViewFilesMsg.getItems();
            message = new Message(author, subject, textMsg, filesMsg);
            main.getMessages().add(message);
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

        if(null == textFieldAuthorMsg.getText() || textFieldAuthorMsg.getText().length() == 0){
            errorMessage += "Кто автор? \n";
        }
        if(null == textFieldSubjectMsg.getText() || textFieldSubjectMsg.getText().length() == 0){
            errorMessage += "Введите тему! \n";
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

    @FXML
    private void handleAdd(){

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
