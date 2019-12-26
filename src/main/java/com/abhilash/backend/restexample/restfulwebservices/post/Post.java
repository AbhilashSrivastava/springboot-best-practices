package com.abhilash.backend.restexample.restfulwebservices.post;

import com.abhilash.backend.restexample.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;

    /* MANY X TO Y.. X BEING CURRENT CLASS*/
    /*fetchType SPECIFIES THAT POSTS WILL NOT BE AUTOFETCHED ON USERS CALL*/
    /*DOESNT FETCH AND SHOW USER JSON FOR POSTS*/
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected Post(){}

    public Post(Integer id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }
}
