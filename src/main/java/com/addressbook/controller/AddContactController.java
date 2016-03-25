/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.addressbook.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.addressbook.entity.AddressBook;
import com.addressbook.entity.Contact;
import com.addressbook.service.AddressBookService;
import com.addressbook.service.ContactsService;
import com.addressbook.service.ContactsServiceImpl;
import com.addressbook.util.FxControllerHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author ivan
 */
public class AddContactController implements Initializable {

    @FXML
    public ComboBox addrBookCombo;
    @FXML
    public Label message;
    @FXML
    private Button backButton;

    //search parameters
    @FXML private TextField paramFirstName;
    @FXML private TextField paramLastName;
    @FXML private TextField paramAddress1;
    @FXML private TextField paramAddress2;
    @FXML private TextField paramTown;
    @FXML private TextField paramCounty;
    @FXML private TextField paramCountry;
    @FXML private TextField paramPostCode;
    @FXML private TextField paramHomeTelephone;
    @FXML private TextField paramWorkTelephone;
    @FXML private TextField paramMobileTelephone;
    @FXML private TextField paramFax;
    @FXML private TextField paramEmail;

    private ContactsService contactsService;
    private AddressBookService addressBookService;
    private List<AddressBook> availableAddressBooks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(addressBookService == null) {
            addressBookService = new AddressBookService();
        }
        availableAddressBooks = addressBookService.findAll();
        ObservableList<String> options = FXCollections.observableArrayList(
                availableAddressBooks.stream().map(AddressBook::getName).collect(Collectors.toList())
        );
        addrBookCombo.getItems().addAll(options);
    }

    @FXML
    public void back(ActionEvent event) {
        FxControllerHelper.changeView("/view/MainMenuView.fxml", backButton, getClass());
    }

    @FXML
    public void save(ActionEvent event) {
        if(contactsService == null) {
            contactsService = new ContactsServiceImpl();
        }
        Contact contact = prepareContact();
        try {
            contactsService.addNew(contact);
        } catch (Exception e) {
            message.setVisible(true);
            message.setTextFill(Color.RED);
            message.setText("Ошибка! " + e.getLocalizedMessage());
            e.printStackTrace();
            return;
        }
        message.setVisible(true);
        message.setTextFill(Color.GREEN);
        message.setText("Контакт успешно сохранен!");
    }

    private Contact prepareContact() {
        Contact contact = new Contact();
        contact.setFirstName(paramFirstName.getText());
        contact.setLastName(paramLastName.getText());
        contact.setAddress1(paramAddress1.getText());
        contact.setAddress2(paramAddress2.getText());
        contact.setTown(paramTown.getText());
        contact.setCounty(paramCounty.getText());
        contact.setCountry(paramCountry.getText());
        contact.setPostCode(paramPostCode.getText());
        contact.setHomeTelephone(paramHomeTelephone.getText());
        contact.setWorkTelephone(paramWorkTelephone.getText());
        contact.setMobileTelephone(paramMobileTelephone.getText());
        contact.setFax(paramFax.getText());
        contact.setEmail(paramEmail.getText());
        AddressBook addressBook = availableAddressBooks.stream()
                .filter(ab -> addrBookCombo.getValue().toString().equals(ab.getName()))
                .findFirst().get();
        contact.setAddressBook(addressBook);
        return contact;
    }

    public void setParamLastNameText(String lastName) {
        this.paramLastName.setText(lastName);
    }
}
