package com.trial.volunteerservice;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, SQLException {

        Properties properties  = new Properties();
        try {
            properties.load(new FileInputStream("volunteer.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String url = properties.getProperty("dburl");
        String userName = properties.getProperty("user");
        String password = properties.getProperty("password");


        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("------------------------------------");
        System.out.println("Welcome to Volunteer Service System.");
        System.out.println("1. New Registration. \n2. Update Info. \n3. Delete Profile. \n4.List of all volunteers.\n5.Exit\n" +
                "Enter Your Choice: ");
        choice = sc.nextInt();

        Connection connection = DriverManager.getConnection(url,userName,password);
        Statement statement = connection.createStatement();
        switch (choice) {
            case 1: {
                String[] Info = new String[4];
                Info = VolunteerProfile.getInput();
                int age = Integer.parseInt(Info[2]);
                PreparedStatement pstate = connection.prepareStatement("Insert Into volunteer (Name,Gender,age,Mobile) values (?,?,?,?)");
                pstate.setString(1, Info[0]);
                pstate.setString(2, Info[1]);
                pstate.setInt(3, age);
                pstate.setString(4, Info[3]);
                pstate.executeUpdate();

                pstate = connection.prepareStatement("select last_insert_id() from volunteer");
                ResultSet rs = pstate.executeQuery();
                rs.next();
                int ID = Integer.valueOf(rs.getString(1));
                System.out.println("Your ID : " + ID);
                break;
            }
            case 2: {
                System.out.println("------------------------------");
                System.out.print("Enter Your ID: ");
                int id = sc.nextInt();
                String[] Info = new String[4];
                Info = UpdateClass.update(id);
                CallableStatement state = connection.prepareCall("{call update_var(?,?,?,?,?)}");
                state.setString(1, Info[0]);
                state.setString(2, Info[1]);
                state.setInt(3, Integer.parseInt(Info[2]));
                state.setString(4, Info[3]);
                state.setInt(5, id);
                state.execute();
                break;
            }
            case 3: {
                System.out.println("--------------------------------");
                System.out.print("Enter Your ID: ");
                int id = sc.nextInt();
                CallableStatement state = connection.prepareCall("{call delete_row(?)}");
                state.setInt(1, id);
                state.execute();
                break;
            }
            case 4: {
                System.out.println("---------------------------------");
                System.out.println("List of all volunteers:");
                PreparedStatement state = connection.prepareStatement("SELECT * from volunteer");
                ResultSet rs = state.executeQuery();
                while (rs.next()) {
                    System.out.println(' ' + rs.getString(2) + ' ' + rs.getString(3) + ' ' + rs.getString(4) + ' ' + rs.getString(4));
                }
                System.out.println("----------------------------");
                System.out.print("Enter 1 to go back: ");
                int c = sc.nextInt();
                if(c==1)
                {
                    main(new String[]{"1","2"});
                }
                else return;
            }
            case 5: return;

            default:
                System.out.println("Illegal choice");
        }
        main(new String[]{"1","2"});
    }
}
