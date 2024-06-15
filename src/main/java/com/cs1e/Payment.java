package com.cs1e;


import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.awt.*;
//import java.awt.event.*;

public class Payment extends JFrame {
    JLabel LThaniel, LStatus, LDue, LBalance, LAmountDue, LBalanceAfterPay;
    //JButton Confirm, Back;

    public Payment() {
        setSize(560, 400);
        setLayout(null);

        LThaniel = new JLabel("PAYMENT");
        LThaniel.setBounds(200, 70, 200, 20);
        add(LThaniel);


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


        //Confirm = new JButton("Confirm");
        //Confirm.setBounds(150, 250, 100, 30);
        //Confirm.addActionListener(this);
        //frame.add(Confirm);

        //Back = new JButton("Back");
        //Back.setBounds(300, 250, 100, 30);
        //Back.addActionListener(this);
        //frame.add(Back);


        setVisible(true);
    }

}