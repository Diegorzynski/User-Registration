package org.registration;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class User {
    private String name;
    private String lastName;
    private String pesel;
    private String email;
    private String city;
    private String zipCode;


    public User(String name, String lastName, String city, String zipCode) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setEmail(String email) {
        if(EmailValidation.isValid(email)){
            this.email = email;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPesel(String pesel) throws UnsupportedEncodingException {
        if(PeselValidation.isValid(pesel)){
            this.pesel = pesel;
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return pesel.equals(user.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
