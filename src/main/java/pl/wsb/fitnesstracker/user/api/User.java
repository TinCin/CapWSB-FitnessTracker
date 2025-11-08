package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.healthmetrics.HealthMetrics;
import pl.wsb.fitnesstracker.statistics.api.Statistics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"statistics", "healthMetricsList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // relacja one to one z Statistics
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Statistics statistics;

    // relacja many to one z HealthMetrics
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthMetrics> healthMetricsList = new ArrayList<>();

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
    // metody pomocnicze
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
        if (statistics != null) {
            statistics.setUser(this);
        }
    }

    public void addHealthMetric(HealthMetrics metric) {
        healthMetricsList.add(metric);
        metric.setUser(this);
    }

}

