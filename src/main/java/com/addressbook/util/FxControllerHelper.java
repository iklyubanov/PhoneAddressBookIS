package com.addressbook.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxControllerHelper {
    public static void changeView(String resource, Node node, Class curClass) {
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(curClass.getResource(resource));
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
