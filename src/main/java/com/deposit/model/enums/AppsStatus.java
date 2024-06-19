package com.deposit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppsStatus {
    WAITING("Ожидание"),
    CONFIRMED("Депозит открыт"),
    REJECTED("Отказано"),
    ;

    private final String name;

}

