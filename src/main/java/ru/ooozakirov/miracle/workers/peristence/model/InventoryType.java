package ru.ooozakirov.miracle.workers.peristence.model;

import lombok.Getter;

@Getter
public enum InventoryType {
    DUVETCOVER ("Пододеяльник"),
    PILLOWCASE ("Наволочка"),
    SHEET ("Простыня"),
    TOWEL ("Полотенце"),
    PLAID ("Плед"),
    BLANKET ("Одеяло");

    private final String label;

    InventoryType(String label) {
        this.label = label;
    }

    public static InventoryType valueOfLabel(String label) {
        for (InventoryType e : values()) {
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
