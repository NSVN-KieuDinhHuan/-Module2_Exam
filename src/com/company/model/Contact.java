package com.company.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String phoneNumber;
    private String contactGroup;
    private String name;
    private String sex;
    private String adress;
    private String dateOfBirth;
    private String email;

    public Contact(String phoneNumber, String contactGroup, String name, String sex, String adress, String dateOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.contactGroup = contactGroup;
        this.name = name;
        this.sex = sex;
        this.adress = adress;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactGroup() {
        return contactGroup;
    }

    public void setContactGroup(String contactGroup) {
        this.contactGroup = contactGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  phoneNumber  +
                "," + contactGroup  +
                "," + name  +
                "," + sex +
                "," + adress  +
                "," + dateOfBirth +
                "," + email + '\n';
    }
}
