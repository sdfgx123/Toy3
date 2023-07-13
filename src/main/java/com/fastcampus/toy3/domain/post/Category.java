package com.fastcampus.toy3.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Category {
    NEWBIE("새싹"),
    VIP("우수");

    private final String description;

    public static Category fromDescription(String description) {
        return Arrays.stream(values())
                .filter(category -> category.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s'는 존재하지 않는 등급입니다.",
                                                                                description)));
    }
}
