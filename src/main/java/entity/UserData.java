package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User_Details")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_id")
    private Integer userId;

    private  String name;
    @Column(name= "email",unique = true)
    private String email;
    private String pwd;
    private String pwd_updated;
    private Long phno;

    //fk
    @ManyToOne
    @JoinColumn(name="country_id")
    private  CountryData country;
    //fk
    @ManyToOne
    @JoinColumn(name="state_id")
    private  StatesData state;

    //fk
     @ManyToOne
    @JoinColumn(name="city_id")
    private CityData city;

    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;












}
