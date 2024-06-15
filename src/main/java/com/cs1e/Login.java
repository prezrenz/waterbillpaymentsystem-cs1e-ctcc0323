package com.cs1e;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JPanel {
    JLabel Title, Email, Password;
    JTextField TEmail, TPassword;
    JButton Login, Register, Exit;


        Login() {
        setPreferredSize(new Dimension(540, 400));
        setLayout(null);

        Title = new JLabel("Aqua Water Bill Payment System");
        Title.setBounds(190, 10, 300, 20);
        add(Title);

        Email = new JLabel("Email");
        Email.setBounds(50, 50, 300, 20);
        add(Email);

        Password = new JLabel("Password");
        Password.setBounds(50, 90, 300, 20);
        add(Password);

        TEmail = new JTextField(32);
        TEmail.setBounds(150, 50, 300, 20);
        add(TEmail);

        TPassword = new JTextField(32);
        TPassword.setBounds(150, 90, 300, 20);
        add(TPassword);

        Login = new JButton("Login");
        Login.setBounds(150, 140, 120, 30);
        //Back.addActionListener(this);
        add(Login);

        Register = new JButton("Register");
        Register.setBounds(300, 140, 120, 30);
        //Back.addActionListener(this);
        add(Register);

        Exit = new JButton("Exit");
        Exit.setBounds(225, 200, 120, 30);
        //Back.addActionListener(this);
        add(Exit);
    }
}
