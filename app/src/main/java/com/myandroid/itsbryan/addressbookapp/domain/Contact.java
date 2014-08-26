package com.myandroid.itsbryan.addressbookapp.domain;

/**
 * Created by its.Bryan on 8/20/2014.
 */

public class Contact {
    private int id;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;

    private Contact(){}

    public Contact(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.emailAddress = builder.emailAddress;
        this.phoneNumber = builder.phoneNumber;
        this.homeAddress = builder.homeAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public static class Builder {
        public int id;
        public String name;
        public String surname;
        public String emailAddress;
        public String phoneNumber;
        public String homeAddress;

        public Builder(String phoneNumber){            
            this.phoneNumber = phoneNumber;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder homeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder clone(Contact contact){
            this.setId(contact.getId());
            this.name(contact.getName());
            this.surname(contact.getSurname());
            this.phoneNumber(contact.getPhoneNumber());
            this.emailAddress(contact.getEmailAddress());
            this.homeAddress(contact.getHomeAddress());
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }
    }
}
