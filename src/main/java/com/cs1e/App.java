package com.cs1e;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App extends JFrame 
{
    JPanel mainPanel;
    CardLayout cardLayout;
    Database database;

    Login loginScreen;
    Registration registrationScreen;

    App() {
        database = new Database();

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        add(mainPanel);

        loginScreen = new Login();
        registrationScreen = new Registration(this);

        mainPanel.add(loginScreen, "Login");
        mainPanel.add(registrationScreen, "Register");

        cardLayout.show(mainPanel, "Register");
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLayout(new FlowLayout());
        setSize(960, 540);
        setVisible(true);
    }

    public static void main( String[] args ) {
        new App();
    }
}
