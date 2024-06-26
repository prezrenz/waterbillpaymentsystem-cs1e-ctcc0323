package com.cs1e;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registration extends JPanel {
    App mainApp;

    JLabel headerLabel;

    JPanel optionsPanel;

    JPanel labelsPanel;
    JPanel fieldsPanel;
    JPanel buttonsPanel;

    JLabel emailLabel;
    JLabel passwordLabel;
    JLabel nameLabel;
    JLabel addressLabel;
    JLabel creditCardLabel;
    
    JTextField emailField;
    JPasswordField passwordField;
    JTextField nameField;
    JTextField addressField;
    JTextField creditCardField;

    JButton registerButton;
    JButton backButton;

    Registration(App parent) {
        mainApp = parent;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        headerLabel = new JLabel("Registration");       
        add(headerLabel);

        labelsPanel = new JPanel();
        fieldsPanel = new JPanel();
        buttonsPanel = new JPanel();
        optionsPanel = new JPanel();

        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        emailLabel = new JLabel("Email", JLabel.LEFT);
        passwordLabel = new JLabel("Password", JLabel.LEFT);
        nameLabel = new JLabel("Full Name", JLabel.LEFT);
        addressLabel = new JLabel("Address", JLabel.LEFT);
        creditCardLabel = new JLabel("Credit Card Number", JLabel.LEFT);

        emailField = new JTextField(24);
        passwordField = new JPasswordField(24);
        nameField = new JTextField(24);
        addressField = new JTextField(24);
        creditCardField = new JTextField(24);

        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        optionsPanel.add(labelsPanel);
        labelsPanel.add(emailLabel);
        labelsPanel.add(passwordLabel);
        labelsPanel.add(nameLabel);
        labelsPanel.add(addressLabel);
        labelsPanel.add(creditCardLabel);

        // Give space below each label to align with fields
        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        creditCardLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    
        optionsPanel.add(fieldsPanel);
        fieldsPanel.add(emailField);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(nameField);
        fieldsPanel.add(addressField);
        fieldsPanel.add(creditCardField);
        
        add(optionsPanel);
        
        add(buttonsPanel);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(backButton);

        registerButton.addActionListener((ae) -> register());
        backButton.addActionListener((ae) -> back());
    }

    private void clearFields() {
        emailField.setText("");
        passwordField.setText("");
        nameField.setText("");
        addressField.setText("");
        creditCardField.setText("");
    }

    private void register() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String address = addressField.getText();
            String creditCardNumber = creditCardField.getText();

            if((name.isEmpty()) || (email.isEmpty()) || (password.isEmpty()) || (address.isEmpty()) || (creditCardNumber.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill up all the fields", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(password.contains(" ")) {
                throw new Database.DatabaseError("Spaces are not allowed in password");
            }

            // pattern from: https://www.baeldung.com/java-email-validation-regex
            if(!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                throw new Database.DatabaseError("Please use a valid email");
            }

            mainApp.database.newUser(name, email, password, address, creditCardNumber);
            JOptionPane.showMessageDialog(mainApp, "Successfully registered user!");
            clearFields();
            back();
        } catch (Database.DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Registration Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { // Catch-all
            e.printStackTrace();
        }
    }

    private void back() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Login");
        mainApp.database.usersToFile();
    }
}
