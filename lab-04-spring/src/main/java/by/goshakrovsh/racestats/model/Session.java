package by.goshakrovsh.racestats.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
@Table(name = "sessions")
public class Session {
    public enum Tyre {
        Slick("Slick"),
        SemiSlick("SemiSlick"),
        RoadTyre("RoadTyre");

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
        Dry("Dry"),
        Wet("Wet");

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

    @NotNull
    private Integer time;

    @Column(columnDefinition = "timestamp(3)")
    @NotNull
    private Timestamp date_time;

    @Enumerated(EnumType.STRING)
    @Type(type = "by.goshakrovsh.racestats.enum_mapping.EnumTypePostgreSql")
    private Tyre tyre;
    @Enumerated(EnumType.STRING)
    @Type(type = "by.goshakrovsh.racestats.enum_mapping.EnumTypePostgreSql")
    private Conditions conditions;

    @Column(columnDefinition = "text")
    private String description;

    public String getDescription() {
        return description == null ? "" : description;
    }

    public String getFormatTime() {
        int minutes = time / (60 * 1000);
        int seconds = (time - minutes * 60 * 1000) / 1000;
        int milliseconds = time - minutes * 60 * 1000 - seconds * 1000;
        return (minutes < 10 ? "0" + minutes : minutes) + ":"
                + (seconds < 10 ? "0" + seconds : seconds) + ":"
                + (milliseconds < 10 ? "00" + milliseconds :
                    milliseconds < 100 ? "0" + milliseconds : milliseconds);
    }

    public void setFormatTime(String time) {
        var splitedString = time.split(":");
        try {
            this.time = 0;
            this.time += Integer.parseInt(splitedString[0]) * 1000 * 60;
            this.time += Integer.parseInt(splitedString[1]) * 1000;
            this.time += Integer.parseInt(splitedString[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
