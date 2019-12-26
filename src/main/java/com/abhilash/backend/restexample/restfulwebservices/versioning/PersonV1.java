package com.abhilash.backend.restexample.restfulwebservices.versioning;

/* OLD API */
public class PersonV1 {
    private String Name;

    public PersonV1(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
