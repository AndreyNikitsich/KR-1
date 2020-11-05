package com.andrey;







public class Sportsman {
    private String cardID;
    private String name;
    private String birthday;
    private String kindOfSport;
    private String countOfVictories;

    public Sportsman(String[] fields) throws IllegalArgumentException {
        if (fields.length != 5) {
            throw new IllegalArgumentException("Incorrect format of data");
        } else {
            this.cardID = fields[0];
            this.name = fields[1];
            this.birthday = fields[2];
            this.kindOfSport = fields[3];
            this.countOfVictories = fields[4];
        }
    }


    public static String[] stringToArrayOfFields(String str) {
        return str.split(";");
    }

    @Override
    public String toString() {
        return String.join(" ", name, birthday,kindOfSport, countOfVictories);
    }

    public String getCardID() {
        return cardID;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public String getCountOfVictories() {
        return countOfVictories;
    }
}
