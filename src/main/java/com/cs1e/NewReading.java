package com.cs1e;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewReading extends JPanel {
    App mainApp;

    JLabel header;

    JLabel emailLabel;
    JLabel readingLabel;
    JLabel newDueLabel;

    JTextField emailField;
    JTextField readingField;
    JTextField newDueField;

    JPanel optionsPanel;

    JPanel labelsPanel;
    JPanel fieldsPanel;
    JPanel buttonsPanel;

    JButton confirmButton;
    JButton backButton;

    NewReading(App parent) {
        mainApp = parent;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        header = new JLabel("Set New Reading");
        header.setAlignmentX(JLabel.CENTER);
        add(header);

        optionsPanel = new JPanel();
        labelsPanel = new JPanel();
        fieldsPanel = new JPanel();
        buttonsPanel = new JPanel();

        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        emailLabel = new JLabel("Enter customer email");
        readingLabel = new JLabel("Enter new reading");
        newDueLabel = new JLabel("Enter new due (DD/MM/YYYY)");

        emailField = new JTextField(32);
        readingField = new JTextField(32);
        newDueField = new JTextField(32);

        confirmButton = new JButton("Confirm");
        backButton = new JButton("Back");

        add(optionsPanel);
        optionsPanel.add(labelsPanel);
        labelsPanel.add(emailLabel);
        labelsPanel.add(readingLabel);
        labelsPanel.add(newDueLabel);

        optionsPanel.add(fieldsPanel);
        fieldsPanel.add(emailField);
        fieldsPanel.add(readingField);
        fieldsPanel.add(newDueField);

        add(buttonsPanel);
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(backButton);
    }
}
