package service;

import dto.LoginDto;
import dto.RegisterDto;
import dto.ResetPwdDto;
import dto.UserDto;

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
