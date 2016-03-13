package com.addressbook.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FxControllerHelper {
    public static void changeView(String resource, Node node, Class curClass) {
        changeView(null, resource, node, curClass);
    }

    public static void changeView(FXMLLoader fxmlLoader, String resource, Node node, Class curClass) {
        if(fxmlLoader == null) {
            fxmlLoader = new FXMLLoader();
        }
        Stage stage = (Stage) node.getScene().getWindow();
        URL location = curClass.getResource(resource);
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = null;
        try {
            root = fxmlLoader.load(location.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Deprecated
    public static void changeStageSize(Node node, double height, double width) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setHeight(height);
        stage.setWidth(width);
    }
}
