package com.myapplicationdev.android.pd4;

public class Card {

    private int id;
    private String name;
    private String birthday;
    private int horoscope;
    private int phone;
    private String address;
    private String fav;

    public Card(int id, String name, String birthday, int horoscope, int phone, String address, String fav) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.horoscope = horoscope;
        this.phone = phone;
        this.address = address;
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(int horoscope) {
        this.horoscope = horoscope;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }
}
