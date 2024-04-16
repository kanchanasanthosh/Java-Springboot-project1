package com.ashokit.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Country_Master")
public class CountryData {

    @Id
    @Column(name="country_id")
    private Integer country_id;

    private String country_name;



}
