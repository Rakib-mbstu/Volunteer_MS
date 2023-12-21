package com.trial.volunteerservice;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class VolunteerProfile {

    private String name,age;

    public static boolean genderChecker(String gender){
         if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female")){
             return true;
         }
         else{
             return false;
         }
    }
    public static String[] getInput(){
        System.out.println("---------------------------------------");
        Scanner sc = new Scanner(System.in);
        String name,age,gender,mobile;
        System.out.println("Enter Your Info:\n");
        System.out.print("Name : ");
        name = sc.next();
        System.out.print("Age  : ");
        age = sc.next();
        System.out.print("Gender: ");
        gender = sc.next();
        System.out.print("Mobile: ");
        mobile = sc.next();
        if(!genderChecker(gender)){
            System.out.println("Gender is not correct!\n");
            getInput();
        }
        try {
            int a = (Integer.parseInt(age));
        }
        catch (Exception e){
            System.out.println("Incorrect age\n");
            getInput();
        }
        System.out.println("Thank you");
        String[] Info = new String[]{name,gender,age,mobile};
        return Info;
    }
}
