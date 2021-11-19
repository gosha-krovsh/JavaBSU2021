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
        SLICK,
        SEMI_SLICK,
        ROAD_TYRE
    }
    public enum Conditions {
        DRY,
        WET
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;
    private int time;
    @Column(columnDefinition = "timestamp(3)")
    private Timestamp date_time;
    private Tyre tyre;
    private Conditions conditions;
    @Column(columnDefinition = "text")
    private String description;
}

