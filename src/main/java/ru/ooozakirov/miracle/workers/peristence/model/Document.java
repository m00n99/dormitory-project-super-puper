package ru.ooozakirov.miracle.workers.peristence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@Data
@Accessors(chain = true)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    private String filename;
    @Column(name = "mime_type")
    private String mimeType;
    private byte[] data;
}

