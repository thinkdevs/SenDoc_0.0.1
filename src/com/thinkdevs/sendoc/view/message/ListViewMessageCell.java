package com.thinkdevs.sendoc.view.message;

import javafx.scene.control.ListCell;

/**
 * Created by m.tikhanovskiy on 09.12.2015.
 */
public class ListViewMessageCell extends ListCell<Message> {

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
            MessageView messageView = new MessageView();
            messageView.setInfo(message);
            setGraphic(messageView.getView());
        }
    }
}
