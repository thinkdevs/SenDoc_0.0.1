package com.thinkdevs.sendoc;

import com.thinkdevs.sendoc.model.Order;
import com.thinkdevs.sendoc.model.OrderListWrapper;
import com.thinkdevs.sendoc.view.homeOverview.OrderOverviewController;
import com.thinkdevs.sendoc.view.orderDialogEdit.OrderDialogEditController;
import com.thinkdevs.sendoc.view.rootLayout.RootLayoutController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

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
        for(String orderName : Repository.getOrdersNameList()){
            ordersData.add(new Order(new SimpleStringProperty(orderName), new SimpleStringProperty(orderName)));
            System.out.println(orderName);
        }
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
            loader.setLocation(Main.class.getResource("view/rootLayout/rootLayout.fxml"));
            rootLayout = (BorderPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = getOrderFilePath();
        if (file != null) {
            loadOrderDataFromFile(file);
        }
    }

    /**
     * Show home inside the root layout
     */
    public void showHomeOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/homeOverview/ordersOverview.fxml"));
            AnchorPane home = (AnchorPane)loader.load();
            rootLayout.setCenter(home);

            OrderOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user clicks 'OK',
     * the changes are saved into provided order object and true is returned.
     * @param order the order object to be edited
     * @return true if the user clicked 'OK', false otherwise
     */
    public boolean showOrderEditDialog(Order order){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/orderDialogEdit/orderDialogEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование заказа");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            OrderDialogEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrder(order);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
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


    /**
     * Returns order file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * @return
     */
    public File getOrderFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if(filePath != null){
            return new File(filePath);
        }
        else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted
     * in the OS specific registry.
     * @param file
     */
    public void setOrderFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("AddressApp");
        }
    }

    /**
     * Loads person data from the specifies file. The current order data will be
     * replaced.
     * @param file
     */
    public void loadOrderDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrderListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            OrderListWrapper wrapper = (OrderListWrapper)um.unmarshal(file);

            ordersData.clear();
            ordersData.addAll(wrapper.getOrders());

            setOrderFilePath(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * @param file
     */
    public void saveOrderDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrderListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrderListWrapper wrapper = new OrderListWrapper();
            wrapper.setOrders(ordersData);

            m.marshal(wrapper, file);

            setOrderFilePath(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
