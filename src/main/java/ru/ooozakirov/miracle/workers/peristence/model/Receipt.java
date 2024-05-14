package ru.ooozakirov.miracle.workers.peristence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Table(name = "receipt")
@Data
@Accessors(chain = true)
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @Temporal(value = TemporalType.DATE)
    private LocalDate date;
    private String data;
    @OneToOne(mappedBy = "receipt")
    private Check check;
}
