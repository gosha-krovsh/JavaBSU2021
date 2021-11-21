package by.goshakrovsh.racestats.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    public Car(String manufacturer,
               String model,
               String generation) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.generation = generation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @Column(columnDefinition = "varchar(255) NOT NULL")
    String manufacturer;
    @Column(columnDefinition = "varchar(255) NOT NULL")
    String model;
    @Column(columnDefinition = "varchar(255) DEFAULT ''")
    String generation;

    public String getName() {
        return manufacturer + " " + model;
    }
}
