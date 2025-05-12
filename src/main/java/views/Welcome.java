package main.java.views;

import java.util.Scanner;
import main.java.model.User;
import main.java.service.GenerateOTP;
import main.java.service.SendOTPService;
import main.java.service.UserService;

public class Welcome {
    private final Scanner sc = new Scanner(System.in);

    public void welcomeScreen() {
        while (true) {
            System.out.println("\n--- Welcome ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void register() {
        System.out.println("\n--- Register ---");
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        String otp = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, otp);

        System.out.print("Enter the OTP sent to your email: ");
        String enteredOtp = sc.nextLine();

        if (otp.equals(enteredOtp)) {
            User user = new User(name, email);
            Integer result = UserService.saveUser(user);
            if (result != null && result == 1) {
                System.out.println("Registration successful!");
                new UserViews(email).home();
            } else if (result == 0) {
                System.out.println("User already exists. Try logging in.");
            } else {
                System.out.println("Registration failed due to internal error.");
            }
        } else {
            System.out.println("Incorrect OTP. Registration failed.");
        }
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        String otp = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, otp);

        System.out.print("Enter the OTP sent to your email: ");
        String enteredOtp = sc.nextLine();

        if (otp.equals(enteredOtp)) {
            System.out.println("Login successful!");
            new UserViews(email).home();
        } else {
            System.out.println("Incorrect OTP. Login failed.");
        }
    }
}
