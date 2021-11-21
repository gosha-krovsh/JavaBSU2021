package by.goshakrovsh.racestats.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
@Table(name = "sessions")
public class Session {
    public enum Tyre {
        slick("SLICK"),
        semi_slick("SEMI-SLICK"),
        road_tyre("ROAD-TYRE");

        private String name;
        Tyre(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    public enum Conditions {
        dry("DRY"),
        wet("WET");


        private String string;
        Conditions(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @Column(columnDefinition = "NOTNULL")
    private Integer time;

    @Column(columnDefinition = "timestamp(3) NOTNULL")
    private Timestamp date_time;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "DEFAULT 'slick'")
    private Tyre tyre;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "DEFAULT 'dry'")
    private Conditions conditions;

    @Column(columnDefinition = "text")
    private String description;

    public String getFormatedTime() {
        int minutes = time / (60 * 1000);
        int seconds = (time - minutes * 60 * 1000) / 1000;
        int milliseconds = time - minutes * 60 * 1000 - seconds * 1000;
        return (minutes < 10 ? "0" + minutes : minutes) + ":"
                + (seconds < 10 ? "0" + seconds : seconds) + ":"
                + (milliseconds < 10 ? "00" + milliseconds :
                    milliseconds < 100 ? "0" + milliseconds : milliseconds);
    }
}

