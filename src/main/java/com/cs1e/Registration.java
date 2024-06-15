package com.cs1e;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    JTextField passwordField;
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
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));

        emailLabel = new JLabel("Email", JLabel.LEFT);
        passwordLabel = new JLabel("Password", JLabel.LEFT);
        nameLabel = new JLabel("Full Name", JLabel.LEFT);
        addressLabel = new JLabel("Address", JLabel.LEFT);
        creditCardLabel = new JLabel("Credit Card Number", JLabel.LEFT);

        emailField = new JTextField(32);
        passwordField = new JTextField(32);
        nameField = new JTextField(32);
        addressField = new JTextField(32);
        creditCardField = new JTextField(32);

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

    private void register() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String address = addressField.getText();
            String creditCardNumber = creditCardField.getText();

            if((name.isEmpty()) || (email.isEmpty()) || (password.isEmpty()) || (address.isEmpty()) || (creditCardNumber.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill up all the fields", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mainApp.database.newUser(name, email, password, address, creditCardNumber);
        } catch (Database.DatabaseError e) {
            JOptionPane.showMessageDialog(null, e.getMsg(), "Registration Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { // Catch-all
            e.printStackTrace();
        }
    }

    private void back() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Login");
        mainApp.database.usersToFile();
    }
}
