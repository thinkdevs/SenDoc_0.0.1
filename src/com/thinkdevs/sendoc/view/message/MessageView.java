package com.thinkdevs.sendoc.view.message;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.List;

/**
 * Created by m.tikhanovskiy on 09.12.2015.
 */
public class MessageView {
    @FXML
    private SplitPane spMessage;
    @FXML
    private Label lAuthor;
    @FXML
    private Label lDate;
    @FXML
    private TextArea taTextMessage;
    @FXML
    private TextArea taLinks;

    public MessageView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/thinkdevs/sendoc/view/message/messageView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(Message message){
        lAuthor.setText(message.getAuthor());
        lDate.setText(message.getDate().toString());
        taTextMessage.setText(message.getTextMessage());
        List<String> links = message.getLinks();
        if(null != links) {
            StringBuilder sbLinks = new StringBuilder("");
            for (String link : links){
                sbLinks.append(link);
                sbLinks.append("\n");
            }
            taLinks.setText(String.valueOf(sbLinks));
        }
    }

    public Node getView(){
        return spMessage;
    }
}
