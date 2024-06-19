package com.deposit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Goals {
    REVOCABLE("Отзывной"),
    IRREVOCABLE("Безотзывной"),
    ;

    private final String name;

}

