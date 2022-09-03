package com.rest.api.stream;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class TestDto {
    private int id;

    public TestEntity toEntity() {
        return TestEntity.builder().id(this.id).build();
    }

    @Builder
    public TestDto(int id){
        this.id = id;
    }

    public List<TestDto> toListEntity(List<TestEntity> testEntityList){
        return testEntityList.stream().map(TestEntity::toDto).collect(Collectors.toList());
    }

}
