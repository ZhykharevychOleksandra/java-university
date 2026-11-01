package lab07.legacy.task1_srp;

public class WorkoutValidator {

    public void validate(WorkoutSessionData session) {

        // Перевірка об'єкта
        if (session == null) {
            throw new IllegalArgumentException(
                    "Параметр session не може бути null."
            );
        }

        // Перевірка користувача
        if (session.getUserId() == null ||
                session.getUserId().isBlank()) {

            throw new IllegalArgumentException(
                    "Ідентифікатор користувача не може бути порожнім."
            );
        }

        // Перевірка токена пристрою
        if (session.getDeviceToken() == null ||
                session.getDeviceToken().isBlank()) {

            throw new IllegalArgumentException(
                    "Токен пристрою не може бути порожнім."
            );
        }

        // Перевірка дистанції
        double distance = session.getDistanceKm();

        if (!Double.isFinite(distance) ||
                distance <= 0 ||
                distance > 300) {

            throw new IllegalArgumentException(
                    "Некоректна дистанція забігу."
            );
        }

        // Перевірка тривалості
        int duration = session.getDurationMinutes();

        if (duration <= 0 || duration > 1440) {

            throw new IllegalArgumentException(
                    "Некоректна тривалість тренування."
            );
        }

        // Перевірка пульсу
        int heartRate = session.getAverageHeartRate();

        if (heartRate < 40 || heartRate > 230) {

            throw new IllegalArgumentException(
                    "Пульс виходить за межі допустимих показників."
            );
        }

        // Перевірка ваги
        double weight = session.getUserWeightKg();

        if (!Double.isFinite(weight) ||
                weight <= 0 ||
                weight > 500) {

            throw new IllegalArgumentException(
                    "Некоректна вага користувача."
            );
        }
    }
}