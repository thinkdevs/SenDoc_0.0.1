package com.thinkdevs.sendoc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Model class for Order
 *
 * @author Maxim Tikhanovskiy
 */
public class Order {
    private StringProperty bidNumber;
    private StringProperty orderNumber;
    private StringProperty dateOpen;
    private StringProperty manager;
    private StringProperty customer;
    private StringProperty volumeNumber;
    private StringProperty dateShipping;
    private StringProperty repository;

    public Order() {
        orderNumber = new SimpleStringProperty("");
        repository = new SimpleStringProperty("");
        bidNumber = new SimpleStringProperty("");
        dateOpen = new SimpleStringProperty("");
        manager = new SimpleStringProperty("");
        customer = new SimpleStringProperty("");
        volumeNumber = new SimpleStringProperty("");
        dateShipping = new SimpleStringProperty("");
    }

    public Order(StringProperty orderNumber, StringProperty repository){
        this.orderNumber = orderNumber;
        this.repository = repository;
        bidNumber = new SimpleStringProperty("");
        dateOpen = new SimpleStringProperty("");
        manager = new SimpleStringProperty("");
        customer = new SimpleStringProperty("");
        volumeNumber = new SimpleStringProperty("");
        dateShipping = new SimpleStringProperty("");
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public void setOrderNumber(String name) {
        this.orderNumber.set(name);
    }

    public String getRepository() {
        return repository.get();
    }

    public StringProperty repositoryProperty() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository.set(repository);
    }

    public String getBidNumber() {
        return bidNumber.get();
    }

    public StringProperty bidNumberProperty() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber.set(bidNumber);
    }

    public String getDateOpen() {
        return dateOpen.get();
    }

    public StringProperty dateOpenProperty() {
        return dateOpen;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen.set(dateOpen);
    }

    public String getManager() {
        return manager.get();
    }

    public StringProperty managerProperty() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager.set(manager);
    }

    public String getCustomer() {
        return customer.get();
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getVolumeNumber() {
        return volumeNumber.get();
    }

    public StringProperty volumeNumberProperty() {
        return volumeNumber;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber.set(volumeNumber);
    }

    public String getDateShipping() {
        return dateShipping.get();
    }

    public StringProperty dateShippingProperty() {
        return dateShipping;
    }

    public void setDateShipping(String dateShipping) {
        this.dateShipping.set(dateShipping);
    }

    @Override
    public String toString() {
        return orderNumber.get();
    }
}
