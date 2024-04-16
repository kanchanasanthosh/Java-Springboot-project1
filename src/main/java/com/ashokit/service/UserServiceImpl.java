package com.ashokit.service;

import com.ashokit.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ashokit.entity.CityData;
import com.ashokit.entity.CountryData;
import com.ashokit.entity.StatesData;
import com.ashokit.entity.UserData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ashokit.repository.CityRepository;
import com.ashokit.repository.CountryRepository;
import com.ashokit.repository.StateRepository;
import com.ashokit.repository.UserRepository;
import com.ashokit.utils.EmailUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CountryRepository countryRepo;
    @Autowired
    private StateRepository stateRepo;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public Map<Integer, String> getCountries() {
    List<CountryData> countriesList= countryRepo.findAll();
    Map<Integer,String> countryMap =  new HashMap<>();
    countriesList.forEach(c-> {
        countryMap.put(c.getCountry_id(),c.getCountry_name());
    });

        return countryMap;
    }
    @Override
    public Map<Integer, String> getStates(Integer cid) {
        Map<Integer,String> statesMap = new HashMap<>();
        /* CountryData countryData= new CountryData();
        countryData.setCountry_id(cid);
        StatesData statesData= new StatesData();
        statesData.setCountryData(countryData);
        Example<StatesData> of = Example.of(statesData);
         List<StatesData> statesList =stateRepo.findAll(of);   */
       List<StatesData> statesList =stateRepo.getStates(cid);
        statesList.forEach(s-> {
            statesMap.put(s.getState_id(),s.getState_name());
        });

        return statesMap;
    }
    @Override
    public Map<Integer, String> getCities(Integer sid) {
        Map<Integer , String> citiesMap = new HashMap<>();

        List< CityData>   citiesList =cityRepo.getCities(sid);
      citiesList.forEach(c->{
          citiesMap.put(c.getCity_id(),c.getCity_name());
      });

        return citiesMap;
    }
    @Override
    public UserDto getUser(String email) {
        //option:1. using bean utils properties
       UserData userData= userRepo.findByEmail(email);
       /*
       UserDto udto = new UserDto();
        BeanUtils.copyProperties(userData,udto);  */
        //option 2: using Modal mapper
        if(userData ==null){
            return  null;
        }
        ModelMapper mapper = new ModelMapper();
        UserDto udto = mapper.map(userData,UserDto.class);
        return udto;
    }

    @Override
    public boolean registerUser(RegisterDto regDto) {
    ModelMapper mapper = new ModelMapper();
    UserData userData = mapper.map(regDto,UserData.class);
    CountryData countryData = countryRepo.findById(regDto.getCountry_id()).orElseThrow();
    StatesData statesData=stateRepo.findById(regDto.getState_id()).orElseThrow();
    CityData cityData=cityRepo.findById(regDto.getCity_id()).orElseThrow();
        userData.setCountry(countryData);
        userData.setState(statesData);
        userData.setCity(cityData);
        userData.setPwd(generateRandom());
        userData.setPwd_updated("No");
        // user registration
        UserData savedEntity =userRepo.save(userData);
        String subject ="User Registration";
        String body = "your temporary pwd is " + userData.getPwd();
        emailUtils.sendEmail(regDto.getEmail(), subject,body);
        return savedEntity.getUserId() != null;
    }

    @Override
    public UserDto getUserLogin(LoginDto loginDto) {
             UserData userData =userRepo.findByEmailandPwd(loginDto.getEmail(),loginDto.getPwd());
             if(userData == null){
                 return  null;
             }
    ModelMapper mapper = new ModelMapper();
        return mapper.map(userData,UserDto.class);
    }

    @Override
    public boolean resetPwd(ResetPwdDto pwdDto) {
        UserData userData=userRepo.findByEmailandPwd(pwdDto.getEmail(),pwdDto.getOldpwd());
        if(userData != null) {
            userData.setPwd(pwdDto.getNewpwd());
            userData.setPwd_updated("yes");
            userRepo.save(userData);
            return  true;
        }
            return false;
    }
    @Override
    public String getQuote() {
        QuoteDto[] quotations=null;
        String url ="https://type.fit/api/quotes";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String>  forEntity =rt.getForEntity(url,String.class);
      String responsebody=  forEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        try {
          quotations=  mapper.readValue(responsebody, QuoteDto[].class);
        }catch (Exception e) {
        e.printStackTrace();
        }
        Random random = new Random();
       int index= random.nextInt(quotations.length-1);
            return quotations[index].getText();
        }
    private  static String generateRandom(){
        String aToz = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<5; i++){
            int randIndex = random.nextInt(aToz.length());
            builder.append(aToz.charAt(randIndex));
        }
        return builder.toString();
    }

}
