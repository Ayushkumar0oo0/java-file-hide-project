package main.java.views;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import main.java.dao.Datadao;
import main.java.model.Data;

public class UserViews {
    private String email;

    public UserViews(String email) {
        this.email = email;
    }

    public void home() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome " + this.email);
            System.out.println("1. Show hidden files");
            System.out.println("2. Hide new file");
            System.out.println("3. Unhide file");
            System.out.println("0. Exit");
            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = Datadao.getAllFiles(this.email);
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getfileName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Enter file path:");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(), path, this.email);
                    try {
                        Datadao.hideFile(file);
                        System.out.println("File hidden successfully.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    try {
                        List<Data> files = Datadao.getAllFiles(this.email);
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getfileName());
                        }
                        System.out.println("Enter ID to unhide:");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean valid = files.stream().anyMatch(f -> f.getId() == id);
                        if (valid) Datadao.unhide(id);
                        else System.out.println("Invalid ID.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 0 -> {
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
