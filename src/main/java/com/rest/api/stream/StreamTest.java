package com.rest.api.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args){


        List<String> list = new ArrayList<>();
        list.add("ccc");
        list.add("bbb");
        list.add("aaa");

        list.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
        list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).forEach(System.out::println);

        List<String> list2 = list.stream().sorted().collect(Collectors.toList());

        System.out.println("------------");
        for(String a : list2){
            System.out.println(a);
        }

        TestDto testDto = TestDto.builder().id(1).build();
        System.out.println("testDto = " + testDto);


        TestEntity testEntity = testDto.toEntity();
        System.out.println("testEntity = " + testEntity);
        

        TestDto testDto1 = testEntity.toDto();
        System.out.println("testDto1 = " + testDto1);


        List<TestEntity> list3= new ArrayList<>();
        list3.add(TestEntity.builder().id(1).build());
        list3.add(TestEntity.builder().id(2).build());
        list3.add(TestEntity.builder().id(3).build());


        //List<TestDto> testDto =


    }
}
