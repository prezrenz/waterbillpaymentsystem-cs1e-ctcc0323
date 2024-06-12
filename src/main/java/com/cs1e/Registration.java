package com.cs1e;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registration extends JPanel {
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

    Registration() {
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
    }
}
