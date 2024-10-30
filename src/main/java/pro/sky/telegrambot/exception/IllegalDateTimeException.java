package pro.sky.telegrambot.exception;

public class IllegalDateTimeException extends RuntimeException{
    public IllegalDateTimeException(String message) {
        super(message);
    }
}
