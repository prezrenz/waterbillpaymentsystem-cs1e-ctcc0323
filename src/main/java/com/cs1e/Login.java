package com.cs1e;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cs1e.Database.DatabaseError;

public class Login extends JPanel {
    App mainApp;

    JLabel Title, Email, Password;
    JTextField TEmail, TPassword;
    JButton Login, Register, Exit;

    Login(App parent) {
        mainApp = parent;

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
        Login.addActionListener((ae) -> login());
        add(Login);

        Register = new JButton("Register");
        Register.setBounds(300, 140, 120, 30);
        Register.addActionListener((ae) -> register());
        add(Register);

        Exit = new JButton("Exit");
        Exit.setBounds(225, 200, 120, 30);
        Exit.addActionListener((ae) -> exit());
        add(Exit);
    }

    private void login() {
        try {
            String email = TEmail.getText();
            String password = TPassword.getText();

            System.out.println(email.isEmpty());

            if((email.isEmpty()) || (password.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill up all the fields", "Login Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = mainApp.database.findUser(email);

            if(user.password.equals(password)) {
                JOptionPane.showMessageDialog(mainApp, "Successfully logged in!");
                mainApp.currentUser = user;
                mainApp.dashboardScreen.setup();
                mainApp.cardLayout.show(mainApp.mainPanel, "Dashboard");
                return;
            }
            
            throw new DatabaseError("Wrong password");
        } catch (DatabaseError e) { 
            JOptionPane.showMessageDialog(mainApp, e.getMsg(), "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void register() {
        mainApp.cardLayout.show(mainApp.mainPanel, "Register");
        mainApp.database.usersToFile();
    }

    private void exit() {
        mainApp.database.usersToFile();
        System.exit(0);
    }
}
