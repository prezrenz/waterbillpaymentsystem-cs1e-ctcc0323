package com.cs1e;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cs1e.Database.DatabaseError;


public class Dashboard extends JPanel {
    App mainApp;

    JLabel JWelcome, JStatus, JDueDate, JCons, JCurrent, JPrevious;
    JButton LChange, LPass, LAddress, LEmail, LPay, LLogout;

    public Dashboard(App parent) {
        mainApp = parent;

        setPreferredSize(new Dimension(540, 400));
        setLayout(null);

        JWelcome = new JLabel("Welcome, valued custumer!");
        JWelcome.setBounds(190, 10, 300, 20);
        add(JWelcome);

        JStatus = new JLabel("Status: UNPAID");
        JStatus.setBounds(150, 35, 300, 20);
        add(JStatus);

        JDueDate = new JLabel("Date: DD/MM/YYYY");
        JDueDate.setBounds(300, 35, 300, 20);
        add(JDueDate);


        JCons = new JLabel("Consumption: 100");
        JCons.setBounds(225, 60, 150, 20);
        add(JCons);

        JCurrent = new JLabel("Current Reading: 100cu.m");
        JCurrent.setBounds(200, 85, 200, 20);
        add(JCurrent);

        JPrevious = new JLabel("Previous Reading: 100cu.m");
        JPrevious.setBounds(195, 110, 200, 20);
        add(JPrevious);


        LChange = new JButton("Change Name");
        LChange.setBounds(100, 150, 150, 30);
        LChange.addActionListener((ae) -> changeName());
        add(LChange);

        LPass = new JButton("Change Password");
        LPass.setBounds(275, 150, 150, 30);
        LPass.addActionListener((ae) -> changePassword());
        add(LPass);

        LAddress = new JButton("Change Address");
        LAddress.setBounds(100, 200, 150, 30);
        LAddress.addActionListener((ae) -> changeAddress());
        add(LAddress);

        LEmail = new JButton("Change Email");
        LEmail.setBounds(275, 200, 150, 30);
        LEmail.addActionListener((ae) -> changeEmail());
        add(LEmail);

        LPay = new JButton("Pay");
        LPay.setBounds(185, 250, 150, 30);
        LPay.addActionListener((ae) -> pay());
        add(LPay);

        LLogout = new JButton("Logout");
        LLogout.setBounds(185, 300, 150, 30);
        LLogout.addActionListener((ae) -> logout());
        add(LLogout);
    }

    public void setup() {
        JWelcome.setText("Welcome, " + mainApp.currentUser.name);
        JStatus.setText("STATUS: " + mainApp.currentUser.status);
        JDueDate.setText("Due Date" + mainApp.currentUser.dueDate);
        JCons.setText("Consumption: " + mainApp.currentUser.consumption);
        JCurrent.setText("Current Reading: " + mainApp.currentUser.currentReading);
        JPrevious.setText("Previous Reading: " + mainApp.currentUser.previousReading);
    }

    private void changeName() {
        try {
            String newName = JOptionPane.showInputDialog("Enter new name:");

            if(newName.isEmpty()) {
                throw new DatabaseError("Please enter a new name");
            }
            
            mainApp.currentUser.name = newName;
            JOptionPane.showMessageDialog(mainApp, "Successfully changed name");
            mainApp.database.usersToFile();
            setup();
        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Failed to change name", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void changeEmail() {
        try {
            String newEmail = JOptionPane.showInputDialog("Enter new email:");

            if(newEmail.isEmpty()) {
                throw new DatabaseError("Please enter a new email");
            }

            // I hate this, but i don't have time to refactor
            try { // find user throws error on user not found
                mainApp.database.findUser(newEmail); // if this throws no error, means email already in use/found
                JOptionPane.showMessageDialog(mainApp, "Email already in use", "Failed to change email", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (DatabaseError e) {
                mainApp.currentUser.email = newEmail;
                mainApp.database.usersToFile();
                JOptionPane.showMessageDialog(mainApp, "Successfully changed email");
            }

        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Failed to change email", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void changePassword() {
        try {
            String newPassword = JOptionPane.showInputDialog("Enter new password:");

            if(newPassword.isEmpty()) {
                throw new DatabaseError("Please enter a new password");
            }
            
            mainApp.currentUser.password = newPassword;
            mainApp.database.usersToFile();
            JOptionPane.showMessageDialog(mainApp, "Successfully changed password");
            setup();
        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Failed to change password", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void changeAddress() {
        try {
            String newAddress = JOptionPane.showInputDialog("Enter new address:");

            if(newAddress.isEmpty()) {
                throw new DatabaseError("Please enter a new address");
            }
            
            mainApp.currentUser.address = newAddress;
            mainApp.database.usersToFile();
            JOptionPane.showMessageDialog(mainApp, "Successfully changed address");
            setup();
        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Failed to change name", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pay() {
        try {
            if(mainApp.currentUser.status.equalsIgnoreCase("PAID")) {
                throw new DatabaseError("Already paid, thank you for using the Aqua Water Billing System!");
            }
            
            if(mainApp.currentUser.status.equalsIgnoreCase("UNVERIFIED")) {
                throw new DatabaseError("You are unverified, please wait for the admin to set you a new reading");
            }

            mainApp.cardLayout.show(mainApp.mainPanel, "Payment");
            mainApp.paymentScreen.setup();

        } catch (DatabaseError e) {            
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Login");
        mainApp.database.usersToFile();
    }

}

