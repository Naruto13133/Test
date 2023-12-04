package com.atm.pojo;

import java.sql.Date;
import java.time.LocalDate;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String email;
    private String phoneNumber;
    private String country;
    private String secretKey;
    private String password;
    private String isAuthenticated;
    

    // Constructors
    public Patient() {
        // Default constructor
    }

    public Patient(int patientID, String firstName, String lastName, Date dateOfBirth, String gender,
                   String address, String city, String state, String zipCode, String email, String phoneNumber ,
                   String country, String secretKey , String password, String isAuthenticated ) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.secretKey=secretKey ;
        this.password = password ;
        this.isAuthenticated = isAuthenticated;
    }

    // Getters and Setters for all fields
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    public String getCountry() {
        return phoneNumber;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country ='" + country + '\'' +
                ", secretKey  ='" + secretKey  + '\'' +
                ", password  ='" + password  + '\'' +
                '}';
    }

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(String isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}