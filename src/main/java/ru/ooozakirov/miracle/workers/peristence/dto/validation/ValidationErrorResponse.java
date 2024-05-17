package ru.ooozakirov.miracle.workers.peristence.dto.validation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
