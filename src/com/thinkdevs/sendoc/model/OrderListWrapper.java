package com.thinkdevs.sendoc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of persons. This is used for saving the list to
 * list of orders to XML
 *
 * @author Maxim Tikhanovskiy
 */
@XmlRootElement(name = "orders")
public class OrderListWrapper {

    private List<Order> orders;

    @XmlElement(name = "order")
    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
