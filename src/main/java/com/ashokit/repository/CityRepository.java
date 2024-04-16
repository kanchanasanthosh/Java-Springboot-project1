package com.ashokit.repository;

import com.ashokit.entity.CityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<CityData,Integer> {

    @Query(value = "select * from city_master where state_id=: state_id",nativeQuery = true)
    public List<CityData> getCities(Integer stateId);

}

