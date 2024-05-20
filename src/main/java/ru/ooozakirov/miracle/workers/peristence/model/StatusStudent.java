package ru.ooozakirov.miracle.workers.peristence.model;

import lombok.Getter;

@Getter
public enum StatusStudent {
    LIVES ("Живёт"),
    EVICTED ("Выселен");

    private final String label;

    StatusStudent(String label) {
        this.label = label;
    }

    public static StatusStudent valueOfLabel(String label) {
        for (StatusStudent e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
