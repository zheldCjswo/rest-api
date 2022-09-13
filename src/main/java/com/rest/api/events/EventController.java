package com.rest.api.events;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Controller
@RequestMapping(value="/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    private final EventValidator eventValidator;


    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors); //errors를 json으로 변경 불가함
        }

        eventValidator.validate(eventDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = this.eventRepository.save(event);

        var selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createdUri = selfLinkBuilder.toUri();
        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
        return ResponseEntity.created(createdUri).body(eventResource);
    }

    @GetMapping
    public ResponseEntity queryEvents(Pageable pageable, PagedResourcesAssembler<Event> assembler) {
        Page<Event> page = this.eventRepository.findAll(pageable);
        PagedModel<EventResource> pageModel = assembler.toModel(page, e -> new EventResource(e));
        pageModel.add(new Link("/docs/index.html#resources-events-querys-list").withRel("profile"));
        return ResponseEntity.ok().body(pageModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity queryEventOne(@PathVariable("id") Long id) throws IllegalAccessException {
        Optional<Event> eventOptional = this.eventRepository.findById(id);
        if (eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event event = eventOptional.get();
        EventResource eventResource = new EventResource(event);
        eventResource.add(new Link("/docs/index.html#resources-events-querys-get").withRel("profile"));
        return ResponseEntity.ok(eventResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Long id,
                                      @RequestBody @Valid EventDto eventDto,
                                      Errors errors){
        Optional<Event> event  = this.eventRepository.findById(id);
        if(event.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(errors.hasErrors()){
            return badReqeust(errors);
        }

        this.eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return badReqeust(errors);
        }
        Event existingEvent = event.get();
        this.modelMapper.map(eventDto, existingEvent);
        Event savedEvent = this.eventRepository.save(existingEvent);

        EventResource eventResource = new EventResource(savedEvent);
        eventResource.add(new Link("/docs/index.html#resources-events-querys-get").withRel("profile"));
        return ResponseEntity.ok(eventResource);


    }

    private ResponseEntity badReqeust(Errors errors) {
        return ResponseEntity.badRequest().body(errors);
    }
}
