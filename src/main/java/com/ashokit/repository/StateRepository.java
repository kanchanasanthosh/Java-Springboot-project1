package com.ashokit.repository;

import com.ashokit.entity.StatesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<StatesData,Integer> {

    @Query(value = "select * from states_master where country_id=:cid",nativeQuery = true)
    public List<StatesData> getStates(Integer cid);

}
