package com.rest.api.index;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
class IndexController {

    @GetMapping("/api")
    public RepresentationModel index(){
        RepresentationModel index = new RepresentationModel();
        index.add(linkTo(IndexController.class).withRel("events"));
        return index;
    }
}
// https://stalker5217.netlify.app/spring/hateoas/
// https://haruhiism.tistory.com/200 설명 참고 정리가 잘되어 있음

//        현재 스프링 부트 공식 페이지 보고 알았네요.
//        따라하시는 분들, 아마 Resource 임포트 안되실 겁니다.
//        강의 이후에 공식 클래스 이름이 바뀌었나보네요.
//        도큐먼트에 다음과 같이 나와있습니다. 참고하세요.
//        ResourceSupport is now RepresentationModel
//        Resource is now EntityModel
//        Resources is now CollectionModel
//        PagedResources is now PagedModel

//        ControllerLinkBuilder.linkTo
//
//        ControllerLinkBuilder.methodOn
//
//        사용하실 때 ControllerLinkBuilder -> WebMvcLinkBuilder로 변경되었습니다.

//    }