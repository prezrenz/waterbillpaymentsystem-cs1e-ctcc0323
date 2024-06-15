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
    User currentUser;

    Login loginScreen;
    Registration registrationScreen;
    Admin adminScreen;
    NewReading newReadingScreen;
    Payment paymentScreen;

    App(final int WIDTH, final int HEIGHT) {
        database = new Database();

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        add(mainPanel);

        loginScreen = new Login();
        registrationScreen = new Registration(this);
        adminScreen = new Admin(this);
        newReadingScreen = new NewReading(this);
        paymentScreen = new Payment(this);

        mainPanel.add(loginScreen, "Login");
        mainPanel.add(registrationScreen, "Register");
        mainPanel.add(adminScreen, "Admin");
        mainPanel.add(newReadingScreen, "NewReading");
        mainPanel.add(paymentScreen, "Payment");
        cardLayout.show(mainPanel, "Payment");

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
        final int WIDTH = 560;
        final int HEIGHT = 400;

        new App(WIDTH, HEIGHT);
    }
}
