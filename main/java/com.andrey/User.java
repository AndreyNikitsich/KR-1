package com.andrey;

public class User {
    private String userName;
    private String login;
    private String email;
    private String password;
    private String userRole;


    public User(String[] fields) throws IllegalArgumentException {
        if (fields.length != 5) {
            throw new IllegalArgumentException("Incorrect format of data");
        } else {
            this.userName = fields[0];
            this.login = fields[1];
            this.email = fields[2];
            this.password = fields[3];
            this.userRole = fields[4];
        }
    }


    public static String[] stringToArrayOfFields(String str) {
        return str.split(";");
    }

    @Override
    public String toString() {
        return String.join(";", userName, login, email, password, userRole) + '\n';
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }
}
