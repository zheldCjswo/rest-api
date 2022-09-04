package com.rest.api.events;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class EntityModelTest extends EntityModel<Event> {

    public EntityModelTest(Event content, Link... links) {

        super(content, links);
        //this.add(linkTo(methodOn(this.getClass()).re)
    }
}
