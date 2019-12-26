package com.abhilash.backend.restexample.restfulwebservices.user;

import com.abhilash.backend.restexample.restfulwebservices.post.Post;
import com.abhilash.backend.restexample.restfulwebservices.post.PostRepository;
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
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/*USING IN MEMORY DATABASE H2*/
/* localhost:8080/h2-console */
/* JDBC URL: jdbc:h2:mem:testdb */
@RestController
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveOneUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID-" + id);
        }

        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    @PostMapping("/jpa/users")
    public ResponseEntity createUser(
            @Valid /* TO VALIDATE THE USER DATA RECEIVED AND THROW AN INVALID REQUEST ERROR IF INVALID. VALIDATION HAPPENS THROUGH FIELDS GIVEN IN USER BEAN*/
            @RequestBody /*TO NOTIFY THAT THE RECEIVED PARAMETERS ARE VIA THE DATA IN REQUEST BODY*/
                User user
    ) {
        User newUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retreiveAllPosts(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id="+id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> optionalUser =  userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("id="+id);
        }
        User user = optionalUser.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
