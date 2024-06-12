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

    App() {
        database = new Database();

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        loginScreen = new Login();

        mainPanel.add(loginScreen, "Login");
        
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
