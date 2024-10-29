package pro.sky.telegrambot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки Telegram-бота.
 * <p>
 * Этот класс использует аннотацию {@link Configuration} для определения бина
 * Telegram-бота, который будет использоваться в приложении. Он считывает токен
 * бота из конфигурации приложения и инициализирует экземпляр бота.
 * </p>
 */
@Configuration
public class TelegramBotConfiguration {

    /**
     * Токен для аутентификации Telegram-бота.
     * <p>
     * Значение токена считывается из конфигурации приложения с помощью
     * аннотации {@link Value}. Токен должен быть установлен в
     * {@code application.properties} или другом файле конфигурации.
     * </p>
     */
    @Value("${telegram.bot.token}")
    private String token;

    /**
     * Создает и настраивает экземпляр {@link TelegramBot}.
     * <p>
     * Этот метод создает новый экземпляр {@link TelegramBot} с заданным токеном,
     * а также выполняет команду {@link DeleteMyCommands}, чтобы удалить все
     * предыдущие команды бота. Возвращаемый объект будет управляться
     * контейнером Spring.
     * </p>
     *
     * @return экземпляр {@link TelegramBot}, настроенный с заданным токеном
     */
    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }
}

