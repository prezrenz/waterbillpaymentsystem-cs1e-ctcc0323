
package com.cs1e;

import java.util.ArrayList;
import java.io.*;

public class Database {
    static class DatabaseError extends RuntimeException {
        String msg;

        DatabaseError(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    ArrayList<User> users = new ArrayList<User>();

    String databaseFileName = "users.dat";

    Database() {
        File databaseFile = new File(databaseFileName);

        if(!databaseFile.exists()) {
            User admin = new User("admin", "admin", "root");
            users.add(admin);

            try {
                FileWriter databaseWriter = new FileWriter(databaseFile); 
                databaseWriter.write(usersToString());

                databaseWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileReader databaseReader = new FileReader(databaseFile); 
                BufferedReader reader = new BufferedReader(databaseReader);

                String line = reader.readLine();

                while(line != null) {
                    users.add(stringToUser(line));
                    line = reader.readLine();
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    void usersToFile()
    {
        try {
            FileWriter databaseWriter = new FileWriter(databaseFileName);
            databaseWriter.write(usersToString());

            databaseWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String usersToString() {
        String data = "";

        for (User user : users) {
            data = data.concat(user.stringify());
        }

        return data;
    }

    User stringToUser(String data) {
        String[] split = data.split(",");

        String name = split[0];
        String email = split[1];
        String password = split[2];
        boolean isAdmin = Boolean.valueOf(split[3]);

        String address = split[4];
        int previousReading = Integer.valueOf(split[5]);
        int currentReading = Integer.valueOf(split[6]);
        int consumption = Integer.valueOf(split[7]);
        int totalDue = Integer.valueOf(split[8]);
        String dueDate = split[9];
        String status = split[10];

        String creditCardNumber = split[11];
        int balance = Integer.valueOf(split[12]);
        
        User newUser = new User(name, email, password, isAdmin, address,
                                previousReading, currentReading, consumption,
                                totalDue, dueDate, status, creditCardNumber, balance);

        return newUser;
    }

    void newUser(String name, String email, String password, String address, String creditCardNumber) {
        for (User user : users) {
            if(name == user.name) {
                throw new DatabaseError("User already exists");
            }
        }

        User newUser = new User(name, email, password, false, address, 0, 0, 0, 0, "11/11/1111", "UNVERIFIED", creditCardNumber, 1000);

        users.add(newUser);

    }
}
