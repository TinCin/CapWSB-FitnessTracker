package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrainingReportScheduler {

    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;

    @Scheduled(cron = "0 0 0 * * MON")

    public void generateWeeklyReport() {
        log.info("- Generowanie raportu tygodniowego -");

        Date oneWeekAgo = Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<User> users = userProvider.findAllUsers();
        List<Training> allTrainings = trainingProvider.getAllTrainings();

        for (User user : users) {
            List<Training> weeklyTrainings = allTrainings.stream()
                    .filter(training -> training.getUser().getId().equals(user.getId()))
                    .filter(training -> training.getEndTime().after(oneWeekAgo))
                    .toList();

            log.info("Użytkownik ID: {}, Email: {}", user.getId(), user.getEmail());
            log.info("Liczba treningów w ostatnim tygodniu: {}", weeklyTrainings.size());

            weeklyTrainings.forEach(training ->
                    log.info(" - Trening: {}, Czas zakończenia: {}", training.getActivityType(), training.getEndTime())
            );
        }

        log.info("- Zakończono generowanie raportu -");
    }
}