package com.cs1e;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.cs1e.Database.DatabaseError;


public class Dashboard extends JPanel {
    App mainApp;

    JMenuBar menuBar;
    JMenu settingsMenu;
    JMenuItem changeNameMenuItem, changePassMenuItem, changeAddressMenuItem, changeEmailMenuItem;
    JLabel JWelcome, JStatus, JDueDate, JCons, JCurrent, JPrevious;
    JButton LChange, LPass, LAddress, LEmail, LPay, LLogout;

    public Dashboard(App parent) {
        mainApp = parent;

        setPreferredSize(new Dimension(540, 400));
        setLayout(null);

        menuBar = new JMenuBar();
        settingsMenu = new JMenu("Account Settings");
        changeNameMenuItem = new JMenuItem("Change Name");
        changePassMenuItem = new JMenuItem("Change Password");
        changeAddressMenuItem = new JMenuItem("Change Address");
        changeEmailMenuItem = new JMenuItem("Change Email");

        settingsMenu.add(changeNameMenuItem);
        settingsMenu.add(changePassMenuItem);
        settingsMenu.add(changeAddressMenuItem);
        settingsMenu.add(changeEmailMenuItem);
        menuBar.add(settingsMenu);

        changeNameMenuItem.addActionListener((ae) -> changeName());
        changePassMenuItem.addActionListener((ae) -> changePassword());
        changeAddressMenuItem.addActionListener((ae) -> changeAddress());
        changeEmailMenuItem.addActionListener((ae) -> changeEmail());

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

        // LChange = new JButton("Change Name");
        // LChange.setBounds(100, 150, 150, 30);
        // LChange.addActionListener((ae) -> changeName());
        // add(LChange);
        //
        // LPass = new JButton("Change Password");
        // LPass.setBounds(275, 150, 150, 30);
        // LPass.addActionListener((ae) -> changePassword());
        // add(LPass);
        //
        // LAddress = new JButton("Change Address");
        // LAddress.setBounds(100, 200, 150, 30);
        // LAddress.addActionListener((ae) -> changeAddress());
        // add(LAddress);
        //
        // LEmail = new JButton("Change Email");
        // LEmail.setBounds(275, 200, 150, 30);
        // LEmail.addActionListener((ae) -> changeEmail());
        // add(LEmail);

        LPay = new JButton("Pay");
        LPay.setBounds(185, 150, 150, 100);
        LPay.addActionListener((ae) -> pay());
        add(LPay);

        LLogout = new JButton("Logout");
        LLogout.setBounds(185, 260, 150, 30);
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
            // Only god and i know how this works
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
            JPasswordField newPasswordField = new JPasswordField(16);
            JOptionPane.showConfirmDialog(mainApp, newPasswordField, "Enter new password", JOptionPane.DEFAULT_OPTION);
            String newPassword = new String(newPasswordField.getPassword());

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
        int answer = JOptionPane.showConfirmDialog(mainApp, "Are you sure you want to log out?", "Logging out...", JOptionPane.YES_NO_OPTION);

        if(answer == 0) {
            mainApp.cardLayout.show(mainApp.mainPanel, "Login");
            mainApp.setJMenuBar(null);
            mainApp.database.usersToFile();
        }

    }

}

