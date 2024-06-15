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

    App(final int WIDTH, final int HEIGHT) {
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
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // centers the frame
        setVisible(true);
    }

    public static void main( String[] args ) {
        final int WIDTH = 540;
        final int HEIGHT = 400;

        new App(WIDTH, HEIGHT);
    }
}
