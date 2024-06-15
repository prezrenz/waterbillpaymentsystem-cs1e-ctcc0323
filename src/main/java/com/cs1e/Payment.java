package com.cs1e;

import java.awt.Dimension;
import javax.swing.JPanel;

import com.cs1e.Database.DatabaseError;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Payment extends JPanel {
    App mainApp;

    JLabel LPayment;
    JLabel LStatus;
    JLabel LDue; 
    JLabel LBalance;
    JLabel LAmountDue;
    JLabel LBalanceAfterPay;
    
    JButton Confirm;
    JButton Back;

    Payment(App parent) {
        mainApp = parent;

        setPreferredSize(new Dimension(560, 400));
        setLayout(null);

        LPayment = new JLabel("PAYMENT");
        LPayment.setBounds(250, 50, 200, 20);
        add(LPayment);


        LStatus = new JLabel("Status: UNPAID");
        LStatus.setBounds(200, 100, 200, 20);
        add(LStatus);

        LDue = new JLabel("Due Date: DD/MM/YYYY");
        LDue.setBounds(200, 130, 200, 20);
        add(LDue);

        LBalance = new JLabel("Current Balance: 100 php");
        LBalance.setBounds(200, 160, 200, 20);
        add(LBalance);

        LAmountDue = new JLabel("Amount Due: 80 php");
        LAmountDue.setBounds(200, 190, 200, 20);
        add(LAmountDue);

        LBalanceAfterPay = new JLabel("Balance After Pay: 20 php");
        LBalanceAfterPay.setBounds(200, 220, 200, 20);
        add(LBalanceAfterPay);


        Confirm = new JButton("Confirm");
        Confirm.setBounds(150, 250, 100, 30);
        Confirm.addActionListener((ae) -> pay());
        add(Confirm);

        Back = new JButton("Back");
        Back.setBounds(300, 250, 100, 30);
        Back.addActionListener((ae) -> back());
        add(Back);
    }

    private void pay() {
        try {
            mainApp.currentUser.pay(); 
            JOptionPane.showMessageDialog(mainApp, "Successfully paid due");
            back();
        } catch (DatabaseError e) {
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Payment Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void back() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Dashboard"); // currently errors
        mainApp.database.usersToFile();
    }

}
