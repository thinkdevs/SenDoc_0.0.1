package com.thinkdevs.sendoc.view.message;

import com.thinkdevs.sendoc.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Main class for start application
 *
 * @author Maxim Tikhanovskiy
 */
public class MessageListController {
    @FXML
    private ListView<Message> messageList;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
        messageList.setItems(main.getMessages());
    }

    @FXML
    private void initialize() {
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new ListViewMessageCell();
            }
        });
    }

}
