package com.thinkdevs.sendoc.view.message;

import com.thinkdevs.sendoc.model.Message;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;

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
            this.setPadding(new Insets(10));
            setGraphic(messageView.getView());
        }
    }
}
