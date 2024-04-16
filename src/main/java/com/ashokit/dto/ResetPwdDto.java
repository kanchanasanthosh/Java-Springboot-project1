package com.ashokit.dto;

import lombok.Data;

@Data
public class ResetPwdDto {

    private String userId;
    private String email;
    private String oldpwd;
    private String newpwd;
    private String confirmpwd;





}
