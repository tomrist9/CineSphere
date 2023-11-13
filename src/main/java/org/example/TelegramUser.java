package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class TelegramUser {
    private String chatId;

    private String step;

    private String msg;

    private String fullName;

    private String selectLang;

    @Override
    public String toString() {
        return "TelegramUser{" +
                "chatId='" + chatId + '\'' +
                ", step='" + step + '\'' +
                ", msg='" + msg + '\'' +
                ", fullName='" + fullName + '\'' +
                ", selectLang='" + selectLang + '\'' +
                '}';
    }
}