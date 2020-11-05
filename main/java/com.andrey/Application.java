package com.andrey;

import javax.xml.parsers.SAXParser;
import java.awt.desktop.SystemSleepEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static String USERS_TXT = "src\\inputFiles\\users.txt";
    private static String SPORT_TXT = "src\\inputFiles\\sport.txt";
    private static String MENU = "\nChoose point of menu:\n1.Register new user\n2.Log in\n3.Show all entries\n4.Find using cardID\n7.Exit7";
    private static User currentUser;
    public static void main(String[] args) {
        List<User> users;
        List<Sportsman> sportsmen;
        boolean loged = false;


        try (Scanner scanner = new Scanner(System.in)) {
            users = loadUsers(USERS_TXT);
            sportsmen = loadSportsmen(SPORT_TXT);
            while (true) {
                System.out.println(MENU);
                int pointMenu = scanner.nextInt();
                switch (pointMenu) {
                    case 1:
                        String[] fields = new String[5];
                        System.out.println("Input username: ");
                        scanner.nextLine();
                        fields[0] = scanner.nextLine();
                        System.out.println("Input login: ");
                        fields[1] = scanner.nextLine();
                        System.out.println("Input email: ");
                        fields[2] = scanner.nextLine();
                        System.out.println("Input password: ");
                        fields[3] = scanner.nextLine();
                        fields[4] = "USER";
                        User newUser = new User(fields);
                        registration(newUser, users);
                        break;

                    case 2:
                        String login;
                        String password;
                        System.out.println("Input login: ");
                        scanner.nextLine();
                        login = scanner.nextLine();
                        System.out.println("Input password: ");
                        password = scanner.nextLine();
                        if(login(login, password,users)){
                            System.out.println("You've successfully logged");
                        } else {
                            System.out.println("Login or password was incorrect");
                        }
                        break;

                    case 3:
                        System.out.println("All sportsmen:\n");
                        showAllEntries(sportsmen);
                        break;


                    case 4:
                        if (!loged){
                            System.out.println("To use this option log in, please");
                            continue;
                        } else {
                            if(currentUser.getUserRole().equals("ADMIN")){
                                System.out.println("Input sportsman code:\n");
                                scanner.nextLine();
                                String cardID = scanner.nextLine();
                                System.out.println(findUsingID(cardID, sportsmen));
                            }
                        }
                        break;

                    case 7:
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static List<User> loadUsers(String fileName) throws IOException {
        List<User> users = new ArrayList<>();
        int row = 0;
        try (FileInputStream USERS_STREAM = new FileInputStream(USERS_TXT); Scanner scanner = new Scanner(USERS_STREAM)) {
            while (scanner.hasNextLine()) {
                row++;
                users.add(new User(User.stringToArrayOfFields(scanner.nextLine())));
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage() + " in row: " + row);
        }
        return users;
    }


    private static List<Sportsman> loadSportsmen(String filename) throws IOException {
        List<Sportsman> sportsmen = new ArrayList<>();
        int row = 0;
        try (FileInputStream SPORTSMEN_STREAM = new FileInputStream(SPORT_TXT); Scanner scanner = new Scanner(SPORTSMEN_STREAM)) {
            while (scanner.hasNextLine()) {
                row++;
                sportsmen.add(new Sportsman(Sportsman.stringToArrayOfFields(scanner.nextLine())));
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage() + " in row: " + row);
        }
        return sportsmen;
    }

    private static void registration(User user, List<User> database) throws IOException {
        for (User i : database) {
            if (user.getLogin().equals(i.getLogin())) {
                System.out.println("You have been already registred.");
                return;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(USERS_TXT, true)) {
            database.add(user);
            fileOutputStream.write(user.toString().getBytes());
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }

    }

    private static boolean login(String login, String password, List<User> database) {
        boolean successfully = false;
        for (User i : database) {
            if (i.getLogin().equals(login)) {
                if (i.getPassword().equals(password)) {
                    successfully = true;
                    currentUser = i;
                    break;
                }
            }
        }
        return successfully;
    }

    private static void showAllEntries(List<Sportsman> sportsmen) {
        for (Sportsman i : sportsmen) {
            System.out.println(i.toString());
        }
    }

    private static Sportsman findUsingID(String cardID, List<Sportsman> sportsmen) throws Exception {
        for (Sportsman i : sportsmen) {
            if (i.getCardID().equals(cardID)) {
                return i;
            }
        }
        throw new Exception("Sportsman with this card number wasn't found");

    }
}
