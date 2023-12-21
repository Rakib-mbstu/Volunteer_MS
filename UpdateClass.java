package com.trial.volunteerservice;

import java.util.Scanner;

public class UpdateClass {
    public static String[] update(int id)
    {
        String name,age,gender,mobile;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Enter Your New Info: ");
        System.out.print("Name : ");
        name = sc.next();
        System.out.print("Age  : ");
        age = sc.next();
        System.out.print("Gender: ");
        gender = sc.next();
        System.out.print("Mobile: ");
        mobile = sc.next();
        if(!(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female"))){
            System.out.println("Gender is not correct!\n");
            update(id);
        }
        try {
            int a = (Integer.parseInt(age));
        }
        catch (Exception e){
            System.out.println("Incorrect age\n");
            update(id);
        }
        System.out.println("Thank you");
        String[] Info = new String[]{name,gender,age,mobile};
        return Info;
    }
}
