package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "user_event")
@Getter
@Setter
@NoArgsConstructor
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relacja many to one UserEvent z User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // relacja many to one UserEvent z Event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column
    private String status;
}