package dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;
    private  String name;
    private String email;
    private String phno;
    private  String pwd;
    private String pwdupdated;
    private String newpwd;
    private String confirmpwd;
    private Integer country_id;
    private Integer state_id;
    private Integer city_id;



}
