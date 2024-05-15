package ru.ooozakirov.miracle.workers.peristence.model;


import lombok.Getter;

@Getter
public enum DocumentType {

    PASSPORT_RF ("Паспорт");

    private final String label;

    DocumentType(String label) {
        this.label = label;
    }

    public static DocumentType valueOfLabel(String label) {
        for (DocumentType e : values()) {
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
