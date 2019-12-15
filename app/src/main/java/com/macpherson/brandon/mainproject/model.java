package com.macpherson.brandon.mainproject;

/*
This class holds all the info for a datalist entry
*/
public class model {
    public String item, info, address, city, state, location;
    public int photo;

    public model(String item, String info, String address, String city, String state, String location, int photo) {
        this.item = item;
        this.info = info;
        this.address = address;
        this.city = city;
        this.state = state;
        this.location = location;
        this.photo = photo;
    }

    public model(String item, String info, String address, String city, String state, int photo) {
        this.item = item;
        this.info = info;
        this.address = address;
        this.city = city;
        this.state = state;
        this.photo = photo;
        this.location = String.format("%s\n%s, %s", address, city, state);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
