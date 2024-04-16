package com.ashokit.dto;

import lombok.Data;

@Data
public class RegisterDto {


    private  String name;
    private String email;



    private String phno;

    private Integer country_id;
    private Integer state_id;
    private Integer city_id;



}
