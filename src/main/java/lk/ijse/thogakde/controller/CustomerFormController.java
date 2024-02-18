package lk.ijse.thogakde.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakde.entity.Customer;
import lk.ijse.thogakde.config.SessionFactoryConfig;
import lk.ijse.thogakde.embeddad.NameIdentifier;
import lk.ijse.thogakde.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.regex.Pattern;


public class CustomerFormController {

    @FXML
    private AnchorPane paneCreateAccount;

    @FXML
    private TextField txtId;


    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txtlastname;


    public void initialize() {
        setId();
    }

    private void setId() {
        try {
            Session session = SessionFactoryConfig.getFactoryConfig().getSession();
            Query query = session.createQuery("select max(id) from Customer");
            Integer maxId = (Integer) ((org.hibernate.query.Query<?>) query).uniqueResult();
            int nextId = maxId != null ? maxId + 1 : 1;

            txtId.setText(String.valueOf(nextId));

            session.close();
        } catch (NumberFormatException e) {
            System.err.println("Invalid customer ID format: " + txtId.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void idSearch(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                Session session = SessionFactoryConfig.getFactoryConfig().getSession();
                Transaction transaction = session.beginTransaction();

                int cusid = Integer.parseInt(txtId.getText());
                Customer searchCustomer = session.get(Customer.class, cusid);

                if (searchCustomer != null) {
                    String firstName = searchCustomer.getName().getFistName();
                    String lastName = searchCustomer.getName().getLastName();
                    String address = searchCustomer.getAddress();
                    String tel = searchCustomer.getTel();
                    String Salary =searchCustomer.getSalary();

                    txtfirstname.setText(firstName);
                    txtlastname.setText(lastName);
                    txtaddress.setText(address);
                    txtTel.setText(tel);
                    txtsalary.setText(Salary);
                } else {
                    clearFields();
                }
                transaction.commit();
                session.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        try {
            Session updateSession = SessionFactoryConfig.getFactoryConfig().getSession();
            Transaction updateTransaction = updateSession.beginTransaction();

            int cusid = Integer.parseInt(txtId.getText());
            Customer existingCustomer = updateSession.get(Customer.class, cusid);

            NameIdentifier nameIdentifier = new NameIdentifier();
            nameIdentifier.setFistName(txtfirstname.getText());
            nameIdentifier.setLastName(txtlastname.getText());
            existingCustomer.setName(nameIdentifier);

            existingCustomer.setAddress(txtaddress.getText());
            existingCustomer.setTel(txtTel.getText());
            existingCustomer.setSalary(new String(txtsalary.getText()));

            updateSession.update(existingCustomer);

            updateTransaction.commit();
            updateSession.close();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backOnAction(MouseEvent event) {
        System.exit(0);
        System.out.println("exit system");
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Session session = null;
        boolean isValidated = validate();
        if (isValidated) {
            try {
                session = SessionFactoryConfig.getFactoryConfig().getSession();
                Transaction transaction = session.beginTransaction();

                Customer customer = new Customer();

                NameIdentifier nameIdentifier = new NameIdentifier();
                nameIdentifier.setFistName(txtfirstname.getText());
                nameIdentifier.setLastName(txtlastname.getText());
                customer.setName(nameIdentifier);

                customer.setAddress(txtaddress.getText());
                customer.setTel(txtTel.getText());
                customer.setSalary(new String(txtsalary.getText()));

                session.save(customer);

                transaction.commit();
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    private boolean validate() {

        String nameText = txtfirstname.getText();
        boolean isNameValidated = Pattern.matches("[A-Za-z]{3,}", nameText);
        if (!isNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer Fist name").show();
            return false;
        }

        String LastnameText = txtlastname.getText();
        boolean isNameValidatede = Pattern.matches("[A-Za-z]{3,}", LastnameText);
        if (!isNameValidatede) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer Last name").show();
            return false;
        }

        String cantacText = txtTel.getText();
        boolean isCantacValidated = Pattern.matches("[0-9]{10}", cantacText);
        if (!isCantacValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid contac").show();
            return false;
        }

        String addressText = txtaddress.getText();
        boolean isAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", addressText);
        if (!isAddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid  address").show();
            return false;
        }

        String Salary = txtsalary.getText();
        boolean isCantacValidatede = Pattern.matches("[0-9]{5}", Salary);
        if (!isCantacValidatede) {
            new Alert(Alert.AlertType.ERROR, "Invalid contac").show();
            return false;
        }
        return true;
    }

    @FXML
    void deleteOnAaction(ActionEvent event) {

        Session deleteSession = SessionFactoryConfig.getFactoryConfig().getSession();
        Transaction transaction = deleteSession.beginTransaction();

        int cusid = Integer.parseInt(txtId.getText());
        // Retrieve the existing customer entity using id
        Customer deleteCustomer = deleteSession.get(Customer.class, cusid);

        NameIdentifier nameIdentifier = new NameIdentifier();
        nameIdentifier.setFistName(txtfirstname.getText());
        nameIdentifier.setLastName(txtlastname.getText());
        deleteCustomer.setName(nameIdentifier);

        deleteCustomer.setAddress(txtaddress.getText());
        deleteCustomer.setTel(txtTel.getText());
        deleteCustomer.setSalary(new String(txtsalary.getText()));


        deleteSession.delete(deleteCustomer);
        transaction.commit();

        deleteSession.close();
        clearFields();
    }
    void clearFields(){
        txtfirstname.clear();
        txtlastname.clear();
        txtaddress.clear();
         txtTel.clear();
        txtsalary.clear();
    }

}
