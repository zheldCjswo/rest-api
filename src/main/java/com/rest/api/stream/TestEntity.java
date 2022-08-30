package com.rest.api.stream;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class TestEntity {

    @Id
    @GeneratedValue
    private int id;

    @Builder
    public TestEntity(int id){
        this.id = id;
    }

    public TestDto toDto(){
        return TestDto.builder().id(this.id).build();
    }
}
