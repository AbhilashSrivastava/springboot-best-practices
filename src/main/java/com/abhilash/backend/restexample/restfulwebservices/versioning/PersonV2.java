package com.abhilash.backend.restexample.restfulwebservices.versioning;

/* NEW API */
public class PersonV2 {
    private String firstName;
    private String LastName;

    public PersonV2(String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
