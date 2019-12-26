package com.abhilash.backend.restexample.restfulwebservices.user;

import com.abhilash.backend.restexample.restfulwebservices.post.Post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    /* TO SET IT AS THE PRIMARY KEY TO THE DB */
    @Id
    /* TO GENERATE THE VALUE FROM DATABASE WHEN USING ONE */
    @GeneratedValue
    private Integer id;

    @Size(min=2)
    private String name;

    @Past
    private Date birthDate;

    /*TO REMEMBER: One X To Y ... X Being the current class*/
    /* mappedBy SPECIFIES THAT WHICH ENTITY IS GOING TO HAVE THE OWNERSHIP OF RELATION, AS IN WHICH ENITY WILL HAVE A RELATIONSHIP TABLE */
    @OneToMany(mappedBy="user")
    private List<Post> posts;

    /* TO HAVE AN EMPTY CONSTRUCTOR */
    protected  User() {}
    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
