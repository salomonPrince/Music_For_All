package com.example.music_for_all;

public class Artist {
    private String name;
    private String realName;
    private String dateOfBirth;
    private String nationality;

    // Constructor
    public Artist(String name, String realName, String dateOfBirth, String nationality) {
        this.name = name;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }


    public String getName() {
        return name;
    }


    public String getRealName() {
        return realName;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public String getNationality() {
        return nationality;
    }
}



