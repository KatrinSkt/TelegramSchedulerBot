package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private Long chatId;
    private String textMessage;
    private LocalDateTime dateTime;

    /**
     * Конструктор для создания экземпляра {@link Notification}.
     *
     * @param chatId      идентификатор чата, в который будет отправлено уведомление.
     * @param textMessage текст сообщения уведомления.
     * @param dateTime    дата и время отправки уведомления.
     */
    public Notification(Long chatId, String textMessage, LocalDateTime dateTime) {
        this.chatId = chatId;
        this.textMessage = textMessage;
        this.dateTime = dateTime;
    }

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
