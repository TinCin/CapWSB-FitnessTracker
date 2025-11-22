package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.Training;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_session")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "start_latitude")
    private Double startLatitude;

    @Column(name = "start_longitude")
    private Double startLongitude;

    @Column(name = "end_latitude")
    private Double endLatitude;

    @Column(name = "end_longitude")
    private Double endLongitude;

    @Column
    private Double altitude;

    // relacja do Training
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = true)
    private Training training;
}