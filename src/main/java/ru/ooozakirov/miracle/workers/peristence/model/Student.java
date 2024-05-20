package ru.ooozakirov.miracle.workers.peristence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "student")
@Data
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private String studentId;
    private String firstname;
    private String lastname;
    private String middlename;
    private String phone;
    private String email;
    private StatusStudent status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy = "student")
    private List<Document> documents;
    @OneToMany(mappedBy = "student")
    private List<Inventory> inventories;
    @OneToMany(mappedBy = "student")
    private List<Receipt> receipts;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

