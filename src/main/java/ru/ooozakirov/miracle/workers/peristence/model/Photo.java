package ru.ooozakirov.miracle.workers.peristence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photo")
@Data
@Accessors(chain = true)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    @Column(name = "mime_type")
    private String mimeType;
    private byte[] data;
    @OneToOne(mappedBy = "photo")
    private Student student;
    @OneToOne(mappedBy = "photo")
    private Commandant commandant;
}
