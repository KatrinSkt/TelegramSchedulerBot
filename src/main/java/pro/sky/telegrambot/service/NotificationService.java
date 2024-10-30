package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Сервис для управления уведомлениями.
 * <p>
 * Этот класс отвечает за отправку уведомлений пользователям через Telegram-бота.
 * Он использует репозиторий для получения уведомлений, которые необходимо отправить,
 * и отправляет их в заданное время.
 * </p>
 */
@Service
public class NotificationService {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final NotificationRepository notificationRepository;
    private final TelegramBot telegramBot;

    /**
     * Конструктор для создания экземпляра {@link NotificationService}.
     *
     * @param notificationRepository репозиторий для работы с уведомлениями.
     * @param telegramBot            экземпляр {@link TelegramBot}, используемый для отправки сообщений.
     */

    public NotificationService(NotificationRepository notificationRepository, TelegramBot telegramBot) {
        this.notificationRepository = notificationRepository;
        this.telegramBot = telegramBot;
    }

    /**
     * Запланированный метод для отправки уведомлений.
     * <p>
     * Этот метод выполняется по расписанию (каждую минуту) и ищет все уведомления,
     * которые должны быть отправлены в текущую минуту. После отправки уведомлений
     * они удаляются из базы данных.
     * </p>
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        List<Notification> messages = notificationRepository.findAllByDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        messages.forEach(task -> {
            SendMessage message = new SendMessage(task.getChatId(), task.getTextMessage());
            telegramBot.execute(message);
            logger.info("Уведомление отправлено: chatId = " + task.getChatId() + " , text = " + task.getTextMessage());
            notificationRepository.deleteById(task.getId());
            logger.info("Уведомление удалено из БД");
        });
    }
}
