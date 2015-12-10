package com.thinkdevs.sendoc.view.message;

import com.thinkdevs.sendoc.model.Message;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.List;

public class MessageView {
    @FXML
    private AnchorPane anchorPaneMsg;
    @FXML
    private Label labelAuthorMsg;
    @FXML
    private Label labelSubjectMsg;
    @FXML
    private Label labelDateMsg;
    @FXML
    private TextFlow textFlowDesctiptionMsg;

    @FXML
    private void initialize(){}

    public MessageView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/thinkdevs/sendoc/view/fxml/messageView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(Message message){
        labelAuthorMsg.setText(message.getAuthor());
        labelSubjectMsg.setText(message.getSubject());
        labelDateMsg.setText(message.getDate().toString());
        ObservableList<Node> lines = textFlowDesctiptionMsg.getChildren();
        lines.add(new Text(message.getTextMessage()));
        List<String> links = message.getLinks();
        if(null != links && links.size() != 0) {
            int i = 1;
            lines.add(new Text("\n Cсылки: \n"));
            for (String link : links){
                lines.add(new Text( i + ") "));
                Hyperlink hyperlink = new Hyperlink(link);
                hyperlink.setOnAction(getHandlerHyperLink());
                lines.add(hyperlink);
                lines.add(new Text(";"));
                i++;
            }
        }
    }

    public Node getView(){
        return anchorPaneMsg;
    }

    @FXML
    private EventHandler<ActionEvent> getHandlerHyperLink() {
        return event -> {
            System.out.println("push to hyper");
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
