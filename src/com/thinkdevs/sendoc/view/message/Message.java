package com.thinkdevs.sendoc.view.message;

import java.util.Date;
import java.util.List;


public class Message {

    private String author;
    private String textMessage;
    private List<String> links;
    private Date date;

    public Message(String author, String textMessage, List<String> links) {
        this.author = author;
        this.textMessage = textMessage;
        this.links = links;
        this.date = new Date();
    }

    public String getAuthor() {
        return author;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public List<String> getLinks() {
        return links;
    }

    public Date getDate() {
        return date;
    }
}
