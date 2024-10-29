--liquibase formatted sql

--changeset katrin_skt:1
CREATE TABLE Notification
(
    id          BIGINT PRIMARY KEY,
    chatId      BIGINT    NOT NULL,
    textMessage TEXT      NOT NULL,
    dateTime    TIMESTAMP NOT NULL
);