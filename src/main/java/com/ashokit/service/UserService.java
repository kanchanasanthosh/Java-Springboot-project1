package com.ashokit.service;

import com.ashokit.dto.LoginDto;
import com.ashokit.dto.RegisterDto;
import com.ashokit.dto.ResetPwdDto;
import com.ashokit.dto.UserDto;

import java.util.Map;

public interface UserService  {


    public Map<Integer,String> getCountries();

    public Map<Integer,String> getStates(Integer cid);

    public Map<Integer,String> getCities(Integer sid);

    public UserDto getUser(String email);

    public boolean registerUser(RegisterDto regDto);

    public UserDto getUserLogin(LoginDto loginDto);

    public boolean resetPwd(ResetPwdDto pwdDto);

    public String getQuote(); // api-call







}
