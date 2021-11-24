package by.goshakrovsh.racestats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(columnDefinition = "VARCHAR(255) UNIQUE NOT NULL")
    String name;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String image;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String location;
    Integer record;

    @JsonIgnore
    public String getFormatedRecord() {
        if (record == null) {
            return "";
        }

        int minutes = record / (60 * 1000);
        int seconds = (record - minutes * 60 * 1000) / 1000;
        int milliseconds = record - minutes * 60 * 1000 - seconds * 1000;
        return (minutes < 10 ? "0" + minutes : minutes) + ":"
                + (seconds < 10 ? "0" + seconds : seconds) + ":"
                + (milliseconds < 10 ? "00" + milliseconds :
                milliseconds < 100 ? "0" + milliseconds : milliseconds);
    }
}
