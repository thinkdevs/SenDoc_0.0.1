package com.thinkdevs.sendoc.model;

import javafx.beans.property.StringProperty;


/**
 * Model class for Order
 *
 * @author Maxim Tikhanovskiy
 */
public class Order {
    private final StringProperty name;
    private final StringProperty url;

    public Order(StringProperty name, StringProperty url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }
}
