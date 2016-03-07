/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.addressbook.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.addressbook.entity.Contact;
import com.addressbook.service.ContactsService;
import com.addressbook.service.ContactsServiceImpl;
import com.addressbook.util.FxControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ivan
 */
public class DeepSearchController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, String> contactId;
    @FXML private TableColumn<Contact, String> contactAddressBookName;
    @FXML private TableColumn<Contact, String> contactFirstName;
    @FXML private TableColumn<Contact, String> contactLastName;
    @FXML private TableColumn<Contact, String> contactAddress1;
    @FXML private TableColumn<Contact, String> contactAddress2;
    @FXML private TableColumn<Contact, String> contactTown;
    @FXML private TableColumn<Contact, String> contactCounty;
    @FXML private TableColumn<Contact, String> contactCountry;
    @FXML private TableColumn<Contact, String> contactPostCode;
    @FXML private TableColumn<Contact, String> contactHomeTelephone;
    @FXML private TableColumn<Contact, String> contactWorkTelephone;
    @FXML private TableColumn<Contact, String> contactMobileTelephone;
    @FXML private TableColumn<Contact, String> contactFax;
    @FXML private TableColumn<Contact, String> contactEmail;

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
    private Contact searchContact = new Contact();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //todo check this out
        contactAddressBookName.setCellValueFactory(new PropertyValueFactory<>("addressBookName"));
        contactFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        contactLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        contactAddress1.setCellValueFactory(new PropertyValueFactory<>("address1"));
        contactAddress2.setCellValueFactory(new PropertyValueFactory<>("address2"));
        contactTown.setCellValueFactory(new PropertyValueFactory<>("town"));
        contactCounty.setCellValueFactory(new PropertyValueFactory<>("county"));
        contactCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        contactPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        contactHomeTelephone.setCellValueFactory(new PropertyValueFactory<>("homeTelephone"));
        contactWorkTelephone.setCellValueFactory(new PropertyValueFactory<>("workTelephone"));
        contactMobileTelephone.setCellValueFactory(new PropertyValueFactory<>("mobileTelephone"));
        contactFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    public void back(ActionEvent event) {
        FxControllerHelper.changeView("/view/MainMenuView.fxml", backButton, getClass());
    }

    @FXML
    public void search(ActionEvent event) {
        if(contactsService == null) {
            contactsService = new ContactsServiceImpl();
        }

        bindParamsToContact();
        List<Contact> result = contactsService.deepSearch(searchContact);
        tableView.getItems().setAll(result);
    }

    private void bindParamsToContact() {
        searchContact.setFirstName(paramFirstName.getText());
        searchContact.setLastName(paramLastName.getText());
        searchContact.setAddress1(paramAddress1.getText());
        searchContact.setAddress2(paramAddress2.getText());
        searchContact.setTown(paramTown.getText());
        searchContact.setCounty(paramCounty.getText());
        searchContact.setCountry(paramCountry.getText());
        searchContact.setPostCode(paramPostCode.getText());
        searchContact.setHomeTelephone(paramHomeTelephone.getText());
        searchContact.setWorkTelephone(paramWorkTelephone.getText());
        searchContact.setMobileTelephone(paramMobileTelephone.getText());
        searchContact.setFax(paramFax.getText());
        searchContact.setEmail(paramEmail.getText());
    }
}
