package com.thinkdevs.sendoc;

import com.thinkdevs.sendoc.model.Order;
import com.thinkdevs.sendoc.view.OrderOverviewController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for start application
 *
 * @author Maxim Tikhanovskiy
 */
public class Main extends Application {

    private Stage      primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Order> ordersData = FXCollections.observableArrayList();

    public Main() {
        ordersData.add(new Order(new SimpleStringProperty("7170"), new SimpleStringProperty("C:\\orders\\7170")));
        ordersData.add(new Order(new SimpleStringProperty("7171"), new SimpleStringProperty("C:\\orders\\7171")));
        ordersData.add(new Order(new SimpleStringProperty("7172"), new SimpleStringProperty("C:\\orders\\7172")));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("SenDoc");
        initRoot();
        showHomeOverview();
    }


    /**
     * Initialization rootLayout
     */
    public void initRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/root.fxml"));
            rootLayout = (BorderPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show home inside the root layout
     */
    public void showHomeOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/homeOverview.fxml"));
            AnchorPane home = (AnchorPane)loader.load();
            rootLayout.setCenter(home);

            OrderOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Order> getOrdersData() {
        return ordersData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
