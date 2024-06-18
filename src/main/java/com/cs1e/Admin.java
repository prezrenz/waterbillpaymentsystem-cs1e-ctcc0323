package com.cs1e;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cs1e.Database.DatabaseError;

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
    JButton logoutButton;

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
        logoutButton = new JButton("Log out");

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

        add(logoutButton);
        logoutButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        setNewReadingButton.addActionListener((ae) -> setNewReading());
        deleteAccountButton.addActionListener((ae) -> deleteAccount());
        viewAllUnpaidButton.addActionListener((ae) -> viewAllUnpaid());
        viewAllCustomersButton.addActionListener((ae) -> viewAllCustomers());
        viewAllPaidButton.addActionListener((ae) -> viewAllPaid());
        viewAllUnverifiedButton.addActionListener((ae) -> viewAllUnverified());
        logoutButton.addActionListener((ae) -> logout());
    }

    private JScrollPane createTablePane(ArrayList<Object[]> tableData) {
        String columnNames[] = { "Full Name", "Email", "Address",
                                "Previous Reading", "Current Reading", "Consumption",
                                "Total Due", "Due Date", "Status"};
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // Makes all cells uneditable
            }
        };
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

    private void setNewReading() {
        mainApp.cardLayout.show(mainApp.mainPanel, "NewReading");
        mainApp.database.usersToFile();
    }

    private void deleteAccount() {
        String email = JOptionPane.showInputDialog("Please enter the email of the user to be removed:");
        
        if(email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field is empty, please try again", "Failed to delete account!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            mainApp.database.deleteUser(email);
            JOptionPane.showMessageDialog(mainApp, "Successfully deleted account!");
        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(null, e.getMsg(), "Failed to delete account!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAllUnpaid() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("UNPAID");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Unpaid");
    }

    private void viewAllCustomers() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData();
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Customers");
    }

    private void viewAllPaid() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("PAID");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Paid");
    }

    private void viewAllUnverified() {
        ArrayList<Object[]> tableData = mainApp.database.usersToTableData("UNVERIFIED");
        JScrollPane scrollPane = createTablePane(tableData);
        createDialog(scrollPane, "View All Unverified");
    }

    private void logout() {
        int answer = JOptionPane.showConfirmDialog(mainApp, "Are you sure you want to log out?", "Logging out...", JOptionPane.YES_NO_OPTION);

        if(answer == 0) {
            mainApp.cardLayout.show(mainApp.mainPanel, "Login");
            mainApp.database.usersToFile();
        }
    }
}
