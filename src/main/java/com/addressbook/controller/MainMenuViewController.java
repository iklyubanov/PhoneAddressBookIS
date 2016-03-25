/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.addressbook.controller;

import com.addressbook.util.FxControllerHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class MainMenuViewController implements Initializable {

    @FXML
    private Button showAllButton;
    @FXML
    private Button deepSearchButton;
    @FXML
    private Button addContactButton;
    @FXML
    private TextField quickSearch;

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
        quickSearch.addEventFilter(KeyEvent.KEY_PRESSED, eventFilter -> {
            if(KeyCode.ENTER.equals(eventFilter.getCode())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                FxControllerHelper.changeView(fxmlLoader, "/view/DeepSearchView.fxml", quickSearch, getClass());
                DeepSearchController searchController = (DeepSearchController) fxmlLoader.getController();
                searchController.setParamLastNameText(quickSearch.getText());
                searchController.search();
            }
        });
    }

    @FXML
    public void deepSearch(ActionEvent event) {
        FxControllerHelper.changeView("/view/DeepSearchView.fxml", deepSearchButton, getClass());
        //FxControllerHelper.changeStageSize(deepSearchButton, 800.0, 640.0);
    }

    @FXML
    public void addContact(ActionEvent event) {
        FxControllerHelper.changeView("/view/AddContactView.fxml", addContactButton, getClass());
    }
}
