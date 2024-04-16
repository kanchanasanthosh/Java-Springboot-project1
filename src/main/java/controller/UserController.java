package controller;



import dto.LoginDto;
import dto.RegisterDto;
import dto.ResetPwdDto;
import dto.UserDto;
import entity.UserData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(UserDto userDto, Model model){
 model.addAttribute("registerDto", new RegisterDto());
 Map<Integer,String>   countriesMap =userService.getCountries();
 model.addAttribute("countries", countriesMap);
        return "registerView";
    }
    @GetMapping("/states/{cid}")
    @ResponseBody
    public Map<Integer, String> getStates(@PathVariable("cid") Integer cid){
    
        return  userService.getStates(cid);
    }
    @GetMapping("/cities/{sid}")
    @ResponseBody
    public Map<Integer, String> getCities( @PathVariable("sid") Integer sid){

        return  userService.getCities(sid);
    }

    @PostMapping("/register")
    public String register(RegisterDto registerDto, Model model){

      UserDto user =userService.getUser(registerDto.getEmail());
      if(user != null) {
          model.addAttribute("emsg","Duplicate Email");

          return "registerView";
          
      }
      boolean registeruser = userService.registerUser(registerDto);
      if(registeruser){
          model.addAttribute("smsg","user Registered successfully");
      }else {
          model.addAttribute("emsg", "Registration failed");
      }
        return "registerView";
    }

    @GetMapping("/")
    public String loginPage(Model model){
        model.addAttribute("loginDto",new LoginDto());
        return "index";
    }

    @PostMapping("/login")
    public String login(LoginDto loginDto, Model model){
       UserDto user =userService.getUserLogin(loginDto);
       if(user == null){
           model.addAttribute("emsg","Invalid credentials");
           return "index";
       }
     String pwdUpdated=  user.getPwdupdated();
       if("yes".equals(pwdUpdated)){
           //pwd already updated go to dashboard
           return  "redirect:dashboard";
       }else {
           //pwd not updated - go to reset pwd page
           ResetPwdDto resetPwdDto = new ResetPwdDto();
           resetPwdDto.setEmail(user.getEmail());
           model.addAttribute("resetPwdDto", resetPwdDto);
           return  "resetpassword";
       }

    }

    @PostMapping("/resetpwd")
    public String resetPwd(ResetPwdDto pwdDto,Model model){
        if(!(pwdDto.getNewpwd().equals(pwdDto.getConfirmpwd()))){
            model.addAttribute("emsg", "New pwd and confirm pwd should be same");
            return  "resetpassword";
        }
    UserDto user =userService.getUser(pwdDto.getEmail());
    if(user.getPwd().equals(pwdDto.getOldpwd())){
        boolean resetpwd=userService.resetPwd(pwdDto);
        if(resetpwd){
            return "redirect:dashboard";
                  }
        else{
            model.addAttribute("emsg","password update failed");
            return "resetpassword";
        }
    }else {
        model.addAttribute("emsg","given old password is wrong");
        return "resetpassword";
    }

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
    String quote =userService.getQuote();
    model.addAttribute("quote", quote);
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(){

        return  "redirect:/";
    }

}
