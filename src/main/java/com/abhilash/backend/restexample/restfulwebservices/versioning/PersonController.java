package com.abhilash.backend.restexample.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    /* ------------------------------------------------------------- */
    /* URI VERSIONING */
    @GetMapping("v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Tom Hanks");
    }

    @GetMapping("v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2("Tom", "Hanks");
    }
    /* ------------------------------------------------------------- */

    /* ------------------------------------------------------------- */
    /* REQUEST-PARAM VERSIONING */
    @GetMapping(value="person/param", params="version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Tom Hanks");
    }

    @GetMapping(value="person/param", params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2("Tom", "Hanks");
    }
    /* ------------------------------------------------------------- */

    /* ------------------------------------------------------------- */
    /* HEADER VERSIONING */
    @GetMapping(value="person/header", headers="X_API_VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Tom Hanks");
    }

    @GetMapping(value="person/header", headers="X_API_VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2("Tom", "Hanks");
    }
    /* ------------------------------------------------------------- */

    /* ------------------------------------------------------------- */
    /* MIME-TYPE VERSIONING OR ACCEPT-HEADER VERSIONING. ALSO CALLED CONTENT NEGOTIATION */
    @GetMapping(value="person/produces", produces="application/app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Tom Hanks");
    }

    @GetMapping(value="person/produces", produces="application/app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2("Tom", "Hanks");
    }
    /* ------------------------------------------------------------- */

}
