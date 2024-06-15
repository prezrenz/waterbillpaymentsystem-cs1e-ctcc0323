package com.cs1e;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Admin extends JPanel {
    App mainApp;

    JLabel header;

    JPanel functionsPanel;
    JPanel dataViewerOnePanel;
    JPanel dataViewerTwoPanel;

    JButton setNewReadingButton;
    JButton deleteAccountButton;
    JButton viewAllUnpaidButton;
    JButton viewAllCustomersButton;
    JButton viewAllPaidButton;
    JButton viewAllUnverifiedButton;
    JButton viewAllLogoutButton;

    Admin(App parent) {
        mainApp = parent;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        header = new JLabel("Welcome Admin!");
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(header);

        functionsPanel = new JPanel();
        dataViewerOnePanel = new JPanel();
        dataViewerTwoPanel = new JPanel();

        functionsPanel.setLayout(new BoxLayout(functionsPanel, BoxLayout.Y_AXIS));
        dataViewerOnePanel.setLayout(new BoxLayout(dataViewerOnePanel, BoxLayout.Y_AXIS));
        dataViewerTwoPanel.setLayout(new BoxLayout(dataViewerTwoPanel, BoxLayout.Y_AXIS));
        
        setNewReadingButton = new JButton("Set New Reading");
        deleteAccountButton = new JButton("Delete Account");

        viewAllUnpaidButton = new JButton("View All Unpaid");
        viewAllCustomersButton = new JButton("View All Customers");
        viewAllPaidButton = new JButton("View All Paid");
        viewAllUnverifiedButton = new JButton("View All Unverified");
        viewAllLogoutButton = new JButton("Log out");

        add(functionsPanel);
        functionsPanel.add(setNewReadingButton);
        functionsPanel.add(deleteAccountButton);
        setNewReadingButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        deleteAccountButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);

        add(dataViewerOnePanel);
        dataViewerOnePanel.add(viewAllUnpaidButton);
        dataViewerOnePanel.add(viewAllPaidButton);
        viewAllUnpaidButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        viewAllPaidButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        
        add(dataViewerTwoPanel);
        dataViewerTwoPanel.add(viewAllCustomersButton);
        dataViewerTwoPanel.add(viewAllUnverifiedButton);
        viewAllCustomersButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        viewAllUnverifiedButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);

        add(viewAllLogoutButton);
        viewAllLogoutButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        viewAllLogoutButton.addActionListener((ae) -> logout());
    }

    private void logout()
    {
        mainApp.cardLayout.show(mainApp.mainPanel, "Login");
        mainApp.database.usersToFile();
    }
}
