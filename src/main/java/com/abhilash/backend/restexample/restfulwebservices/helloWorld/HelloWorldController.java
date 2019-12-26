package com.abhilash.backend.restexample.restfulwebservices.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    /* RETURNED AS A STRING */
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World Again!a!";
    }

    /* RETURNED AS A BEAN, CONVERTED AS JSON */
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World From Bean");
    }

    /* RETURNED AS A BEAN, CONVERTED AS JSON, AND PATH VARIABLE USED TO FETCH THE NAME */
    @GetMapping("/hello-world/{name}")
    public HelloWorldBean helloWorldPathBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    /* INTERNATIONALIZATION EXAMPLE */
    @GetMapping("/hello-world-i18n")
    public String helloWorldI18n() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
