package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="city_Master")
public class CityData {

    @Id
    @Column(name="city_id")
    private Integer city_id;

    private String city_name;


    @ManyToOne
    @JoinColumn(name="state_id")
    private StatesData statesData;








}
