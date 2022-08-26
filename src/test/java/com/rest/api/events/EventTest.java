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

    @Test
    public void testFree() {
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();

        event.update();

        assertThat(event.isFree()).isTrue();

        event = Event.builder()
                .basePrice(10)
                .maxPrice(100)
                .build();

        event.update();

        assertThat(event.isFree()).isFalse();
    }

    @Test
    public void testOffine() {
        Event event = Event.builder()
                .location("강남역 D2 스타텁 팩토리")
                .build();

        event.update();

        assertThat(event.isOffline()).isTrue();

        event = Event.builder()
                .build();

        event.update();

        assertThat(event.isOffline()).isFalse();
    }

}