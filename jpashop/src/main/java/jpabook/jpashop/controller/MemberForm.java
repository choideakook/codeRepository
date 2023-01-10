package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty (message = "need your name ")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
