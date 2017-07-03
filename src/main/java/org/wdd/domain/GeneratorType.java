package org.wdd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum GeneratorType {
    LOCAL("-local"),
    REMOTE("-remote");

    private @Getter String commandlineParam;

    public static GeneratorType fromArgument(String arg) {
        return Arrays.stream(values())
                .filter(item -> item.commandlineParam.equals(arg))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Incorrect param"));
    }
}
