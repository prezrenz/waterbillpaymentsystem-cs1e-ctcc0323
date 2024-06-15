package com.cs1e;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Dashboard extends JPanel {
    JLabel JWelcome, JRenjie, JDIE, JCons, JCurrent, JPrevious;
    JButton LChange, LPass, LAddress, LEmail, LPay, LLogout;

    public Dashboard() {
        setPreferredSize(new Dimension(540, 400));
        setLayout(null);

        JWelcome = new JLabel("Welcome, valued custumer!");
        JWelcome.setBounds(190, 10, 300, 20);
        add(JWelcome);

        JRenjie = new JLabel("Status: UNPAID");
        JRenjie.setBounds(150, 35, 300, 20);
        add(JRenjie);

        JDIE = new JLabel("Date: DD/MM/YYYY");
        JDIE.setBounds(300, 35, 300, 20);
        add(JDIE);


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
        //Confirm.addActionListener(this);
        add(LChange);

        LPass = new JButton("Change Password");
        LPass.setBounds(275, 150, 150, 30);
        //Back.addActionListener(this);
        add(LPass);

        LAddress = new JButton("Change Address");
        LAddress.setBounds(100, 200, 150, 30);
        //Back.addActionListener(this);
        add(LAddress);

        LEmail = new JButton("Change Email");
        LEmail.setBounds(275, 200, 150, 30);
        //Back.addActionListener(this);
        add(LEmail);

        LPay = new JButton("Pay");
        LPay.setBounds(185, 250, 150, 30);
        //Back.addActionListener(this);
        add(LPay);

        LLogout = new JButton("Logout");
        LLogout.setBounds(185, 300, 150, 30);
        //Back.addActionListener(this);
        add(LLogout);


        setVisible(true);
    }

}

