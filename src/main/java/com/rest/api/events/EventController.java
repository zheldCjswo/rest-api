package com.rest.api.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value="/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto,  Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI cratedUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return (ResponseEntity) ResponseEntity.created(cratedUri).body(event);
    }
}
