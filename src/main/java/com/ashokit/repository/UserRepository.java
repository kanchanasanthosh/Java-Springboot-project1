package com.ashokit.repository;

import com.ashokit.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Integer> {

    public UserData findByEmail (String email);

    public  UserData findByEmailandPwd(String email, String pwd);




}
