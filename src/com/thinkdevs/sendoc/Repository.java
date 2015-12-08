package com.thinkdevs.sendoc;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to get all orders directory
 *
 * @author Maxim Tikhanovskiy
 */
public class Repository {

    private static String repositoryOrders = "\\\\192.168.0.6\\KDProduction\\";
    private static Path path = Paths.get(repositoryOrders);

    private static List<String> getOrdersList(Path path) {
        List<String> ordersList = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path entry : directoryStream) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attributes.isDirectory()) {
                    ordersList.add(entry.getFileName().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * Get all orders name
     * @return List all orders
     */
    public static List<String> getOrdersNameList (){
        return getOrdersList(path);
    }

}
