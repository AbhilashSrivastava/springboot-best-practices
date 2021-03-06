package com.abhilash.backend.restexample.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/*USING STATIC ARRAYLIST TO SET THE VALUES */
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveOneUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("ID-" + id);
        }

        Resource<User> resource = new Resource<User>(user);

        /*TO CREATE A BACK-LINK TO ALL USERS PAGE*/
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User newUser = service.save(user);

        /* TO RESPOND WITH THE URL OF THE NEW RESOURCE CREATED */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteOne(id);
        if (user == null) {
            throw new UserNotFoundException("id=" + id);
        }
    }
}
