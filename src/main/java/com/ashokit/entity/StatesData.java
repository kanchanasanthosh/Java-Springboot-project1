package com.ashokit.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="State_Master")
public class StatesData {

    @Id
    @Column(name="state_id")
    private Integer state_id;

    private String state_name;

    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryData countryData;







}
