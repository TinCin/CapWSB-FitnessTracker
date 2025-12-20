package pl.wsb.fitnesstracker.training.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Obiekt transferu danych (DTO) reprezentujący informacje o treningu.
 * Wykorzystywany do przesyłania danych treningowych przez API sieciowe.
 */

public class TrainingDto {
    private Long id;
    private UserDto user;
    private ActivityType activityType;
    private Date startTime;
    private Date endTime;
    private double distance;
    private double averageSpeed;
}