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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ivan
 */
public class DeepSearchController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
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
    private Contact selectedContact;

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
    public void mouseClickedOnTable(MouseEvent event) {
        TableView<Contact> table = (TableView<Contact>)event.getSource();
        if(table != null) {
            selectedContact = table.getSelectionModel().getSelectedItem();
            deleteButton.setDisable(false);
            editButton.setDisable(false);
        }
    }

    @FXML
    public void searchClicked(ActionEvent event) {
        search();
    }

    public void search() {
        if(contactsService == null) {
            contactsService = new ContactsServiceImpl();
        }
        if (!tableView.getItems().isEmpty()) {
            tableView.getItems().clear();
        }

        bindParamsToContact(searchContact);
        List<Contact> result = contactsService.deepSearch(searchContact);
        tableView.getItems().setAll(result);
    }

    private void bindParamsToContact(Contact contact) {
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
    }

    private void bindContanctToParams(Contact contact) {
        paramFirstName.setText(contact.getFirstName());
        paramLastName.setText(contact.getLastName());
        paramAddress1.setText(contact.getAddress1());
        paramAddress2.setText(contact.getAddress2());
        paramTown.setText(contact.getTown());
        paramCounty.setText(contact.getCounty());
        paramCountry.setText(contact.getCountry());
        paramPostCode.setText(contact.getPostCode());
        paramHomeTelephone.setText(contact.getHomeTelephone());
        paramWorkTelephone.setText(contact.getWorkTelephone());
        paramMobileTelephone.setText(contact.getMobileTelephone());
        paramFax.setText(contact.getFax());
        paramEmail.setText(contact.getEmail());
    }

    public void setParamLastNameText(String lastName) {
        this.paramLastName.setText(lastName);
    }

    @FXML
    public void edit(ActionEvent actionEvent) {
        bindContanctToParams(selectedContact);
        saveButton.setDisable(false);
        editButton.setDisable(true);
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        tableView.getSelectionModel().clearSelection();
        contactsService.delete(selectedContact);
        disableEditButtons();
        search();
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        bindParamsToContact(selectedContact);
        contactsService.save(selectedContact);
        disableEditButtons();
        tableView.getSelectionModel().clearSelection();
        search();
    }

    private void disableEditButtons() {
        deleteButton.setDisable(true);
        saveButton.setDisable(true);
        editButton.setDisable(true);
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        searchContact = new Contact();
        bindContanctToParams(searchContact);
    }
}
