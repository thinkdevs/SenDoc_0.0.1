package com.thinkdevs.sendoc.view.rootLayout;

import com.thinkdevs.sendoc.Main;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller class for menu
 *
 * @author Maxim Tikhanovskiy
 */
public class RootLayoutController {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Creates an empty orders book.
     */
    @FXML
    private void handleNew() {
        main.getOrdersData();
        main.setOrderFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadOrderDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is not
     * open file, the 'save as' dialog is shown.
     */
    @FXML
    private void handleSave() {
        File orderFile = main.getOrderFilePath();
        if (orderFile != null) {
            main.saveOrderDataToFile(orderFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveOrderDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {

    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
