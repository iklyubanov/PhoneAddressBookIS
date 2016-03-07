/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.addressbook.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.addressbook.util.FxControllerHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ivan
 */
public class MainMenuViewController implements Initializable {

    @FXML private Button showAllButton;
    @FXML private Button deepSearchButton;

    @FXML
    public void showAll(ActionEvent event) {
        FxControllerHelper.changeView("/view/ShowContactsView.fxml", showAllButton, getClass());
    }

    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void deepSearch(ActionEvent event) {
        FxControllerHelper.changeView("/view/DeepSearchView.fxml", deepSearchButton, getClass());
        //FxControllerHelper.changeStageSize(deepSearchButton, 800.0, 640.0);
    }
}
