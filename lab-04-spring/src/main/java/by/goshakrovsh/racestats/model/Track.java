package by.goshakrovsh.racestats.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tracks")
public class Track {
    public Track(String name, String image, String location, Integer record) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.record = record;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String name;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String image;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String location;
    Integer record;
}
