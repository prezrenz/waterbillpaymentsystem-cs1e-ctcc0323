package com.cs1e;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        
        viewAllUnpaidButton.addActionListener((ae) -> viewAllUnpaid());
        viewAllCustomersButton.addActionListener((ae) -> viewAllCustomers());
        viewAllPaidButton.addActionListener((ae) -> viewAllPaid());
        viewAllUnverifiedButton.addActionListener((ae) -> viewAllUnverified());
        viewAllLogoutButton.addActionListener((ae) -> logout());
    }

    private JScrollPane createTablePane(ArrayList<Object[]> tableData) {
        String columnNames[] = { "Full Name", "Email", "Address",
                                "Previous Reading", "Current Reading", "Consumption",
                                "Total Due", "Due Date", "Status"};
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        for (Object[] data : tableData) {
            tableModel.addRow(data);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        table.setFillsViewportHeight(true);

        return scrollPane;
    }

    private void createDialog(JScrollPane scrollPane, String title) {
        JDialog dialog = new JDialog(mainApp, title);
        dialog.add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(mainApp);
        dialog.setVisible(true);
    }

    private void viewAllUnpaid() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("UNPAID");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Customers");
    }

    private void viewAllCustomers() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData();
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Customers");
    }

    private void viewAllPaid() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("PAID");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Customers");
    }

    private void viewAllUnverified() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("UNVERIFIED");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Customers");
    }

    private void logout() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Login");
        mainApp.database.usersToFile();
    }
}
