package com.rest.api.events;

import jdk.dynalink.linker.support.Guards;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Infleran")
                .description("test")
                .build();
        assertThat(event).isNotNull();
    }

}